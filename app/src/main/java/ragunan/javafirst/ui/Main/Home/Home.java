package ragunan.javafirst.ui.Main.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.GlobalVariabels;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.Utils.SharedPrefManager;
import ragunan.javafirst.ui.Main.Content.Intro.Content;
import ragunan.javafirst.ui.Main.Topup.Intro.IntroTopup;
import ragunan.javafirst.ui.Main.Transaction.Intro.IntroTransaction;

import static ragunan.javafirst.Utils.SharedPrefManager.SP_MONEY;

public class Home extends AppCompatActivity {
CardView btOn,btOff,buy;
RelativeLayout startJourney;
ImageView img,topup;
    BluetoothAdapter bluetoothAdapter = null;
    Thread thread;
    TextView money;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
   LinearLayout ok;
SharedPrefManager sharedPrefManager;
SharedPreferences shared;
    ScriptHelper helper = new ScriptHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPrefManager = new SharedPrefManager(Home.this);
        shared = getSharedPreferences("spTMII", MODE_PRIVATE);

//        btOff = findViewById(R.id.btOff);
//        btOn = findViewById(R.id.btOn);
//        img = findViewById(R.id.gambar);
        money = findViewById(R.id.TVmoney);
        money.setText(helper.formatRupiah.format(Integer.valueOf(shared.getString( SP_MONEY , ""))));


        startJourney = findViewById(R.id.BstartJourney);
        topup = findViewById(R.id.topup);
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), IntroTopup.class);
                startActivity(i);
            }
        });
buy = findViewById(R.id.CVbuy);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
if (!bluetoothAdapter.isEnabled()){
    warningBluetoothTurnOn();
}



            widgetHandler();




//        btOn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//        });
//        btOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bluetoothAdapter.disable();
//              thread.interrupt();
//Thread.dumpStack();
//
//
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void widgetHandler(){

            startJourney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!bluetoothAdapter.isEnabled()){
                        warningBluetoothTurnOn();

                    }
                    {
                    Intent i =   new Intent(getApplicationContext(), Content.class);
                    startActivity(i);
                    }
    }
            });
buy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (!bluetoothAdapter.isEnabled()){
            warningBluetoothTurnOn();

        }
        {
            Intent i =   new Intent(getApplicationContext(), IntroTransaction.class);
            startActivity(i);
        }
    }
});

}


    public void warningBluetoothTurnOn(){
        dialog = new AlertDialog.Builder(Home.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alert_bluetooth_turn_on_item, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        final AlertDialog dialogg = dialog.create();

       ok = dialogView.findViewById(R.id.Bok);
      ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter == null){
                    finish();
                    Toast.makeText(getApplicationContext(),"Bluetooth tidak tersedia di HP Anda",Toast.LENGTH_SHORT).show();
                }
               else if (!bluetoothAdapter.isEnabled()){


                    bluetoothAdapter.enable();
dialogg.dismiss();
                }
                else{
                    warningBluetoothTurnOn();
                }
             }
        });



        dialogg.show();
    }


}
