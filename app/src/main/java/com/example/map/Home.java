package com.example.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojoarraylistexample.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class Home extends AppCompatActivity {



    AlertDialog.Builder builder;
    int LOCATION_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static final String TAG = "Issues";
    EditText textView;
    String loc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        textView = findViewById(R.id.displayd);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Home.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
//            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {

            askPermission();
        }
    }


    public void askPermission() {
        if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Permission Required")
                        .setMessage("Permission is mandatory Kindly allow")
                        .create();
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Home.this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                        }, LOCATION_CODE);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
            ActivityCompat.requestPermissions(Home.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_CODE);


        }

    }

    public void getLocation() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    Toast.makeText(Home.this, ""+location.getLatitude(), Toast.LENGTH_SHORT).show();
                   loc=loc+"Lati:"+location.getLatitude()+"\n"+"Longitude:"+location.getLongitude()+"\n"+"Provider:"+location.getProvider()+"\n"+"Speed:"+location.getSpeed()+"\n"+"Time:"+location.getTime()+"\n"+"Accuracy:"+location.getAccuracy();
                    textView.setText(loc);

                }
                else {
                    Toast.makeText(Home.this, "Loc null" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Home.this, " "+e, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "" + e);
            }
        });


    }


}


