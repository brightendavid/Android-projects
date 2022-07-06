package com.example.life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("message","onCreate");
        if (savedInstanceState!=null){
            TextView textView =findViewById(R.id.time_view);
            String str= savedInstanceState.getString("string");
            textView.setText(str);
        }

    }
    protected void onStart() {
        super.onStart();
        Log.d("message","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("message","onRestart");
    }

    protected void onStop() {
        super.onStop();
        Log.d("message","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message","onDestroy");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("message","onPostResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("message","onPause");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("message","onBackPressed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message","onResume");
    }


    public void onclick_change(View view) {
        TextView textView =findViewById(R.id.time_view);
        EditText editText =findViewById(R.id.editTextTextPersonName);
        String str = editText.getText().toString();
        textView.setText(str);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView textView =findViewById(R.id.time_view);
        String str = textView.getText().toString();
        savedInstanceState.putString("string",str);
    }
}