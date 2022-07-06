package com.example.is1901.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_check(View view) {
        TextView textView = findViewById(R.id.Return);
        TextView NameView = findViewById(R.id.Nametext);
        TextView PasswordView = findViewById(R.id.Password_text);
        String Name =NameView.getText().toString();
        String Password =PasswordView.getText().toString();
        if((Name.equals(Password))){
            textView.setText("True");
        }
        else {
            textView.setText("False");
        }
    }
}