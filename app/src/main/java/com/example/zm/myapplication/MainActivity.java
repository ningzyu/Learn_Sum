package com.example.zm.myapplication;

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

import com.example.zm.myapplication.base.BaseActivity;

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
//        initData("通知栏",new Activity01());
//        initData("ListView头部悬浮效果",new Activity02());
//        initData("MPChart图表开源库1",new Activity03());
//        initData("MPChart图表开源库2",new Activity04());
//        initData("实用小组件",new Activity05());
//        initData("购物车",new Activity06());
//        initData("网上的全选全部选",new Activity07());
//        initData("Gson解析",new Activity08());
//        initData("Recycler使用",new Activity09());
//        initData("Fragment管理",new Activity10());
//        initData("嵌套滑动",new Activity11());
//        initData("阿姨帮分类页",new Activity12());
//        initData("阿姨帮分类页",new Activity13());
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
