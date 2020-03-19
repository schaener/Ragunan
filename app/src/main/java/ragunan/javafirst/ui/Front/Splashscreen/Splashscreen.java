package ragunan.javafirst.ui.Front.Splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.SharedPrefManager;
import ragunan.javafirst.ui.Main.Home.Home;

import static ragunan.javafirst.Utils.SharedPrefManager.SP_MONEY;

public class Splashscreen extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
   SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        shared = getSharedPreferences("spTMII", MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(Splashscreen.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (shared.getString( SP_MONEY , "").equals("0")){
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_MONEY , "0");

                }
              else {
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_MONEY , shared.getString( SP_MONEY , ""));

                }
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

    @Override
    protected void onStart() {
        super.onStart();
        if (shared.getString( SP_MONEY , "").equals("")){

            sharedPrefManager.saveSPString(SharedPrefManager.SP_MONEY , "0");

    }
}}
