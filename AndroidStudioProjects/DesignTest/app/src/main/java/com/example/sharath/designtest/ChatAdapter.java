package com.example.sharath.designtest;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sharath on 14/3/18.
 */

public class ChatAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Message> chat;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    Animation animation;
    int lastPosition = -1;
    ChatAdapter(Context applicationContext, ArrayList<Message> chat) {
        this.context = applicationContext;
        this.chat = chat;
    }

    @Override
    public int getItemViewType(int position) {
        if (chat.get(position).getMsgType().equalsIgnoreCase("IN")) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_msg_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_msg_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = chat.get(position);
        animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
        }

    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return chat.size();
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime;

        SentMessageHolder(View itemView) {
            super(itemView);

            txtMessage = itemView.findViewById(R.id.txt_msg_sent);
            txtTime = itemView.findViewById(R.id.txt_sent_time);
        }

        void bind(Message message) {
            txtMessage.setText(message.getMessage());
            txtTime.setText(message.getTimeStamp());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtMessageReceived,txtNameAndTime;
        ImageButton ibSmiley;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            txtMessageReceived = itemView.findViewById(R.id.txt_msg_received);
            txtNameAndTime = itemView.findViewById(R.id.txt_sender_name_time);
            ibSmiley = itemView.findViewById(R.id.ib_smiley);
        }

        void bind(Message message) {
            txtMessageReceived.setText(message.getMessage());
            txtNameAndTime.setText(String.format("Sharath Weaver, %s", message.getTimeStamp()));
            ibSmiley.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final float scale = context.getResources().getDisplayMetrics().density;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.custom_pop_up,null);
            PopupWindow popupWindow = new PopupWindow(layout, (int) (246 * scale + 0.5f), LinearLayout.LayoutParams.WRAP_CONTENT,true);
            if(Build.VERSION.SDK_INT>=21)
                popupWindow.setElevation(16);
            popupWindow.showAsDropDown(v);
        }
    }
}
