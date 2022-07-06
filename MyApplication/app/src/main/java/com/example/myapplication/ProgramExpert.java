package com.example.myapplication;

import android.widget.Button;

import com.google.android.material.bottomappbar.BottomAppBar;

public class ProgramExpert {
    public String getLanguage(String feature) {
        String result;
        switch (feature) {
            case "fast":
                result = "C/C++";break;
            case "easy":
                result = "Python";break;
            case "new":
                result = "Kotlin";break;
            case "00":
                result = "Java";break;
            case "sjw":
                result=feature+"good";
                break;
            default:
                result = "You got me";
        }

        return result;
    }
    public float setButtonalpha(){
        return (float) ((1+Math.random()*(10-1+1))/10);
    }

}
