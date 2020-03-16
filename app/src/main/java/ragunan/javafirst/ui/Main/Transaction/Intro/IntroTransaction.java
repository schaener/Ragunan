package ragunan.javafirst.ui.Main.Transaction.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Transaction.Detail.TransactionDetail;

public class IntroTransaction extends AppCompatActivity {
ImageView barcode;
    MultiFormatWriter multiFormatWriter;
    ImageView action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_transaction);
        barcode = findViewById(R.id.barcode);
        action = findViewById(R.id.Baction);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TransactionDetail.class);
                startActivity(i);
            }
        });
        multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode("David", BarcodeFormat.QR_CODE, 1000, 1000);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
       barcode.setImageBitmap(bitmap);
    }
}
