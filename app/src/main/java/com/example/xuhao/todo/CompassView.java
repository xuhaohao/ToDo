package com.example.xuhao.todo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

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

    private void init() {
        this.setFocusable(true);

        Resources r = getResources();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(r.getColor(R.color.background_color));
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        strNorth = r.getString(R.string.cardinal_north);
        strSouth = r.getString(R.string.cardinal_south);
        strEast = r.getString(R.string.cardinal_east);
        strWest = r.getString(R.string.cardinal_west);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(r.getColor(R.color.text_color));

        textHeight = (int) textPaint.measureText("yY");

        markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        markerPaint.setColor(r.getColor(R.color.background_color));
    }

    private float bearing;

    private Paint markerPaint;
    private Paint textPaint;
    private Paint circlePaint;
    private int textHeight;
    private String strNorth;
    private String strSouth;
    private String strEast;
    private String strWest;


    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measure(widthMeasureSpec);
        int measureHeight = measure(heightMeasureSpec);

        int minValue = Math.min(measureWidth, measureHeight);

        setMeasuredDimension(minValue, minValue);
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 200;
        } else {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int px = width / 2;
        int py = height / 2;

        int radius = Math.min(px, py);

        canvas.drawCircle(px, py, radius, circlePaint);
        canvas.save();
        canvas.rotate(-bearing, px, py);

        int textWidth = (int) textPaint.measureText("W");
        int cardinalX = px - textWidth / 2;
        int cardinalY = py - radius + textHeight;

        for (int i = 0; i < 24; i++) {
            canvas.drawLine(px, py - radius, px, py - radius + 10, markerPaint);

            canvas.save();
            canvas.translate(0,textHeight);

           if (i % 6 == 0){
               String dirString = "";
               switch(i){
                   case(0):{
                       dirString = strNorth;
                       int arrowY = 2*textHeight;
                       canvas.drawLine(px,arrowY,px-5,3*textHeight,markerPaint);
                       canvas.drawLine(px,arrowY,px+5,3*textHeight,markerPaint);
                       break;
                   }
                   case(6):dirString = strEast;break;
                   case(12):dirString = strSouth;break;
                   case(18):dirString = strWest;break;
               }
            canvas.drawText(dirString,cardinalX,cardinalY,textPaint);
           }
            else if(i % 3 == 0){
               String angle = String.valueOf(i*15);
               float angleTextWidth = textPaint.measureText(angle);

               int angleTextX = (int)(px-angleTextWidth/2);
               int angleTextY = py-radius+textHeight;
               canvas.drawText(angle,angleTextX,angleTextY,textPaint);
           }
            canvas.restore();

            canvas.rotate(15,px,py);
        }
        canvas.restore();
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        if (isShown()){
            String bearingStr = String.valueOf(bearing);
            if (bearingStr.length() > AccessibilityEvent.MAX_TEXT_LENGTH){
                bearingStr = bearingStr.substring(0,AccessibilityEvent.MAX_TEXT_LENGTH);

                event.getText().add(bearingStr);
                return true;
            }
        }
        return false;
    }
}
