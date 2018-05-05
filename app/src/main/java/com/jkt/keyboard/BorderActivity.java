package com.jkt.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jkt.keyboardlib.BorderEditText;
import com.jkt.keyboardlib.NumberInputView;

public class BorderActivity extends AppCompatActivity {

    private BorderEditText mBorderEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_border);
        mBorderEditText = (BorderEditText) findViewById(R.id.edit);
        mBorderEditText.setListener(new BorderEditText.OnBorderEditTextListener() {
            @Override
            public void OnBorderEditTextComplete(BorderEditText editText, String text) {
                Toast.makeText(BorderActivity.this, text, Toast.LENGTH_SHORT).show();
                mBorderEditText.setText("");
            }
        });
        NumberInputView numberInputView = (NumberInputView) findViewById(R.id.input);
        numberInputView.setListener(new NumberInputView.OnNumberInputViewListener() {
            @Override
            public void onNumberClick(NumberInputView view, int num) {
                //mBorderEditText 字符串长度+1
                String s = mBorderEditText.getText().toString();
                s += num;
                mBorderEditText.setText(s);
            }

            @Override
            public void onClearClick(NumberInputView view) {
                mBorderEditText.setText("");
            }

            @Override
            public void onBackwardClick(NumberInputView view) {
                //mBorderEditText 字符串长度-1
                String s = mBorderEditText.getText().toString();
                if (s.length() == 0) {
                    return;
                }
                String substring = s.substring(0, s.length() - 1);
                mBorderEditText.setText(substring);
            }
        });
    }

}
