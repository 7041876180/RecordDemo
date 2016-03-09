# RecordDemo
如果有新建的Activity,在Manifest文件中配置Activity的label,
并且配置如下属性:
```
    <activity
        android:name="com.lanou3g.record.ui.activity.NotificationAty"
        android:label="NotificationAty">
        <intent-filter>
            <action android:name="android.intent.action.MAIN"/>
            <category android:name="com.lanou3g.record.DEMO_CODE"/>
        </intent-filter>
    </activity>
```