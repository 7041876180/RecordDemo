package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;
import com.lanou3g.library.tools.CloseHelper;
import com.lanou3g.record.ui.adapter.SocketAdapter;
import com.lanou3g.library.utils.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketServerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SocketServerActivity";
    private ListView lvChat;
    private Button btnSend;
    private EditText editText;
    private SocketAdapter adapter;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String content = (String) msg.obj;
            ChatMessage chat = new ChatMessage();
            chat.setContent(content);
            chat.setMsgMode(ChatMessage.MODE_RECEIVE);
            adapter.append(chat);
            lvChat.smoothScrollToPosition(adapter.getCount() - 1);
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        lvChat = (ListView) findViewById(R.id.lv_chat);
        btnSend = (Button) findViewById(R.id.btn_send);
        editText = (EditText) findViewById(R.id.et_edit);

        btnSend.setOnClickListener(this);
        adapter = new SocketAdapter(this);
        lvChat.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initSocketServer();
            }
        }).start();
    }

    private void initSocketServer() {
        ServerSocket server = null;
        int i = 1;
        try {
            server = new ServerSocket(1958);
            while (true) {
                Log.d(TAG, "initSocketServer: 准备接收");
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                String content = StreamUtil.inputToString(is);
                Message msg = handler.obtainMessage();
                msg.obj = content;
                handler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != server) server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        String content = editText.getText().toString();
        ChatMessage msg = new ChatMessage();
        msg.setContent("这个是假消息,未进行发送");
        switch (v.getId()) {
            case R.id.btn_send:
                msg.setMsgMode(ChatMessage.MODE_SEND);
                break;
        }
        adapter.append(msg);
        lvChat.smoothScrollToPosition(adapter.getCount() - 1);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
