package ragunan.javafirst.ui.Main.Transaction.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Transaction.Detail.TransactionDetail;

public class IntroTransaction extends AppCompatActivity  implements ZXingScannerView.ResultHandler {
FrameLayout barcodeOn;
ImageView barcodeOff;
    MultiFormatWriter multiFormatWriter;
    private ZXingScannerView mScannerView;

    ImageView action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_transaction);
barcodeOn = findViewById(R.id.barcodeOn);
barcodeOff = findViewById(R.id.barcodeOff);
action = findViewById(R.id.Baction);
action.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        barcodeOff.setVisibility(View.GONE);
        mScannerView = new ZXingScannerView(IntroTransaction.this);
//        setContentView(mScannerView);
        barcodeOn.addView(mScannerView);
        mScannerView.setResultHandler(IntroTransaction.this);
        // Start camera on resume
        mScannerView.startCamera();

    }
});

    }
    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getText()); // Prints scan results
        // Prints the scan format (qrcode, pdf417 etc.)
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        Intent i = new Intent(getApplicationContext(),TransactionDetail.class);
        mScannerView.stopCamera();
        startActivity(i);
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Register ourselves as a handler for scan results.
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        // Stop camera on pause
//        mScannerView.stopCamera();
//    }
}
