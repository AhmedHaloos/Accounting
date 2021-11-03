package com.engashm.possaror;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.UseCase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
     public static final String TAG = "debug_poss";
     private ExtendedFloatingActionButton management, client , delegate = null;
     ActivityResultLauncher<Void> launcher = null;
     BarcodeActivity barcodeActivity = null;
     public static final String CAM_PERMISSION = Manifest.permission.CAMERA;
     public static final int GRANTED = PackageManager.PERMISSION_GRANTED;
     public static final int DENIED = PackageManager.PERMISSION_GRANTED;
     public static final int REQ_CODE = 2;
     boolean permissionstate = false;
     UseCase previewUsecase = null;
     UseCase takepicUsecase = null;
     CameraDevice cameraDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        management = findViewById(R.id.management_btn);
      management.setOnClickListener(loginListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE && grantResults.length>0){
            permissionstate = true;
        }
    }

    View.OnClickListener QRreader = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, BarcodeActivity.class);
            startActivity(intent);

        }
    };
    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    };

}