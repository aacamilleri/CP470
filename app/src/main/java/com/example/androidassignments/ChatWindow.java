package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Adapter;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    ArrayList<String> texts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        ListView listView = findViewById(R.id.listView);
        EditText textMsg = findViewById(R.id.textMsgField);
        Button sendButton = findViewById(R.id.sendButton);

        ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME, "User chatted");
                texts.add(textMsg.getText().toString());
                messageAdapter.notifyDataSetChanged();
                textMsg.setText("");
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String> {
            public ChatAdapter(Context ctx) {
                super(ctx, 0);
            }

            public int getCount() {
                return(texts.size());
            }

            public String getItem(int position) {
                return(texts.get(position));
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
                View result = null;
                if (position % 2 == 0) {
                    result = inflater.inflate(R.layout.chat_row_incoming, null);
                } else {
                    result = inflater.inflate(R.layout.chat_row_outgoing, null);
                }

                TextView message = (TextView) result.findViewById(R.id.message_text);
                message.setText(getItem(position));
                return result;
            }

        }
    }
