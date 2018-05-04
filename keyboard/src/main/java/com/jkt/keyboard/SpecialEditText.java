package com.jkt.keyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

/**
 * Created by Allen on 2018/5/4/004.
 */

public class SpecialEditText extends android.support.v7.widget.AppCompatEditText {
    private Context mContext;
    private Paint mBorderPaint;
    private Paint mIntervalPaint;
    private Paint mCirclePaint;
    private int mNum;
    private int mLength;
    private float mCircleRadius;
    private float mBorderAngle;

    public SpecialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaints();
        initAttrs(attrs);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mNum)});
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setBackgroundDrawable(null);
    }

    private void initPaints() {
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIntervalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.special, 0, 0);
        int borderColor = typedArray.getColor(R.styleable.special_border_color, mContext.getResources().getColor(R.color.border_color));
        int intervalColor = typedArray.getColor(R.styleable.special_interval_color, mContext.getResources().getColor(R.color.interval_color));
        int circleColor = typedArray.getColor(R.styleable.special_circle_color, mContext.getResources().getColor(R.color.circle_color));
        float borderWidth = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.special_border_width, 3));
        mBorderAngle = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.special_border_angle, 10));

        float intervalWidth = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.special_interval_width, 3));
        mCircleRadius = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.special_circle_radius, 5));

        mNum = typedArray.getInteger(R.styleable.special_item_num, 6);

        mBorderPaint.setColor(borderColor);
        mIntervalPaint.setColor(intervalColor);
        mCirclePaint.setColor(circleColor);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mIntervalPaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mBorderPaint.setStrokeWidth(borderWidth);
        mIntervalPaint.setStrokeWidth(intervalWidth);


        typedArray.recycle();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画边框
        int width = getWidth();
        int height = getHeight();
        RectF rect = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rect, mBorderAngle, mBorderAngle, mBorderPaint);
        //画间隔线
        int itemWidth = getWidth() / mNum;
        for (int i = 1; i < mNum; i++) {
            int offsetX = itemWidth * i;
            canvas.drawLine(offsetX, 0, offsetX, height, mIntervalPaint);
        }
        //画实心圆
        for (int i = 0; i < mLength; i++) {
            float circleX = (float) (itemWidth * i + itemWidth * 0.5);
            float circleY = (float) (getHeight() * 0.5);
            canvas.drawCircle(circleX, circleY, mCircleRadius, mCirclePaint);
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mLength = text.toString().length();
        invalidate();
        if (mListener != null) {
            if (mLength == mNum) {
                mListener.OnSpecialEditTextComplete(this, text.toString());
            }
        }
    }

    public interface OnSpecialEditTextListener {
        void OnSpecialEditTextComplete(SpecialEditText editText, String text);
    }

    public OnSpecialEditTextListener mListener;

    public void setListener(OnSpecialEditTextListener listener) {
        mListener = listener;
    }
}
