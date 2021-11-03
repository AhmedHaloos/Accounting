package com.engashm.possaror;


import static androidx.camera.core.CameraSelector.LENS_FACING_BACK;

import static com.engashm.possaror.MainActivity.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class BarcodeActivity extends AppCompatActivity {

    Context context = this;
    PreviewView previewView = null;
    ListenableFuture<ProcessCameraProvider> cameraFutureListener = null;
    ProcessCameraProvider cameraProvider = null;
    CameraSelector cameraSelector = null;
    FloatingActionButton captureImage = null;
    TextView barcodeTextView = null;
    ImageCapture imageCapture = null;
    Preview preview = null;
    ImageAnalysis imageAnalysis = null;
    BarcodeScannerOptions  barcodeScannerOptions = null;
    BarcodeScanner scanner;
    ImageProxy imageProxy = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_reader);
        initCamera();

    }
    
    private void initCamera(){
    previewView =    findViewById(R.id.previeview);
    barcodeTextView = findViewById(R.id.barcode_txt);
    cameraFutureListener = ProcessCameraProvider.getInstance(context);
    cameraSelector = new CameraSelector.Builder().requireLensFacing(LENS_FACING_BACK).build();
    addCamProviderListener();
    barcodeScannerOptions = new BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build();
    scanner = BarcodeScanning.getClient(barcodeScannerOptions);
}
// capture image button listener
    View.OnClickListener captureImageListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            imageCapture.takePicture(getMainExecutor(),
                    new ImageCapture.OnImageCapturedCallback() {
                @Override
                public void onCaptureSuccess(@NonNull ImageProxy image) {
                    super.onCaptureSuccess(image);
                    Log.d(TAG, "onCaptureSuccess: image captured");
                }
                @Override
                public void onError(@NonNull ImageCaptureException exception) {
                    super.onError(exception);
                    Log.d(TAG, "onError: exception : "+exception.getMessage());
                }
            });
        }
    }
};
//add camera provider listenr
    private void addCamProviderListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            cameraFutureListener.addListener(() -> {
                try {
                    cameraProvider = cameraFutureListener.get();
                    setPreviewUsecase(); setTakepicUsecase(); setImageAnalysis();
                    bindUsecases(preview, imageCapture, imageAnalysis);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, context.getMainExecutor());
        }
    }
    // preview use case
    public void setPreviewUsecase(){
        Preview preview = new Preview.Builder()
                .build();
       preview.setSurfaceProvider(previewView.getSurfaceProvider());
       ;
      this.preview = preview;
    }

    //takepicture usecase
    public void setTakepicUsecase(){
    ImageCapture imageCapture = new ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build();
       this.imageCapture = imageCapture;
    }

    private void setImageAnalysis(){
        imageAnalysis = new ImageAnalysis.Builder()
                .setBackgroundExecutor(Executors.newSingleThreadExecutor())
                .build();
        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor()
                , new ImageAnalysis.Analyzer() {
                    @Override
                    public void analyze(@NonNull ImageProxy image) {
                  @SuppressLint("UnsafeOptInUsageError")
                  Image mediaImage = image.getImage();
                  imageProxy = image;
                  InputImage inputImage = InputImage.fromMediaImage(mediaImage,
                          image.getImageInfo().getRotationDegrees());
                  processInputImg(inputImage);
                    }
                });

    }
    private void processInputImg(InputImage inputImage){
        Task<List<Barcode>> task = scanner.process(inputImage);
        task.addOnSuccessListener(barcodes -> processBarcodes(barcodes));

        task.addOnCompleteListener(task1 -> imageProxy.close());

    }
    private void processBarcodes(List<Barcode> barcodes){
        for (Barcode barcode: barcodes) {
           String barcodeData =  barcode.getRawValue();
            Toast.makeText(this, ""+barcodeData, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "processBarcodes: "+barcodeData);
           barcodeTextView.setText(barcodeData);
        }
    }

    public Camera bindUsecases(UseCase...useCases){
       return cameraProvider.bindToLifecycle((LifecycleOwner)context,
                cameraSelector,useCases);
    }

}
