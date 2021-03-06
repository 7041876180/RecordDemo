package com.lanou3g.record.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.library.base.AbsAdapter;
import com.lanou3g.library.base.BaseAdapter;
import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;

import java.util.List;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketAdapter extends AbsAdapter<ChatMessage> {
    public SocketAdapter(List<ChatMessage> dataList, Context context) {
        super(dataList, context);
    }

    public SocketAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getMsgMode();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemType = getItemViewType(position);
        SendHolder sendHolder = null;
        ReceiveHolder receiveHolder = null;
        if (convertView == null) {
            switch (itemType){
                case ChatMessage.MODE_SEND:
                    convertView = inflater.inflate(R.layout.item_send,parent,false);
                    sendHolder = new SendHolder(convertView);
                    convertView.setTag(sendHolder);
                    break;
                case ChatMessage.MODE_RECEIVE:
                    convertView = inflater.inflate(R.layout.item_receive,parent,false);
                    receiveHolder = new ReceiveHolder(convertView);
                    convertView.setTag(receiveHolder);
                    break;
            }
        }else {
            switch (itemType) {
                case ChatMessage.MODE_SEND:
                    sendHolder = (SendHolder) convertView.getTag();
                    break;
                case ChatMessage.MODE_RECEIVE:
                     receiveHolder = (ReceiveHolder) convertView.getTag();
                    break;
            }
        }
        ChatMessage msg = getItem(position);
        switch (itemType) {
            case ChatMessage.MODE_SEND:
                sendHolder.tvContent.setText(msg.getContent());
                break;
            case ChatMessage.MODE_RECEIVE:
                receiveHolder.tvContent.setText(msg.getContent());
                break;
        }


        return convertView;
    }

    public static class SendHolder extends BaseAdapter.ViewHolder{
        private TextView tvContent;
        public SendHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.content_send);
        }
    }

    public static class ReceiveHolder extends BaseAdapter.ViewHolder{
        private TextView tvContent;
        public ReceiveHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.content_receive);
        }
    }

}
