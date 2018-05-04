package com.jkt.keyboard;

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
    private View mInflate;
    private View[] mViews;

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
        initListeners();

    }


    private void initViews() {
        mInflate = LayoutInflater.from(mContext).inflate(R.layout.number_input_view, this, true);
        View zero = mInflate.findViewById(R.id.zero);
        View one = mInflate.findViewById(R.id.one);
        View two = mInflate.findViewById(R.id.two);
        View three = mInflate.findViewById(R.id.three);
        View four = mInflate.findViewById(R.id.four);
        View five = mInflate.findViewById(R.id.five);
        View six = mInflate.findViewById(R.id.six);
        View seven = mInflate.findViewById(R.id.seven);
        View eight = mInflate.findViewById(R.id.eight);
        View nine = mInflate.findViewById(R.id.nine);
        View clear = mInflate.findViewById(R.id.clear);
        View delete = mInflate.findViewById(R.id.backward);
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
