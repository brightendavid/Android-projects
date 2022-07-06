package com.example.messager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class CreateMessageActivity extends AppCompatActivity {
    public static String MESSAGE_KEY="YOUR MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void onSendMessage(android.view.View button){
        EditText editText=findViewById(R.id.hint);
        String message=editText.getText().toString();
        Intent intent =new Intent(this,ReceiveMessageActivity.class); // APP 内传输
        intent.putExtra (MESSAGE_KEY, message); //APP 内传输
        startActivity(intent);

//        ImageView  imageView =findViewById(R.id.imageView);
//        Intent intent =new Intent(Intent.ACTION_SEND); // APP 之间传输  分享功能实现
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT,message);
//        String chooseTitle=getString(R.string.choose);
//        Intent chosenIntent = Intent.createChooser(intent,chooseTitle);
//        startActivity(chosenIntent);

        //   隐式
//        Intent intent=new Intent("come.example.activitytest.ACTION_START"); // 隐式传输 和19行语句可以替换
//        EditText editText=findViewById(R.id.hint);
//        String message=editText.getText().toString();
//        intent.putExtra (MESSAGE_KEY, message); //APP 内传输
//        startActivity(intent);


//        // app 外 显式  分享图片
//        ImageView  imageView =findViewById(R.id.imageView);
//        Intent intent =new Intent(Intent.ACTION_SEND); // APP 之间传输  分享功能实现
//        String  imgPath ="C:\\Users\\brighten\\Desktop\\imgs\\1.png";
//        File f = new File(imgPath);
//        if (f.exists() && f.isFile()) {
//            intent.setType("image/png");
//            Uri u = Uri.fromFile(f);
//            intent.putExtra(Intent.EXTRA_STREAM, u);
//        }
//        String ss=f.getName();
//        EditText editText = findViewById(R.id.hint);
//        editText.setText(ss);
//
//        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.music)); // 有初始图片不显示的问题，更改图像可以显示；
//        // 更改图像时，不会将原本图像替换，而是在原图下方显示，结果为两张图
//        String chooseTitle=getString(R.string.choose);
//        Intent chosenIntent = Intent.createChooser(intent,chooseTitle);
//        startActivity(chosenIntent);
    }
}