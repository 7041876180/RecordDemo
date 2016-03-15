# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Risky/Documents/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes *Annotation*

-keep class com.lanou3g.record.model.entity.** { *; }
-keep class com.lanou3g.record.model.entity.greendao.** { *; }
-keep class com.lanou3g.record.view.** { *; }

-keep class com.jg.** { *; }
-keep class com.qq.taf.jce.** { *; }
-keep class com.tencent.android.tpush.** { *; }
-keep class android.support.v7.** { *; }
-keep class com.android.volley.** { *; }
-keep class me.dm7.barcodescanner.core.** { *; }
-keep class me.dm7.barcodescanner.zxing.** { *; }
-keep class com.google.zxing.** { *; }
-keep class android.support.design.** { *; }
-keep class de.greenrobot.event.** { *; }
-keep class de.greenrobot.dao.** { *; }
-keep class com.google.gson.** { *; }
-keep class org.hamcrest.** { *; }
-keep class junit.** { *; }
-keep class org.junit.** { *; }
-keep class com.squareup.okhttp.** { *; }
-keep class okio.** { *; }
-keep class android.support.percent.** { *; }
-keep class android.support.annotation.** { *; }
-keep class android.support.v4.** { *; }
-keep class rx.** { *; }

-dontwarn com.jg.**
-dontwarn com.qq.taf.jce.**
-dontwarn com.tencent.android.tpush.**
-dontwarn android.support.v7.**
-dontwarn com.android.volley.**
-dontwarn me.dm7.barcodescanner.core.**
-dontwarn me.dm7.barcodescanner.zxing.**
-dontwarn com.google.zxing.**
-dontwarn android.support.design.**
-dontwarn de.greenrobot.event.**
-dontwarn de.greenrobot.dao.**
-dontwarn com.google.gson.**
-dontwarn org.hamcrest.**
-dontwarn junit.**
-dontwarn org.junit.**
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn android.support.percent.**
-dontwarn android.support.annotation.**
-dontwarn android.support.v4.**
-dontwarn rx.**