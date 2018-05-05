package com.jkt.keyboardlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

/**
 * Created by Allen on 2018/5/4/004.
 */

public class CircleEditText extends android.support.v7.widget.AppCompatEditText {

    private Context mContext;
    private Paint mSelectorPaint;
    private Paint mUnSelectorPaint;
    private int mNum;
    private int mLength;
    private float mCircleRadius;

    public CircleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaints();
        initAttrs(attrs);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mNum)});
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setBackgroundDrawable(null);
        setFocusable(false);

    }

    private void initPaints() {
        mSelectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnSelectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.special, 0, 0);
        int selectorColor = typedArray.getColor(R.styleable.circle_circle_selector_color, mContext.getResources().getColor(R.color.selector_color));
        int unSelectorColor = typedArray.getColor(R.styleable.circle_circle_un_selector_color, mContext.getResources().getColor(R.color.un_selector_color));

        mCircleRadius = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.circle_circle_circle_radius, 10));

        mNum = typedArray.getInteger(R.styleable.circle_circle_item_num, 6);

        mSelectorPaint.setColor(selectorColor);
        mSelectorPaint.setStyle(Paint.Style.FILL);

        mUnSelectorPaint.setColor(unSelectorColor);
        mUnSelectorPaint.setStyle(Paint.Style.FILL);

        typedArray.recycle();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        int itemWidth = getWidth() / mNum;
        for (int i = 0; i < mNum; i++) {
            float circleX = (float) (itemWidth * i + itemWidth * 0.5);
            float circleY = (float) (getHeight() * 0.5);
            if (i < mLength) {
                canvas.drawCircle(circleX, circleY, mCircleRadius, mSelectorPaint);
            } else {
                canvas.drawCircle(circleX, circleY, mCircleRadius, mUnSelectorPaint);
            }
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mLength = text.toString().length();
        invalidate();
        if (mListener != null) {
            if (mLength == mNum) {
                mListener.OnCircleEditTextComplete(this, text.toString());
            }
        }
    }

    public void clear() {
        setText("");
    }

    public interface OnCircleEditTextListener {
        void OnCircleEditTextComplete(CircleEditText editText, String text);
    }

    public OnCircleEditTextListener mListener;

    public void setListener(OnCircleEditTextListener listener) {
        mListener = listener;
    }
}
