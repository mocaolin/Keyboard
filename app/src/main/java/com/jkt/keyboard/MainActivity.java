package com.jkt.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SpecialEditText.OnSpecialEditTextCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpecialEditText specialEditText = (SpecialEditText) findViewById(R.id.edit);
        specialEditText.setCallback(this);
    }

    @Override
    public void OnSpecialEditTextComplete(SpecialEditText editText, String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        editText.setText("");
    }
}
