package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;
import com.lanou3g.record.ui.adapter.SocketAdapter;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketClientActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView lvChat;
    private Button btnSend,btnReceive;
    private EditText editText;
    private SocketAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        lvChat = (ListView) findViewById(R.id.lv_chat);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnReceive = (Button) findViewById(R.id.btn_receive);
        editText = (EditText) findViewById(R.id.et_edit);

        btnSend.setOnClickListener(this);
        btnReceive.setOnClickListener(this);
        adapter = new SocketAdapter(this);
        lvChat.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        String content = editText.getText().toString();
        ChatMessage msg = new ChatMessage();
        msg.setContent(content);
        switch (v.getId()) {
            case R.id.btn_send:
                msg.setMsgMode(ChatMessage.MODE_SEND);
                break;
            case R.id.btn_receive:
                msg.setMsgMode(ChatMessage.MODE_RECEIVE);
                break;
        }
        adapter.append(msg);
        lvChat.smoothScrollToPosition(adapter.getCount()-1);
    }
}
