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

为了编译,只是把app设置为了module.其他的都是一些小java程序(设计模式啥的).
想看的话就把目录最外层的settings.gradle文件里面的注释打开
app下面的java目录里面有一些.puml文件,是画UML类图的,需要插件支持.