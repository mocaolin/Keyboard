package com.jkt.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.borderStyle:
                startActivity(new Intent(this, BorderActivity.class));
                break;
            case R.id.circleStyle:
                startActivity(new Intent(this, CircleActivity.class));
                break;
        }
    }
}
