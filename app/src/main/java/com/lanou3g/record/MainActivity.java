package com.lanou3g.record;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;

import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {
    private static final String TAG = "MainActivity";
    private static final String CATEGORY_LANOU3G = "com.lanou3g.lesson.DEMO_CODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
////            mClss = clss;
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA}, 1);
//        }
//        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) &&(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED)){
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_CONTACTS,Manifest.permission.READ_CONTACTS}, 1);
//        }

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.lanou3g.lesson.Path");
        if (path == null) {
            path = "";
        }

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(CATEGORY_LANOU3G);

        PackageManager pm = getPackageManager();

        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;


            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra("com.lanou3g.lesson.Path", path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }

    private MsgReceiver updateListViewReceiver;
    Message m = null;

    private void register() {
        XGPushConfig.enableDebug(this, true);
        // 0.注册数据更新监听器
        updateListViewReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
        registerReceiver(updateListViewReceiver, intentFilter);
        // 1.获取设备Token
        Handler handler = new HandlerExtension(MainActivity.this);
        m = handler.obtainMessage();
        // 注册接口
        XGPushManager.registerPush(getApplicationContext(),
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        Log.w(Constants.LogTag,
                                "+++ register push sucess. token:" + data);
                        m.obj = "+++ register push sucess. token:" + data;
                        m.sendToTarget();
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        Log.w(Constants.LogTag,
                                "+++ register push fail. token:" + data
                                        + ", errCode:" + errCode + ",msg:"
                                        + msg);

                        m.obj = "+++ register push fail. token:" + data
                                + ", errCode:" + errCode + ",msg:" + msg;
                        m.sendToTarget();
                    }
                });
    }

    private static class HandlerExtension extends Handler {
        WeakReference<MainActivity> mActivity;

        HandlerExtension(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity theActivity = mActivity.get();
            if (theActivity == null) {
                theActivity = new MainActivity();
            }
            if (msg != null) {
//                Log.w(Constants.LogTag, msg.obj.toString());
//                TextView textView = (TextView) theActivity
//                        .findViewById(R.id.deviceToken);
//                textView.setText(XGPushConfig.getToken(theActivity));
                Log.d(TAG, "handleMessage: " + msg.obj.toString() + XGPushConfig.getToken(theActivity));
            }
            // XGPushManager.registerCustomNotification(theActivity,
            // "BACKSTREET", "BOYS", System.currentTimeMillis() + 5000, 0);
        }
    }

    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//             TODO Auto-generated method stub
//            allRecorders = notificationService.getCount();
//            getNotificationswithouthint(id);
            Log.d(TAG, "onReceive() called with: " + "context = [" + context + "], intent = [" + intent + "]");
        }
    }
}
