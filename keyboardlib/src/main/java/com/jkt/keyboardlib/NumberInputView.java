package com.jkt.keyboardlib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Allen on 2018/5/4/004.
 */

public class NumberInputView extends LinearLayout {

    private Context mContext;
    private View[] mViews;

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
        initListeners();

    }


    private void initViews() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.number_input_view, this, true);
        View zero = inflate.findViewById(R.id.zero);
        View one = inflate.findViewById(R.id.one);
        View two = inflate.findViewById(R.id.two);
        View three = inflate.findViewById(R.id.three);
        View four = inflate.findViewById(R.id.four);
        View five = inflate.findViewById(R.id.five);
        View six = inflate.findViewById(R.id.six);
        View seven = inflate.findViewById(R.id.seven);
        View eight = inflate.findViewById(R.id.eight);
        View nine = inflate.findViewById(R.id.nine);
        View clear = inflate.findViewById(R.id.clear);
        View delete = inflate.findViewById(R.id.backward);
        mViews = new View[]{zero, one, two, three, four, five, six, seven, eight, nine, clear, delete};
    }

    private void initListeners() {
        for (int i = 0; i < mViews.length; i++) {
            final int finalI = i;
            mViews[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener == null) {
                        return;
                    }
                    if (finalI == 10) {
                        mListener.onClearClick(NumberInputView.this);
                        return;
                    }
                    if (finalI == 11) {
                        mListener.onBackwardClick(NumberInputView.this);
                        return;
                    }
                    mListener.onNumberClick(NumberInputView.this, finalI);

                }
            });
        }
    }

    public interface OnNumberInputViewListener {
        void onNumberClick(NumberInputView view, int num);

        void onClearClick(NumberInputView view);

        void onBackwardClick(NumberInputView view);
    }

    public OnNumberInputViewListener mListener;

    public void setListener(OnNumberInputViewListener listener) {
        mListener = listener;
    }
}
