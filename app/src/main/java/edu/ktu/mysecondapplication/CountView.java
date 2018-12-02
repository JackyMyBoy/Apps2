package edu.ktu.mysecondapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class CountView extends View{

    public CountView (Context context){
        super(context);
    }
    public CountView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public CountView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs,defStyleAttr);
    }
    ArrayList<ModelPost> publication= new ArrayList<>();
    static String Count="";

    public static void setString(String count){
        Count=count;
    }
    //String ct = Count;

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(height/2,width/2,150,paint);
        paint.setColor(Color.WHITE);
        float textsize = 200f;
        paint.setTextSize(textsize);
        paint.setTextAlign(Paint.Align.CENTER);
        int xPos = (width / 2);
        int yPos = (int) ((height / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawText(Count, xPos, yPos, paint);
    }
}
