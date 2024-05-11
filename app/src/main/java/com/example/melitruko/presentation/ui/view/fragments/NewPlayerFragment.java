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
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentNewPlayerBinding;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.io.IOException;
import java.util.Objects;

public class NewPlayerFragment extends DialogFragment {

    private FragmentNewPlayerBinding binding;
    private HomeViewModel viewModel;
    private static final String GALLERY_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private ActivityResultLauncher<String> requestPermissionGallery;
    private ActivityResultLauncher<Intent> resultGallery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createImageBitmap();

        requestPermissionGallery = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted){
                resultGallery.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
            } else {
                Toast.makeText(requireContext(), "É preciso permitir o acesso à galeria", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewPlayerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnDefaultImage.setOnClickListener(view -> {
            binding.ivPlayer.setImageResource(R.drawable.default_person_bmp);
        });

        binding.btnCamera.setOnClickListener(view -> {

        });

        binding.btnGallery.setOnClickListener(view -> {
            validationGalleryPermission();
        });

        binding.ibRemovePhoto.setOnClickListener(view -> {

        });

        binding.btnCancel.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }

    private void validationGalleryPermission() {
        if (isGalleryPermissionGranted()) {
            resultGallery.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        } else {
            requestPermissionGallery.launch(GALLERY_PERMISSION);
        }
    }

    private void createImageBitmap() {
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

    private void setupPlayerImageView(Bitmap bitmap) {
        if (bitmap != null) {
            binding.ivPlayer.setVisibility(View.VISIBLE);
            binding.ibRemovePhoto.setVisibility(View.VISIBLE);
            binding.ivPlayer.setImageBitmap(bitmap);
            binding.btnGallery.setVisibility(View.GONE);
            binding.btnCamera.setVisibility(View.GONE);
            binding.btnDefaultImage.setVisibility(View.GONE);
        }
    }

    private boolean isGalleryPermissionGranted() {
        return ContextCompat.checkSelfPermission(requireContext(), NewPlayerFragment.GALLERY_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }
}