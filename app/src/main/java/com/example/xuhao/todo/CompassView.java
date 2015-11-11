package com.example.xuhao.todo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuhao on 2015/11/11.
 */
public class CompassView extends View {
    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setFocusable(true);
    }

    private float bearing;

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measure(widthMeasureSpec);
        int measureHeight = measure(heightMeasureSpec);

        int minValue = Math.min(measureWidth,measureHeight);

        setMeasuredDimension(minValue,minValue);
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED){
            result = 200;
        }else{
            result = specSize;
        }
        return result;
    }
}
