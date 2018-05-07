package com.jkt.keyboardlib;

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

public class BorderEditText extends android.support.v7.widget.AppCompatEditText {
    private Context mContext;
    private Paint mBorderPaint;
    private Paint mIntervalPaint;
    private Paint mCirclePaint;
    private int mNum;
    private int mLength;
    private float mCircleRadius;
    private float mBorderAngle;
    private Paint mItemPaint;
    private float mBorderWidth;

    public BorderEditText(Context context, AttributeSet attrs) {
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
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mItemPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIntervalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.border, 0, 0);
        //外边框相关
        int borderColor = typedArray.getColor(R.styleable.border_border_color, mContext.getResources().getColor(R.color.border_color));
        mBorderAngle = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.border_border_angle, 10));
        mBorderWidth = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.border_border_width, 1));
        //item颜色
        int itemColor = typedArray.getColor(R.styleable.border_border_color, mContext.getResources().getColor(R.color.withe));
        //间隔线相关
        int intervalColor = typedArray.getColor(R.styleable.border_interval_color, mContext.getResources().getColor(R.color.interval_color));
        float intervalWidth = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.border_interval_width, 1));
        //实心圆相关
        int circleColor = typedArray.getColor(R.styleable.border_circle_color, mContext.getResources().getColor(R.color.circle_color));
        mCircleRadius = DensityUtil.dp2px(mContext, typedArray.getDimension(R.styleable.border_circle_radius, 5));
        //num个数
        mNum = typedArray.getInteger(R.styleable.border_item_num, 6);


        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStyle(Paint.Style.FILL);

        mItemPaint.setColor(itemColor);
        mItemPaint.setStyle(Paint.Style.FILL);

        mIntervalPaint.setColor(intervalColor);
        mIntervalPaint.setStyle(Paint.Style.STROKE);
        mIntervalPaint.setStrokeWidth(intervalWidth);

        mCirclePaint.setColor(circleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        typedArray.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画边框
        int width = getWidth();
        int height = getHeight();
        RectF borderRectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(borderRectF, mBorderAngle, mBorderAngle, mBorderPaint);
        //画item
        RectF itemRectF = new RectF(mBorderWidth, mBorderWidth, width - mBorderWidth, height - mBorderWidth);
        canvas.drawRoundRect(itemRectF, mBorderAngle, mBorderAngle, mItemPaint);
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
                mListener.OnBorderEditTextComplete(this, text.toString());
            }
        }
    }

    public void clear() {
        setText("");
    }

    public interface OnBorderEditTextListener {
        void OnBorderEditTextComplete(BorderEditText editText, String text);
    }

    public OnBorderEditTextListener mListener;

    public void setListener(OnBorderEditTextListener listener) {
        mListener = listener;
    }
}
