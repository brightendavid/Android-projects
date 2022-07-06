package com.example.is1901.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class NewAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_acitvity);
        //使用定时器
        new Timer().schedule(new TimerTask() {
            public void run() {
                Intent intent =new Intent(NewAcitvity.this,MainActivity.class);
                startActivity(intent);
            }
        }, 1500);
        //这里停留时间为1000=1s。
    }
}