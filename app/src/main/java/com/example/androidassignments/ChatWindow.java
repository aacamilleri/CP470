package com.example.androidassignments;

import static com.example.androidassignments.ChatDatabaseHelper.DATABASE_NAME;
import static com.example.androidassignments.ChatDatabaseHelper.KEY_MESSAGE;
import static com.example.androidassignments.ChatDatabaseHelper.KEY_ID;
import static com.example.androidassignments.ChatDatabaseHelper.TABLE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    static final String MESSAGE_QUERY = "SELECT KEY_MESSAGE FROM MESSAGES";
    ArrayList<String> texts = new ArrayList<String>();
    protected static SQLiteDatabase Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        ListView listView = findViewById(R.id.listView);
        EditText textMsg = findViewById(R.id.textMsgField);
        Button sendButton = findViewById(R.id.sendButton);

        ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);


        ChatDatabaseHelper dhHelper = new ChatDatabaseHelper(this);
        Database = dhHelper.getWritableDatabase();

        final Cursor cursor = Database.rawQuery(MESSAGE_QUERY, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL NOTIFIER:" + cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE)));
            texts.add(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE)));
            cursor.moveToNext();
        }

        for(int i=0; i<cursor.getColumnCount();i++) {
            Log.i(ACTIVITY_NAME, "Column Name: " + cursor.getColumnName(i));
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME, "User chatted");
                texts.add(textMsg.getText().toString());
                messageAdapter.notifyDataSetChanged();
                textMsg.setText("");

                ContentValues cv = new ContentValues();
                cv.put(KEY_MESSAGE, textMsg.getText().toString());
                Database.insert(TABLE_NAME, null, cv);
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

    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "In onDestroy");
        super.onDestroy();
        Database.close();
    }
}
