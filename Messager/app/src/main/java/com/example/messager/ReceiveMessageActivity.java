package com.example.messager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent =getIntent();
        String message =intent.getStringExtra(CreateMessageActivity.MESSAGE_KEY);
        TextView textView=findViewById(R.id.output);
        textView.setText(message);


        //接收的activity
//        ImageView imageView =findViewById(R.id.imageView3);
//        Intent intent = getIntent();
//        if (intent  != null &&  intent.getParcelableExtra("BITMAP") != null) {
//            Bitmap bitmap = (Bitmap)getIntent().getParcelableExtra("BITMAP");
//            imageView.setImageBitmap(bitmap);

//        }

    }
}