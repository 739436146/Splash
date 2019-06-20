package com.helloworld;

import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.Utils.MyImageView;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {


    final String path = "http://10.134.141.194:8080/img/bg2.jpg";

    private int skipTime = 6;

    private Runnable runnable = null;

    /**
     * 实现界面更新
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    skip.setText("点击跳过  "+skipTime);
                    break;
                }
                case 1:{
                    MainActivity.startAction(SplashActivity.this,null);
                    SplashActivity.this.finish();
                }
            }
            super.handleMessage(msg);
        }
    };


    private Timer timer = new Timer(true);

    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.spalsh_page);

        skip = findViewById(R.id.skip);

        MyImageView myImageView = findViewById(R.id.splashimg);
        myImageView.setImageURL(path);

        /**
         * 实现延时跳转
         */

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                MainActivity.startAction(SplashActivity.this,null);
                SplashActivity.this.finish();
            }
        },5000);


        /**
         * 实现点击跳转
         */
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startAction(SplashActivity.this,null);
                finish();
            }
        });


        /**
         * 计时器配合handler实现跳转和界面更新
         */
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 skipTime -- ;
                 handler.sendEmptyMessage( 0 );
             }
         },1000,1000);

    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        handler.removeCallbacksAndMessages(null);
        timer.cancel();
        super.onDestroy();
    }
}

