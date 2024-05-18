package com.example.melitruko.presentation.ui.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentNewPlayerBinding;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NewPlayerFragment extends DialogFragment {

    private FragmentNewPlayerBinding binding;
    private HomeViewModel viewModel;
    private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    private static final String GALLERY_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private ActivityResultLauncher<String> requestPermissionCamera;
    private ActivityResultLauncher<String> requestPermissionGallery;
    private ActivityResultLauncher<String> requestPermissionWriteStorage;
    private ActivityResultLauncher<Intent> resultCamera;
    private ActivityResultLauncher<Intent> resultGallery;
    private Uri selectedPhotoUri = null;
    private File imageFile = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionCamera();
        requestPermissionGallery();
        requestPermissionWriteStorage();
        createImageOfCamera();
        createImageOfGallery();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewPlayerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setupActionsButtons();
        setupObservers();

        return binding.getRoot();
    }

    private void setupObservers() {
        viewModel.insertLiveData.observe(getViewLifecycleOwner(), result ->
                Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show());
    }

    private void setupActionsButtons() {
        binding.btnDefaultImage.setOnClickListener(view -> {
            createBitmapDefaultImage();
        });

        binding.btnCamera.setOnClickListener(view -> {
            validationCameraPermission();
        });

        binding.btnGallery.setOnClickListener(view -> {
            validationGalleryPermission();
        });

        binding.ibRemovePhoto.setOnClickListener(view -> {
            removePhoto();
        });

        binding.btnToSave.setOnClickListener(view -> {
            String playerName = Objects.requireNonNull(binding.inputName.getText()).toString();
            if (viewModel.nameValidation(playerName)) {

                viewModel.insertPlayer(playerName, null);
            } else {
                Toast.makeText(requireContext(), "Nome inválido", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnCancel.setOnClickListener(view -> dismiss());
    }

    private void createImageFile() {
        File mediaStorageDir = new File(requireContext().getFilesDir(), "profiles_images");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Toast.makeText(requireContext(), "profiles_images", Toast.LENGTH_LONG).show();
            }
        }

        String pattern = "yyyyMMdd_HHmmss";
        String timeStamp = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        imageFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    private Uri getUriOfImageFile(){
        return FileProvider.getUriForFile(requireContext(), requireContext().getApplicationContext().getPackageName() + ".provider", imageFile);
    }

    private void requestPermissionCamera() {
        requestPermissionCamera = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                setupLauncherResultCamera();
            } else {
                Toast.makeText(requireContext(), "É preciso permitir o acesso à câmera", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestPermissionGallery() {
        requestPermissionGallery = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                setupLauncherResultGallery();
            } else {
                Toast.makeText(requireContext(), "É preciso permitir o acesso à galeria", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestPermissionWriteStorage() {
        requestPermissionWriteStorage = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (!isGranted) {
                Toast.makeText(requireContext(), "É preciso permitir a gravação de imagens", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validationCameraPermission() {
        if (isCameraPermissionGranted()) {
            setupLauncherResultCamera();
        } else {
            requestPermissionCamera.launch(CAMERA_PERMISSION);
        }
    }

    private void validationGalleryPermission() {
        if (isGalleryPermissionGranted()) {
            setupLauncherResultGallery();
        } else {
            requestPermissionGallery.launch(GALLERY_PERMISSION);
        }
    }

    private void validationStoragePermission() {
        if (!isWriteStoragePermissionGranted()) {
            requestPermissionWriteStorage.launch(WRITE_STORAGE_PERMISSION);
        }
    }

    private void createBitmapDefaultImage() {
        Bitmap bitmap = null;
        ImageDecoder.Source source;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            source = ImageDecoder.createSource(requireActivity().getResources(), R.drawable.default_person_bmp);

            try {
                bitmap = ImageDecoder.decodeBitmap(source);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //TODO setupPlayerImageView(bitmap);
    }

    private void createImageOfCamera() {
        resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                validationStoragePermission();
                if (imageFile != null) {
                    setupPlayerImageView(imageFile);
                }
            }
        });
    }

    private void createImageOfGallery() {
        resultGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if ((result.getData() != null) && (result.getData().getData() != null)) {
                validationStoragePermission();

                try {
                    createCopyImage(result.getData().getData());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void createCopyImage(Uri data) throws IOException {
        InputStream inputStream = requireContext().getContentResolver().openInputStream(data);

        if (inputStream != null) {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] compressedImage = byteArrayOutputStream.toByteArray();

            createImageFile();
            FileOutputStream outputStream = new FileOutputStream(imageFile);

            outputStream.write(compressedImage);
            outputStream.close();

            setupPlayerImageView(imageFile);
        }
    }

    private void setupLauncherResultCamera() {
        createImageFile();

        if (imageFile != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriOfImageFile());
            resultCamera.launch(intent);
        }
    }

    private void setupLauncherResultGallery() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            resultGallery.launch(intent);
    }

    private boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isGalleryPermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.GALLERY_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isWriteStoragePermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.WRITE_STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    private void setupPlayerImageView(File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            binding.ivPlayer.setImageBitmap(bitmap);
            binding.ivPlayer.setVisibility(View.VISIBLE);
            binding.ibRemovePhoto.setVisibility(View.VISIBLE);
            binding.btnGallery.setVisibility(View.GONE);
            binding.btnCamera.setVisibility(View.GONE);
            binding.btnDefaultImage.setVisibility(View.GONE);
    }

    private void removePhoto() {
        // TODO remover foto da view model
        binding.ivPlayer.setVisibility(View.GONE);
        binding.ibRemovePhoto.setVisibility(View.GONE);
        binding.btnGallery.setVisibility(View.VISIBLE);
        binding.btnCamera.setVisibility(View.VISIBLE);
        binding.btnDefaultImage.setVisibility(View.VISIBLE);
    }
}