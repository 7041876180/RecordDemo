package com.lanou3g.record.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.record.utils.TimeUtil;

import java.util.Date;

/**
 * Created by Risky on 15/10/28.
 */
public class TimeDescriptionAty extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);
        TextView currentTime = new TextView(this);
        TextView targetTime = new TextView(this);
        TextView descriptionTime = new TextView(this);
        linearLayout.addView(currentTime);
        linearLayout.addView(targetTime);
        linearLayout.addView(descriptionTime);

        currentTime.setText("当前的时间为:" + TimeUtil.getCurrentTime("yy-MM-dd hh:mm:ss"));

        String target = "2015-10-25 10:23:56";
        targetTime.setText("目标的时间是:" + target);

        Date date = TimeUtil.getTimeFromString(target, "yy-MM-dd hh:mm:ss");
        String description = TimeUtil.getDescriptionTimeFromTimestamp(date.getTime());
        descriptionTime.setText(description);


    }
}
