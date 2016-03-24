package com.lanou3g.record.ui.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.ContactBean;
import com.lanou3g.record.tools.CloseHelper;
import com.lanou3g.record.utils.StreamUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Risky on 15/10/28.
 */
public class TestActivity02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView img = new ImageView(this);
        setContentView(img);
        String text = "测试数据流类型:";

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.picture);
        OutputStream baos = null;
        try {
            baos = new FileOutputStream("/sdcard/cache/picture.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        try {
//            StreamUtil.stringToStream("Android Studio is very good !",baos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = new FileInputStream("/sdcard/cache/picture.png");
//            int type = StreamUtil.formatStream(is);
//            Bitmap bitmap = StreamUtil.inputToBitmap(is,"pictrue.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            img.setImageBitmap(bitmap);
//            switch (type) {
//                case StreamUtil.FORMAT_PNG:
//                    text += "-这是一张PNG图片";
//                    break;
//                case StreamUtil.FORMAT_JPEG:
//                    text += "-这是一张JPEG图片";
//                    break;
//                default:
//                    Log.d("TestActivity02", "type:" + type);
//                    text += type;
//                    break;
//            }
            Log.d("TestActivity02", text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseHelper.close(is);
        }
    }
}