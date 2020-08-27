package com.example.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pojoarraylistexample.R;

public class Home_Page extends AppCompatActivity {

    Button fused_id,location_update_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
       getSupportActionBar().hide();
        fused_id=findViewById(R.id.fused_id);
        location_update_id=findViewById(R.id.location_update_id);




        fused_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Page.this,Fused_Location.class);
                startActivity(intent);
            }
        });
        location_update_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Page.this, LocationUpdate.class);
                startActivity(intent);
            }
        });
    }
}