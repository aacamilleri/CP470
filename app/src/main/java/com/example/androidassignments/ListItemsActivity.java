package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    protected static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        ImageButton button = (ImageButton)findViewById(R.id.picButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        Switch switch1 = (Switch)findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    int text = R.string.SwitchOn;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
                } else {
                    int text = R.string.SwitchOff;
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
                }

            }
        });

        CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.DialogMessage);
                    builder.setTitle(R.string.DialogTitle);
                    builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("Response", R.string.Response);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    builder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton picButton = findViewById(R.id.picButton);
            picButton.setImageBitmap(imageBitmap);
        }
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