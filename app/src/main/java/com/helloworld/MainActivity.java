package com.helloworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void startAction(Context context,String data){

        Intent intent = new Intent(context,MainActivity.class);

        //intent.putExtra("0",data);

        context.startActivity(intent);

    }

}
