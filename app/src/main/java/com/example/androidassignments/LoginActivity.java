package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {



    protected static final String ACTIVITY_NAME = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences mPrefs = getSharedPreferences("my_shared_preferences", MODE_PRIVATE);

        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        Log.i(ACTIVITY_NAME, "In onResume");
        super.onResume();
    }

    protected void onStart() {
        Log.i(ACTIVITY_NAME, "In onStart");
        super.onStart();
    }

    protected void onPause() {
        Log.i(ACTIVITY_NAME, "In onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.i(ACTIVITY_NAME, "In onStop");
        super.onStop();
    }

    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "In onDestroy");
        super.onDestroy();
    }
}

