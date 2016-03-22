package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;
import com.lanou3g.record.ui.adapter.SocketAdapter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketClientActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvChat;
    private Button btnSend, btnReceive;
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

    private void sendSocket(String content) {


        // 10.0.3.15:1958
        Socket socket = null;
        try {
            socket = new Socket("192.168.0.103", 1958);
            byte[] data = content.getBytes();
            InputStream is = new ByteArrayInputStream(data);
            OutputStream os = socket.getOutputStream();
            byte[] buffer = new byte[16];
            int temp = 0;
            while ((temp = is.read(buffer)) != -1) {
                os.write(buffer, 0, temp);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {
        final String content = editText.getText().toString();
        ChatMessage msg = new ChatMessage();
        msg.setContent(content);
        msg.setMsgMode(ChatMessage.MODE_SEND);
        adapter.append(msg);
        lvChat.smoothScrollToPosition(adapter.getCount() - 1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendSocket(content);
            }
        }).start();
    }
}
