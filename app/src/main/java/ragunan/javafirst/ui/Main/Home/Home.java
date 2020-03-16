package ragunan.javafirst.ui.Main.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ragunan.javafirst.Notification.MyNotificationPublisher;
import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Content.Intro.Content;
import ragunan.javafirst.ui.Main.Transaction.Intro.IntroTransaction;

public class Home extends AppCompatActivity {
CardView btOn,btOff;
RelativeLayout startJourney,buy;
ImageView img;
    BluetoothAdapter bluetoothAdapter = null;
    Thread thread;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
   LinearLayout ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        btOff = findViewById(R.id.btOff);
//        btOn = findViewById(R.id.btOn);
//        img = findViewById(R.id.gambar);
        startJourney = findViewById(R.id.BstartJourney);
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
