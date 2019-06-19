package com.helloworld;

import android.content.Intent;
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


    final String path = "http://10.149.182.68:8080/img/bg2.jpg";

    private int skipTime = 5;

    /**
     * 实现界面更新
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    skip.setText("点击跳过 "+skipTime);
                    break;
                }
                case 1:{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    SplashActivity.this.finish();
                }
            }
            super.handleMessage(msg);
        }
    };


    private Handler handler1 = new Handler();

    private Timer timer = new Timer(true);

    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.spalsh_page);

        skip = findViewById(R.id.skip);

        MyImageView myImageView = findViewById(R.id.background);
        myImageView.setImageURL(path);

        /**
         * 实现延时跳转
         */
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                SplashActivity.this.finish();
            }
        },5000);


        /**
         * 实现点击跳转
         */
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
                timer.cancel();
            }
        });

        /**
         * 计时器配合handler实现跳转和界面更新
         */
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 skipTime -- ;
                 handler.sendEmptyMessage(skipTime == 0 ? 1 : 0 );
             }
         },1000,1000);

    }

}

