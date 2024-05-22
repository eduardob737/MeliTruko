package com.example.melitruko.presentation.ui.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

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
    private ActivityResultLauncher<String> requestPermissionCamera, requestPermissionGallery, requestPermissionWriteStorage;
    private ActivityResultLauncher<Intent> resultCamera, resultGallery;
    private File imageFile = null, fileDefaultImage = null;
    private String pathImage = null;
    private final String NAME_FILE_DEFAULT_IMAGE = "IMG_DEFAULT_PLAYER.jpg";
    private final String NAME_FOLDER_PROFILE_IMAGES = "profile_images";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionCamera();
        requestPermissionGallery();
        requestPermissionWriteStorage();
        createImageOfCamera();
        createImageOfGallery();
        validationStoragePermission();
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
        viewModel.insertLiveData.observe(getViewLifecycleOwner(), result -> Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show());
        viewModel.statusSuccessLiveData.observe(getViewLifecycleOwner(), result -> {
            if (result)
                NewPlayerFragment.this.dismiss();
        });
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
            if (validationInputData(playerName))
                viewModel.insertPlayer(playerName, pathImage);
        });

        binding.btnCancel.setOnClickListener(view -> dismiss());
    }

    private boolean validationInputData(String name) {
        if (!viewModel.isNameValid(name)) {
            Toast.makeText(requireContext(), "Nome inválido", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (pathImage == null) {
                Toast.makeText(requireContext(), "Nenhuma imagem foi selecionada", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    private void createImageFile() {
        String pattern = "yyyyMMdd_HHmmss";
        String timeStamp = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        imageFile = new File(getFolderProfileImages().getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    private File getFolderProfileImages() {
        File mediaStorageDir = new File(requireContext().getFilesDir(), NAME_FOLDER_PROFILE_IMAGES);
        fileDefaultImage = new File(mediaStorageDir.getPath() + File.separator + NAME_FILE_DEFAULT_IMAGE);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Toast.makeText(requireContext(), "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();
            } else {
                createFileDefaultImage();
            }
        }
        return mediaStorageDir;
    }

    private void createFileDefaultImage() {
        try {
            InputStream inputStream = requireContext().getResources().getAssets().open("default_image_player.bmp");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(fileDefaultImage);
            outputStream.write(getCompressedImage(bitmap));
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                Toast.makeText(requireContext(), "É preciso permitir acesso ao armazenamento do dispositivo", Toast.LENGTH_LONG).show();
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
        if (fileDefaultImage == null) {
            getFolderProfileImages();
        }
        setupPlayerImageView(fileDefaultImage);
    }

    private void createImageOfCamera() {
        resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {

                if ((result.getData() != null) && (result.getData().getExtras() != null)) {
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    try {
                        compressAndSavePhoto(imageBitmap);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void createImageOfGallery() {
        resultGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if ((result.getData() != null) && (result.getData().getData() != null)) {
                try {
                    InputStream inputStream = requireContext().getContentResolver().openInputStream(result.getData().getData());
                    if (inputStream != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        compressAndSavePhoto(bitmap);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void compressAndSavePhoto(Bitmap bitmap) throws IOException {
        if (bitmap != null) {
            byte[] compressedImage = getCompressedImage(bitmap);

            createImageFile();
            FileOutputStream outputStream = new FileOutputStream(imageFile);

            outputStream.write(compressedImage);
            outputStream.close();

            setupPlayerImageView(imageFile);
        }
    }

    private byte[] getCompressedImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void setupLauncherResultCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultCamera.launch(intent);
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

    private void setupPlayerImageView(@NonNull File file) {
        pathImage = file.getAbsolutePath();
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        binding.ivPlayer.setImageBitmap(bitmap);
        binding.ivPlayer.setVisibility(View.VISIBLE);
        binding.ibRemovePhoto.setVisibility(View.VISIBLE);
        binding.btnGallery.setVisibility(View.GONE);
        binding.btnCamera.setVisibility(View.GONE);
        binding.btnDefaultImage.setVisibility(View.GONE);
    }

    private void removePhoto() {
        pathImage = null;
        binding.ivPlayer.setVisibility(View.GONE);
        binding.ibRemovePhoto.setVisibility(View.GONE);
        binding.btnGallery.setVisibility(View.VISIBLE);
        binding.btnCamera.setVisibility(View.VISIBLE);
        binding.btnDefaultImage.setVisibility(View.VISIBLE);
    }
}