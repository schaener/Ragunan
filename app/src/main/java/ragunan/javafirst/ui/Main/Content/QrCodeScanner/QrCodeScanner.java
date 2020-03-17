package ragunan.javafirst.ui.Main.Content.QrCodeScanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Transaction.Detail.TransactionDetail;

public class QrCodeScanner extends AppCompatActivity  implements  ZXingScannerView.ResultHandler{
    FrameLayout barcodeOn;

    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);
        mScannerView = new ZXingScannerView(QrCodeScanner.this);
       setContentView(mScannerView);

    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getText()); // Prints scan results
        // Prints the scan format (qrcode, pdf417 etc.)
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage("Selamat Anda Dapat 500 coin. Segera Tukarkan untuk mendapatkan hadiah yang menarik");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
finish();
            }
        });
//        builder.setMessage(rawResult.getText());
         AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:

    }
    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }
}
