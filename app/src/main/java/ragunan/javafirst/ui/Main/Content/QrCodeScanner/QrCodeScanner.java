package ragunan.javafirst.ui.Main.Content.QrCodeScanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ragunan.javafirst.R;

public class QrCodeScanner extends AppCompatActivity  implements  ZXingScannerView.ResultHandler{
 ZXingScannerView scannerView;
  boolean isCaptured = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);
    }

    @Override
    protected void onStart() {
       scannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }
public void doRequestPermission(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{(Manifest.permission.CAMERA)}, 100);
        }
    }
}


    @Override
    public void handleResult(Result result) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100){
            scannerView.startCamera();
        }
    }
}
