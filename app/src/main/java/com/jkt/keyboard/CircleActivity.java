package com.jkt.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jkt.keyboardlib.CircleEditText;
import com.jkt.keyboardlib.NumberInputView;

public class CircleActivity extends AppCompatActivity {

    private CircleEditText mCircleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        mCircleEditText = (CircleEditText) findViewById(R.id.edit1);
        mCircleEditText.setListener(new CircleEditText.OnCircleEditTextListener() {
            @Override
            public void OnCircleEditTextComplete(CircleEditText editText, String text) {
                Toast.makeText(CircleActivity.this, text, Toast.LENGTH_SHORT).show();
                mCircleEditText.setText("");
            }
        });
        NumberInputView inputView = (NumberInputView) findViewById(R.id.input);
        inputView.setListener(new NumberInputView.OnNumberInputViewListener() {
            @Override
            public void onNumberClick(NumberInputView view, int num) {
                //mBorderEditText 字符串长度+1
                String s = mCircleEditText.getText().toString();
                s += num;
                mCircleEditText.setText(s);
            }

            @Override
            public void onClearClick(NumberInputView view) {
                mCircleEditText.setText("");
            }

            @Override
            public void onBackwardClick(NumberInputView view) {
                //mBorderEditText 字符串长度-1
                String s = mCircleEditText.getText().toString();
                if (s.length() == 0) {
                    return;
                }
                String substring = s.substring(0, s.length() - 1);
                mCircleEditText.setText(substring);
            }
        });
    }
}
