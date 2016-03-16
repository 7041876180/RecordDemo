package com.lanou3g.record.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.record.R;
import com.lanou3g.record.model.entity.Entity;
import com.lanou3g.record.observer.MySubject;
import com.lanou3g.record.observer.Observer;

/**
 * 本类由: Risky57 创建于: 16/3/16.
 */
public class Fragment03 extends Fragment implements Observer<Entity>{

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MySubject.register(this);
        return inflater.inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = (TextView) view.findViewById(R.id.textView);
        tv.setText("Fragment03");

    }
    @Override
    public void onPause() {
        super.onPause();
        MySubject.unRegister(this);
    }

    @Override
    public void update(Entity entity) {
        tv.setText(entity.getMessage()+"Fragment03");
    }
}
