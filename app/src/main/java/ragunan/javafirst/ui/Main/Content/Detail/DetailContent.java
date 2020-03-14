package ragunan.javafirst.ui.Main.Content.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;
import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Content.QrCodeScanner.QrCodeScanner;
import ragunan.javafirst.ui.Main.Content.YoutubePlayer.PlayYoutube;

public class DetailContent extends AppCompatActivity {
CardView video,share,Qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);
        video = findViewById(R.id.CVvideo);
        share = findViewById(R.id.CVShare);
        Qr = findViewById(R.id.CVQr);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlayYoutube.class);
                startActivity(i);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Jerapah memiliki leher yang panjang");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        Qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat intent baru untuk memanggil CaptureActivity bawaan ZXing
                Intent captureIntent = new Intent(DetailContent.this, CaptureActivity.class);

                // Kemudian kita mengeset pesan yang akan ditampilkan ke user saat menjalankan QRCode scanning
                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");

                // Melakukan startActivityForResult, untuk menangkap balikan hasil dari QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                 } else if (resultCode == Activity.RESULT_CANCELED) {
                //failed
                }
        } else {
            //failed
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
