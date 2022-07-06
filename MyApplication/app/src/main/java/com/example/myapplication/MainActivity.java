// 2022/2/23  移动安全教材 第一章   按钮交互
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private final  ProgramExpert export =new ProgramExpert(); // 类的实例化 export
    public void onClickButton(View Button){  // Button，onClickButton和按钮的onclick方法
        Spinner spinner =findViewById(R.id.feature); // findViewById()方法，查找子布局,通过id查找，id为人为设置项目
        Button button=findViewById(R.id.find_language);
        String feature =spinner.getSelectedItem().toString(); //获取spinner中的文本
        String language=export.getLanguage(feature); // 通过getLanguage方法 得到输出
        if(feature.equals("sjw")){
            button.setText("OVER");
        }
        else{
            button.setText("FIND LANGUAGE");
        }
        float t=export.setButtonalpha();
        button.setAlpha(t);
        TextView textView =findViewById(R.id.language); // 返回项为view,要求和定义数据类型相同,拿到它的引用
        textView.setText(language);

        TextView textView2 =findViewById(R.id.show); // 返回项为view,要求和定义数据类型相同,拿到它的引用

        ImageView imageV=findViewById(R.id.imageView);
        if(t<0.5){
            imageV.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
            textView2.setText("you click me ");
        }
        else {
            imageV.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
            textView2.setText("do not click");
        }

    }
//    通过id查找view
//    public void onClickButton1(View Button){
//        Spinner spinner =findViewById(R.id.feature);
//        String feature =spinner.getSelectedItem().toString();
//        String language =export.getLanguage(feature);
//        TextView textView=findViewById(R.id.language);
//        textView.setText(language);
//
// todo 写小作文

}