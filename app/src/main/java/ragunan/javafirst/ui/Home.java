package ragunan.javafirst.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import ragunan.javafirst.Notification.MyNotificationPublisher;
import ragunan.javafirst.R;

public class Home extends AppCompatActivity {
CardView btOn,btOff;
ImageView img;
    BluetoothAdapter bluetoothAdapter = null;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btOff = findViewById(R.id.btOff);
        btOn = findViewById(R.id.btOn);
        img = findViewById(R.id.gambar);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    bluetoothAdapter.enable();

           thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (!Thread.interrupted())
                            try {
                                Thread.sleep(5000);
                                runOnUiThread(new Runnable() // start actions in UI thread
                                {

                                    @Override
                                    public void run() {

                                        bluetoothScanning();


                                    }
                                });
                            } catch (InterruptedException e) {
                                // ooops
                            }
                    }
                });
           thread.start();
            }

        });
        btOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothAdapter.disable();
              thread.interrupt();
Thread.dumpStack();



            }
        });
    }
    public void bluetoothScanning(){

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getApplicationContext().registerReceiver(mReceiver, filter);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

    }


    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Toast.makeText(getApplicationContext(),"Device Name: " + "device " + deviceName,Toast.LENGTH_SHORT).show();
                scheduleNotification(getNotification( deviceName+" Found. You are in Koala's Cage" ) , 1000 ) ;
                Log.d("mac address",deviceHardwareAddress);
               if (deviceHardwareAddress.equals("E0:50:E4:E6:6E:Q2")){
                    img.setImageResource(R.drawable.jerapah);

                }
               else {
                   img.setImageResource(R.drawable.jerapah);
               }

                }
        }
    };

    private void scheduleNotification (Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher.class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, "default") ;
        builder.setContentTitle( "Pemberitahuan" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( "10001") ;
        return builder.build() ;
    }
}
