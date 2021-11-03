package com.engashm.possaror;

import static android.graphics.ImageFormat.JPEG;
import static com.engashm.possaror.MainActivity.REQ_CODE;
import static com.engashm.possaror.MainActivity.TAG;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {
    CameraDevice myCamera;
    CameraCaptureSession customCaptureSession;
    CaptureRequest captureRequest;
    CameraManager cameraManager ;
    TextureView textureView;
    FloatingActionButton button;
    Handler handler;

    public CameraActivity(){

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_view);
        initView();
        initCamera();
    }

    private void initView() {
       textureView = findViewById(R.id.previeview);
       textureView.setSurfaceTextureListener(textureListener);
       button = findViewById(R.id.takepic);
       button.setOnClickListener(clickListener);
    }
    private void initCamera(){

        try {
            handler = new Handler(getMainLooper());
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
           String CameraId =  cameraManager.getCameraIdList()[0];
           if (checkSelfPermission(Manifest.permission.CAMERA)
                   == PackageManager.PERMISSION_GRANTED){
            cameraManager.openCamera(CameraId, stateCallback, null);
           }
           else requestPermissions(new String[]{Manifest.permission.CAMERA},
                           REQ_CODE);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    /*************** Listeners************/
    //StateCallback
    CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {

            myCamera = cameraDevice;
            createCaptureSession();
            Log.d(TAG, "onOpened: createCaptureSession()");
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            Log.d(TAG, "onDisconnected: camrea diconnected!");
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            Log.d(TAG, "onError: ");
        }
    };
    //OnClickListener
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

// TextureListener
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

    }
};
    //capture callback
    CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                       @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Log.d(TAG, "onCaptureCompleted: caputre completed");

        }
    };
    //capture state callback
    CameraCaptureSession.StateCallback stateCaptureCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
          customCaptureSession = cameraCaptureSession;
          createCaptureRequest();
            Log.d(TAG, "onConfigured: configuration completed, createCaptureRequest()");
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            Log.d(TAG, "onConfigureFailed: ");
        }
    };
    private void createCaptureSession(){
        //CameraCaptureSession cameraCaptureSession = null;
        Surface surface =  new Surface(textureView.getSurfaceTexture());
        InputConfiguration inputConfiguration = new InputConfiguration(200, 200, JPEG);
        OutputConfiguration outputConfiguration = new OutputConfiguration(surface);
        List<OutputConfiguration> outputConfigurationList = Arrays.asList(outputConfiguration);
        SessionConfiguration configuration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            configuration = new SessionConfiguration(SessionConfiguration.SESSION_REGULAR,
                    outputConfigurationList, Executors.newSingleThreadExecutor(), stateCaptureCallback);

            try {
             myCamera.createCaptureSession(configuration);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

    }
    private void createCaptureRequest(){
        Surface surface = new Surface(textureView.getSurfaceTexture());
        CaptureRequest.Builder builder = null;
        try {
            builder =  myCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            builder.addTarget(surface);
           customCaptureSession.setRepeatingRequest( builder.build(), captureCallback, handler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void captureView(){
        Surface surface = new Surface(textureView.getSurfaceTexture());
        CaptureRequest.Builder builder = null;
        try {
            builder =  myCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            builder.addTarget(surface);
            customCaptureSession.capture( builder.build(), captureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
