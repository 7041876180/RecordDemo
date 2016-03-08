package com.lanou3g.lesson.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;

import com.lanou3g.lesson.model.entity.ContactBean;

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
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        List<ContactBean> beanList = new ArrayList<>();
        cursor.moveToFirst();

        do {
            ContactBean contactBean = new ContactBean();
            //获取联系人姓名
            String displayName = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contactBean.setName(displayName);
            //获取联系人手机号
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactBean.setTelPhone(number);

            beanList.add(contactBean);
        } while (cursor.moveToNext());

        Log.d("TAGGG", beanList.toString());

        List<Character> tags = new ArrayList<>();
        Map<Character,List<ContactBean>> infos = new HashMap<>();

        for (int i = 0; i < beanList.size(); i++) {
            ContactBean bean = beanList.get(i);
            String name = bean.getName();
            char first = name.charAt(0);
            if (first >= 65 && first <= 90) {
            } else {
                first = (char) (first - 32);
            }

            List<ContactBean> contactBeans = null;
            if (tags.contains(first)) {
                contactBeans = infos.get(first);
            }else {
                contactBeans = new ArrayList<>();
                tags.add(first);
            }
            contactBeans.add(bean);
            infos.put(first,contactBeans);
        }
        Log.d("TAGGG",infos.toString());

        for (int i = 0; i < infos.size(); i++) {
            List<ContactBean> beans = infos.get(i+65);
            Log.d("TAGGG",beans.toString());
        }



    }

}