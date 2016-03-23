package com.lanou3g.record.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;
import com.lanou3g.record.ui.adapter.SocketAdapter;
import com.lanou3g.record.utils.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketServerFgmt extends Fragment implements View.OnClickListener {
    private static final String TAG = "socketServerFgmt";
    private ListView lvChat;
    private Button btnSend, btnReceive;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_socket, container, false);
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvChat = (ListView)  view.findViewById(R.id.lv_chat);
        btnSend = (Button)   view.findViewById(R.id.btn_send);
        btnReceive = (Button)view.findViewById(R.id.btn_receive);
        editText = (EditText)view.findViewById(R.id.et_edit);

        btnSend.setOnClickListener(this);
        btnReceive.setOnClickListener(this);
        btnReceive.setEnabled(false);
        adapter = new SocketAdapter(getActivity());
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
        while (true) {
            try {
                server = new ServerSocket(1958);
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                String content = StreamUtil.inputToString(is);
                Message msg = handler.obtainMessage();
                msg.obj = content;
                handler.sendMessage(msg);

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
    }

    @Override
    public void onClick(View v) {
        String content = editText.getText().toString();
        ChatMessage msg = new ChatMessage();
        msg.setContent("假消息,未发送");
        switch (v.getId()) {
            case R.id.btn_send:
                msg.setMsgMode(ChatMessage.MODE_SEND);
                break;
            case R.id.btn_receive:
                msg.setMsgMode(ChatMessage.MODE_RECEIVE);
                break;
        }
        adapter.append(msg);
        lvChat.smoothScrollToPosition(adapter.getCount() - 1);
    }
}
