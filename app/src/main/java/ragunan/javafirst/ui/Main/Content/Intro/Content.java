package ragunan.javafirst.ui.Main.Content.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ragunan.javafirst.Notification.MyNotificationPublisher;
import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Content.Detail.DetailContent;

public class Content extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    CardView content;
    private GoogleMap mMap;
    private LatLng latLngAr = new LatLng(-6.312356, 106.820094);
    private LatLng latLngDt = new LatLng(-0.927034, 122.864185);
    private LatLng latLngf2 = new LatLng(-0.988182, 122.524080);
    private LatLng latLngGs = new LatLng(-0.534577, 123.062860);
    private LatLng latLngGr = new LatLng(-0.411886, 123.152717);
    private LatLng latLngJg = new LatLng(-0.479769, 122.192278);
    private LatLng latLngLv = new LatLng(-0.530067, 122.381586);
    private LatLng latLngLo = new LatLng(-0.585299, 122.983014);
    private LatLng latLngOa = new LatLng(-0.499045, 123.029332);
    private LatLng latLngGu = new LatLng(-0.499561, 123.033463);
    private LocationManager mLocationManager = null;
    private String provider = null;
    private Marker mCurrentPosition = null;
    CameraPosition cameraPosition;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        content = findViewById(R.id.CVContent);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DetailContent.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        unKnownLocation();
        startService();

//        if (isProviderAvailable() && (provider != null)) {
//            locateCurrentPosition();
//        }

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void unKnownLocation() {

        mMap.addMarker(new MarkerOptions()
                .position(latLngAr)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.marker))

                .title("Ragunan")
                .snippet("Ragunan Jaksel"));


        cameraPosition = new CameraPosition.Builder().target(latLngAr).zoom(8).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent;
                //  Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
//                intent = new Intent(getApplicationContext(), Detail_maps.class);
//                intent.putExtra("nama",marker.getTitle());
//                startActivity(intent);

            }
        });
    }

    private void locateCurrentPosition() {

        int status = getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                getPackageName());

        if (status == PackageManager.PERMISSION_GRANTED) {
            Location location = mLocationManager.getLastKnownLocation(provider);
            updateWithNewLocation(location);
            //  mLocationManager.addGpsStatusListener(this);
            long minTime = 5000;// ms
            float minDist = 5.0f;// meter
            mLocationManager.requestLocationUpdates(provider, minTime, minDist,
                    this);
        }
    }


    private boolean isProviderAvailable() {
        mLocationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        provider = mLocationManager.getBestProvider(criteria, true);
        if (mLocationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

            return true;
        }

        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
            return true;
        }

        if (provider != null) {
            return true;
        }
        return false;
    }

    private void updateWithNewLocation(Location location) {

        if (location != null && provider != null) {
            double lng = location.getLongitude();
            double lat = location.getLatitude();

            addBoundaryToCurrentPosition(lat, lng);

            CameraPosition camPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lng)).zoom(10f).build();

            if (mMap != null)
                mMap.animateCamera(CameraUpdateFactory

                        .newCameraPosition(camPosition));
        } else {
            Log.d("Location error", "Something went wrong");
        }
    }


    private void addBoundaryToCurrentPosition(double lat, double lang) {

        MarkerOptions mMarkerOptions = new MarkerOptions();
        mMarkerOptions.position(new LatLng(lat, lang));
        mMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMarkerOptions.title("Posisi Anda");
        mMarkerOptions.anchor(0.5f, 0.5f);

        CircleOptions mOptions = new CircleOptions()
                .center(new LatLng(lat, lang)).radius(10000)
                .strokeColor(0x110000FF).strokeWidth(1).fillColor(0x110000FF);
        mMap.addCircle(mOptions);
        if (mCurrentPosition != null)
            mCurrentPosition.remove();
        mCurrentPosition = mMap.addMarker(mMarkerOptions);
        mMap.addMarker(new MarkerOptions()
                .position(latLngAr)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Pulau Cinta")
                .snippet("Kecamatan Tilamuta, Kabupaten Boalemo, Gorontalo"));

        mMap.addMarker(new MarkerOptions()
                .position(latLngDt)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Pulau Saronde")
                .snippet("Kecamatan Kwandang Kabupaten Gorontalo Utara"));

        mMap.addMarker(new MarkerOptions()
                .position(latLngf2)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Pulau Diyonumo")
                .snippet("Desa Deme, Sumalata, Kabupaten Gorontalo."));

        mMap.addMarker(new MarkerOptions()
                .position(latLngGs)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Teluk Tomini")
                .snippet("Jl.Bonuo Ulapato A, Telaga, kabupaten Gorontalo."));

        mMap.addMarker(new MarkerOptions()
                .position(latLngGr)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Taman Laut Olele")
                .snippet("Poduoma, Suwawa Timur, Bone Bolango, Gorontalo"));

        mMap.addMarker(new MarkerOptions()
                .position(latLngJg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Pantai Bolihutuo")
                .snippet("Jl.Trans Sulawesi, Bolihutuo, Boalemo, Gorontalo"));

        mMap.addMarker(new MarkerOptions()
                .position(latLngLv)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Pantai Tenilo")
                .snippet("Tilamuta, Kabupaten Boalemo, Gorontalo."));

        mMap.addMarker(new MarkerOptions()
                .position(latLngLo)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Danau Limboto")
                .snippet("Limboto, kabupaten Gorontalo, Gorontalo."));

        mMap.addMarker(new MarkerOptions()
                .position(latLngOa)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Masjid Walima Emas")
                .snippet("Bongo, kecamatan Batudaa Pantai, Gorontalo"));

        mMap.addMarker(new MarkerOptions()
                .position(latLngGu)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Desa Religi Bongo")
                .snippet("Bongo,Kecamatan Batuda’a Pantai, Gorontalo"));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent;
                //  Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
//                intent = new Intent(getApplicationContext(), Detail_maps.class);
//                intent.putExtra("nama",marker.getTitle());
//                startActivity(intent);

            }
        });

    }


    @Override
    public void onLocationChanged(Location location) {

        updateWithNewLocation(location);
    }

    @Override
    public void onProviderDisabled(String provider) {

        updateWithNewLocation(null);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
            case LocationProvider.AVAILABLE:
                break;
        }
    }

    public void bluetoothScanning() {

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getApplicationContext().registerReceiver(mReceiver, filter);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

    }

    public void startService() {


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
//                Toast.makeText(getApplicationContext(),"Device Name: " + "device " + deviceName,Toast.LENGTH_SHORT).show();
                scheduleNotification(getNotification(" Ditemukan. Kamu berada di " + deviceName ), 1000);
                Log.d("mac address", deviceHardwareAddress);
                if (deviceHardwareAddress.equals("E0:50:E4:E6:6E:Q2")) {
                    content.setVisibility(View.VISIBLE);

                } else {
                    //   img.setImageResource(R.drawable.jerapah);
                    content.setVisibility(View.VISIBLE);

                }

            }
        }
    };

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setContentTitle("Pemberitahuan");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId("10001");
        return builder.build();
    }
}