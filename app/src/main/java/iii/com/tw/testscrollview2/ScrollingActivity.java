package iii.com.tw.testscrollview2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {
    //變數區**
    private  int[] resId;
    private  int count=0; //当前图片的序号
    private GestureDetector gestureDetector;  //手势监听器对象
    private ImageView iv;//保存imageView的对象
    //變數區**

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }

    private void init() {

          resId=new  int[]{ R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4}; //图片的资源数组
        //**
        iv= (ImageView)findViewById(R.id.imgDog);//取得图片控件
        gestureDetector=new  GestureDetector(this,onGestureListener);  //手势监听器的处理效果   由onGestureListener来处理
        //**
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return  gestureDetector.onTouchEvent(event);//当前Activity被触摸时回调
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
// TODO Auto-generated method stub
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private  GestureDetector.OnGestureListener onGestureListener
            =new  GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent startEvent, MotionEvent endEvent,
                               float velocityX, float velocityY){

            //Toast.makeText(ScrollingActivity.this,"yes",Toast.LENGTH_SHORT).show();

            //得到滑动手势的起始和终点的X.Y坐标，并进行计算
/*
            float x= e2.getX()-e1.getX();

            //通过计算结果判断用户是向左滑动或者向右滑动
            if (x>0&&count!=5){
                count++;
            }else  if(x<0&&count!=0){
                count--;
            }
            iv.setImageResource(resId[count]);

           */

            //
            if (startEvent.getY() - endEvent.getY() > 100) {
                //Toast.makeText(ScrollingActivity.this, "手势向上滑动", Toast.LENGTH_SHORT).show();
                return true;
            } else if (startEvent.getY() - endEvent.getY() < -100) {
                //Toast.makeText(ScrollingActivity.this, "手势向下滑动", Toast.LENGTH_SHORT).show();
                return true;
            } else if (startEvent.getX() - endEvent.getX() > 100) {
                //Toast.makeText(ScrollingActivity.this, "手势向左滑动", Toast.LENGTH_SHORT).show();
                if(count<resId.length-1)
                count+=1;

               // return true;
            } else if (startEvent.getX() - endEvent.getX() < -100) {
                //Toast.makeText(ScrollingActivity.this, "手势向右滑动", Toast.LENGTH_SHORT).show();
                if(count>0)
                count-=1;
                //return true;
            }

            //
            iv.setImageResource(resId[count]);

            return true;

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
