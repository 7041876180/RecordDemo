package com.lanou3g.lesson.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * 本类由: Risky57 创建于: 16/3/8.
 */
public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "ScanActivity";
    private ZXingScannerView scanView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // API23之后需要手动调用获取权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        }
        scanView = new ZXingScannerView(this);
        setContentView(scanView);

    }

    @Override
    public void onResume() {
        super.onResume();
        scanView.setResultHandler(this);
        scanView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scanView.stopCamera();
    }

    private Handler mHandler = new Handler();

    // 扫描成功后会回调此方法
    @Override
    public void handleResult(Result result) {
        Log.v(TAG, result.getText());
        Log.v(TAG, result.getBarcodeFormat().toString());

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanView.resumeCameraPreview(ScanActivity.this);

            }
        }, 2000);
    }
}
