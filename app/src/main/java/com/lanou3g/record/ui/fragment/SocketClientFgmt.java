package com.lanou3g.record.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ChatMessage;
import com.lanou3g.record.ui.adapter.SocketAdapter;
import com.lanou3g.library.utils.StreamUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 本类由: Risky57 创建于: 16/3/22.
 */
public class SocketClientFgmt extends Fragment implements View.OnClickListener {
    private ListView lvChat;
    private Button btnSend, btnReceive;
    private EditText editText;
    private SocketAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_socket, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvChat = (ListView) view.findViewById(R.id.lv_chat);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        btnReceive = (Button) view.findViewById(R.id.btn_receive);
        editText = (EditText) view.findViewById(R.id.et_edit);

        btnSend.setOnClickListener(this);
        btnReceive.setOnClickListener(this);
        btnReceive.setEnabled(false);
        adapter = new SocketAdapter(getActivity());
        lvChat.setAdapter(adapter);
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

    private void sendSocket(String content) {
        Socket socket = null;
        try {
            socket = new Socket("10.0.3.15", 1958);
            OutputStream os = socket.getOutputStream();
            StreamUtil.stringToStream(content,os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
