package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected static final String FILE_NAME = "shared_prefs";
    protected static final String EMAIL = "Default Email";
    protected static final String ACTIVITY_NAME = "LoginActivity";

    EditText ed1;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate");
        setContentView(R.layout.activity_login);

        ed1 = (EditText)findViewById(R.id.ed1);
        Button button = findViewById(R.id.login_button);

        prefs = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        String val = prefs.getString("Default Email", "email@domain.com");
        ed1.setText(val);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = ed1.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(EMAIL, e);
                editor.commit();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}

