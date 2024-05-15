package com.example.melitruko.presentation.ui.view.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NewPlayerFragment extends DialogFragment {

    private FragmentNewPlayerBinding binding;
    private HomeViewModel viewModel;
    private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    private static final String GALLERY_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private ActivityResultLauncher<String> requestPermissionCamera;
    private ActivityResultLauncher<String> requestPermissionGallery;
    private ActivityResultLauncher<Intent> resultCamera;
    private ActivityResultLauncher<Intent> resultGallery;
    private ActivityResultLauncher<Intent> resultFile;
    private Uri selectedPhotoUri = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionCamera();
        requestPermissionGallery();
        createNewFile();
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

    private void createNewFile() {
        String pattern = "yyyyMMdd_HHmmss";
        String timeStamp = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        File internalStorageDir = requireContext().getFilesDir();
        File imageFile = new File(internalStorageDir.getPath() + File.separator + "profile_image_" + timeStamp + ".jpg");

        selectedPhotoUri = Uri.fromFile(imageFile);
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
        setupPlayerImageView(bitmap);
    }
    private void createImageOfCamera() {
        resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Bitmap bitmap = null;
            if ((result.getData() != null) && (result.getData().getExtras() != null) && (result.getData().getExtras().get("data") != null)) {
                Bundle extras = result.getData().getExtras();
                bitmap = (Bitmap) extras.get("data");
            }
            setupPlayerImageView(bitmap);
        });
    }
    private void createImageOfGallery() {
        resultGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Bitmap bitmap = null;
            if ((result.getData() != null) && (result.getData().getData() != null)) {
                if (Build.VERSION.SDK_INT < 28) {
                    try {
                        assert result.getData() != null;
                        bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), result.getData().getData());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    assert result.getData() != null;
                    ImageDecoder.Source source = ImageDecoder.createSource(requireActivity().getContentResolver(), Objects.requireNonNull(result.getData().getData()));
                    try {
                        bitmap = ImageDecoder.decodeBitmap(source);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            setupPlayerImageView(bitmap);
        });
    }

    private void setupIntentCreateFile() {
        Intent intent = new Intent(MediaStore.EXTRA_OUTPUT, selectedPhotoUri);
        resultFile.launch(intent);

        resultFile = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if ((result.getData() != null) && (result.getData().getExtras() != null))
                Toast.makeText(requireContext(), result.getData().getData().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void setupLauncherResultCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoUri);
        resultCamera.launch(intent);
    }
    private void setupLauncherResultGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoUri);
        resultGallery.launch(intent);
    }

    private boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }
    private boolean isGalleryPermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.GALLERY_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    private void setupPlayerImageView(Bitmap bitmap) {
        if (bitmap != null) {
            binding.ivPlayer.setImageBitmap(bitmap);
            binding.ivPlayer.setVisibility(View.VISIBLE);
            binding.ibRemovePhoto.setVisibility(View.VISIBLE);
            binding.btnGallery.setVisibility(View.GONE);
            binding.btnCamera.setVisibility(View.GONE);
            binding.btnDefaultImage.setVisibility(View.GONE);
        }
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