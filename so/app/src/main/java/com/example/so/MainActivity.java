package com.example.so;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.so.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'so' library on application startup.
    static {
        System.loadLibrary("so");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.label;
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'so' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    public native int addInt(int a,int b);

    public void onClick_button(View view) {
        // check the password and name
        EditText nameview = findViewById(R.id.Name);
        String name = nameview.getText().toString();
        EditText passwordview =findViewById(R.id.Password);
        String password  = passwordview.getText().toString();
        TextView result_view = findViewById(R.id.ResultView);
        String Success_symbol ="SUCCESS";
        String Falue_symbol ="Falue";



        try {
//            int addIntResult = addInt(Integer.parseInt(name),Integer.parseInt(password)); //check the password
            String Result = xorString(name,password);
            if(Result.equals("aaabbb")){
                result_view.setText(Success_symbol);
            }
            else {
                result_view.setText(Falue_symbol);
            }
        }catch (Exception e){
            String errorMessage = "Cannot Empty";
            result_view.setText(errorMessage);
        }

    }
    public native String addString(String a,String b);// string + string
    public native String xorString(String a,String b);// xor string +string

}