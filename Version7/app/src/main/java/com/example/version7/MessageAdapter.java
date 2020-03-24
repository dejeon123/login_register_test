package com.example.version7;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ChatMessageFormat> {
    public MessageAdapter(Context context, int resource, List<ChatMessageFormat> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(ChatMain.TAG, "getView:");

        ChatMessageFormat message = getItem(position);

        if(TextUtils.isEmpty(message.getMessage())){


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chat_user_connected, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);

            Log.i(ChatMain.TAG, "getView: is empty ");
            String userConnected = message.getUsername();
            messageText.setText(userConnected);

        }else if(message.getUniqueId().equals(ChatMain.uniqueId)){
            Log.i(ChatMain.TAG, "getView: " + message.getUniqueId() + " " + ChatMain.uniqueId);


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chat_my_message, parent, false);
            TextView messageText = convertView.findViewById(R.id.message_body);
            messageText.setText(message.getMessage());

        }else {
            Log.i(ChatMain.TAG, "getView: is not empty");

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chat_their_message, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);
            TextView usernameText = (TextView) convertView.findViewById(R.id.name);

            messageText.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);

            messageText.setText(message.getMessage());
            usernameText.setText(message.getUsername());
        }

        return convertView;
    }
}
