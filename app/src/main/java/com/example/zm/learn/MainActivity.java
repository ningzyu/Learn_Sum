package com.example.zm.learn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.zm.learn.base.BaseActivity;
import com.example.zm.learn.ui.Activity01;
import com.example.zm.learn.ui.Activity02;
import com.example.zm.learn.ui.Activity03;
import com.example.zm.learn.ui.Activity04;
import com.example.zm.learn.ui.Activity05;
import com.example.zm.learn.ui.Activity06;
import com.example.zm.learn.ui.Activity07;
import com.example.zm.learn.ui.Activity08;
import com.example.zm.learn.ui.Activity09;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends BaseActivity {
    private ListView lv;
    List<String> list=new ArrayList<>();
    List<Activity> list1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar("学习总结汇总",false);
        ddd();
        lv= (ListView) findViewById(R.id.lv);
        myAdapter ada=new myAdapter();
        lv.setAdapter(ada);
    }

    private void ddd() {
        initData("刷新",new Activity01());
        initData("刷新",new Activity02());
        initData("时间选择",new Activity03());
        initData("仿QQ刷新",new Activity04());
        initData("仿淘宝竖滑广告条",new Activity05());
        initData("ViewFlipper实现轮播",new Activity06());
        initData("",new Activity07());
        initData("",new Activity08());
        initData("",new Activity09());
//        initData("",new Activity10());
//        initData("",new Activity11());
//        initData("",new Activity12());
//        initData("",new Activity13());
    }

    private void initData(String s,Activity a) {
        list1.add(a);
        list.add(s);
    }
    class myAdapter extends BaseAdapter {
        private LayoutInflater inflater;//布局填充器
        public myAdapter(){
            inflater=(LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){//
                view=inflater.inflate(R.layout.lv_main_item,null);
            }
            Button bt= (Button) view.findViewById(R.id.btn);
            bt.setText(list.get(i));
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,list1.get(i).getClass()).putExtra("string",list.get(i)));
                }
            });
            return view;
        }
    }
}
