package com.lanou3g.record.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 本类由: Risky57 创建于: 16/3/23.
 */
public class StreamUtil {
    public static String inputToString(InputStream is) throws IOException {
        if (is != null) {
            InputStreamReader reader = new InputStreamReader(is);
            char[] data = new char[1024];
            int temp = 0;
            StringBuffer buffer = new StringBuffer();
            while ((temp = reader.read(data)) != -1) {
                String str = String.valueOf(data,0,temp);
                buffer.append(str);
            }
            is.close();
            if (reader != null) reader.close();
            return buffer.toString();
        }else return null;
    }
    public static Bitmap inputToBitmap(InputStream is){
        Bitmap bmp = BitmapFactory.decodeStream(is);
        return bmp;
    }

    public static void bmpToStream(Bitmap bmp ,OutputStream os){
        bmp.compress(Bitmap.CompressFormat.JPEG,100,os);
    }

    public static boolean stringToStream(String content,OutputStream os) throws IOException {
        if (os == null || TextUtils.isEmpty(content)) {
            return false;
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(content);
        writer.flush();
        writer.close();
        return true;

    }
}
