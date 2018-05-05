package com.jkt.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
                if (text.equals("123456")) {
                    Toast.makeText(CircleActivity.this, " success " + text, Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(CircleActivity.this, " error " + text, Toast.LENGTH_SHORT).show();
                // 使用错误动画 与下面clear 二选一
                errorAnim();
//                mCircleEditText.clear();

            }

        });
        NumberInputView inputView = (NumberInputView) findViewById(R.id.input);
        inputView.setListener(new NumberInputView.OnNumberInputViewListener() {
            @Override
            public void onNumberClick(NumberInputView view, int num) {
                //mBorderEditText 字符串长度+1
                if (!mCircleEditText.isEnabled()) {
                    return;
                }
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

    private void errorAnim() {
        Animation animation = AnimationUtils.loadAnimation(
                this, R.anim.shake);
        mCircleEditText.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mCircleEditText.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCircleEditText.clear();
                mCircleEditText.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

}
