package com.lanou3g.record.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.lanou3g.record.R;

/**
 * 本类由: Risky57 创建于: 16/3/9.
 */
public class NotificationAty extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ACTION_PRE = "com.lanou3g.record.PRE_ACTION";
    private static final String ACTION_NEXT = "com.lanou3g.record.NEXT_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);


        initReceiver();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 获取NotificationManager对象
        final NotificationManager notyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 通过Builder(建造者模式)的对象,设置Notification的各个属性.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("收到了一个消息,请查看");
        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentTitle("消息来自Lanou").setContentText("love lanou3g~!");
//        builder.setLargeIcon(bmp);
        builder.setContentIntent(getPending());
        builder.setAutoCancel(true);
        // 自定义Notification的布局
        builder.setContent(getRemoteViews());
        // 构建Notification对象
        final Notification notfy = builder.build();

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过NotificationManager的notify方法显示Notification
                // 第一个参数为id,用于区分不同的Notification
                notyManager.notify(10, notfy);
                Log.d(TAG, "onClick: SEND");
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notyManager.cancel(10);
                notyManager.cancelAll();
                Log.d(TAG, "onClick: CANCEL");
            }
        });
    }

    private PreReceiver preReceiver;
    private NextReceiver nextReceiver;

    private void initReceiver() {
        preReceiver = new PreReceiver();
        nextReceiver = new NextReceiver();
        IntentFilter preFilter = new IntentFilter(ACTION_PRE);
        IntentFilter nextFilter = new IntentFilter(ACTION_NEXT);

        registerReceiver(preReceiver, preFilter);
        registerReceiver(nextReceiver, nextFilter);

    }

    private PendingIntent getPending() {
        // 创建一个跳转的Intent
        Intent intent = new Intent(this, NewActivity.class);
        // 转化为PendingIntent.
        // 参数2为结果码
        // FLAG_ONE_SHOT 表示返回的PendingIntent仅能执行一次,执行完后自动取消
        // FLAG_NO_CREATE 表示如果描述的PendingIntent不存在,并不创建相应的PendingIntent,而是返回NULL
        // FLAG_CANCEL_CURRENT 表示相应的PendingIntent已经存在,则取消前者,然后创建新的PendingIntent,这个有利于数据保持为最新的,可以用于即时通信的通信场景
        // FLAG_UPDATE_CURRENT 表示更新的PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    public RemoteViews getRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_view);
        remoteViews.setTextViewText(R.id.btnPre, "上一曲");
        remoteViews.setTextViewText(R.id.btnNext, "下一曲");
        remoteViews.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        Intent preIntent = new Intent(ACTION_PRE);
        Intent nextIntent = new Intent(ACTION_NEXT);

        PendingIntent prePending = PendingIntent.getBroadcast(this,0,preIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextPending = PendingIntent.getBroadcast(this,0,nextIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.btnPre,prePending);
        remoteViews.setOnClickPendingIntent(R.id.btnNext,nextPending);

        return remoteViews;
    }

    public class PreReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: 上一曲");
        }
    }

    public class NextReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: 下一曲");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(preReceiver);
        unregisterReceiver(nextReceiver);
    }

}