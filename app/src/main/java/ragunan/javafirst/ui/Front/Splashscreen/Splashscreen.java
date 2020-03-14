package ragunan.javafirst.ui.Front.Splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Home.Home;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(Splashscreen.this, Home.class);
                startActivity(home);
                finish();

            }
        },3000);
    }
    @Override
    public void onBackPressed() {

    }
}
