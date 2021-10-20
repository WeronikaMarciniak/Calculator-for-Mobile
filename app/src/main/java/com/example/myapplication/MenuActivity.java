package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MenuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void moveToSimpleCalc(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void moveToAdvCalc(View view) {
        Intent intent = new Intent(this, AdvActivity.class);
        startActivity(intent);
    }

    public void moveToExit(View view) {
     finish();
    }
}
