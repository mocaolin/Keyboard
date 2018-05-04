package com.jkt.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SpecialEditText.OnSpecialEditTextListener, NumberInputView.OnNumberInputViewListener, CircleEditText.OnCircleEditTextListener {

    private SpecialEditText mSpecialEditText;
    private CircleEditText mCircleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpecialEditText = (SpecialEditText) findViewById(R.id.edit);
        mSpecialEditText.setListener(this);
        NumberInputView inputView = (NumberInputView) findViewById(R.id.input);
        inputView.setListener(this);
        mCircleEditText = (CircleEditText) findViewById(R.id.edit1);
        mCircleEditText.setListener(this);
    }

    @Override
    public void OnSpecialEditTextComplete(SpecialEditText editText, String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        editText.setText("");
    }

    @Override
    public void OnCircleEditTextComplete(CircleEditText editText, String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        editText.setText("");
    }
    @Override
    public void onNumberClick(NumberInputView view, int num) {
        String s = mSpecialEditText.getText().toString();
        s += num;
        mSpecialEditText.setText(s);
    }

    @Override
    public void onClearClick(NumberInputView view) {
        mSpecialEditText.setText("");
    }

    @Override
    public void onBackwardClick(NumberInputView view) {
        String s = mSpecialEditText.getText().toString();
        if (s.length()==0) {
            return;
        }
        String substring = s.substring(0, s.length() - 1);
        mSpecialEditText.setText(substring);
    }

}
