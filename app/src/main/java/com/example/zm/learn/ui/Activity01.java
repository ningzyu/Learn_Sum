package com.example.zm.learn.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.Toast;

import com.example.zm.learn.R;
import com.example.zm.learn.adpter.A1_Adapter;
import com.example.zm.learn.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Activity01 extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private List<String> mDatas;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private A1_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
        initToolBar(getIntent().getStringExtra("string"),true);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);

        /**
         * 主要方法
         setOnRefreshListener(OnRefreshListener): 为布局添加一个Listener
         setRefreshing(boolean): 显示或隐藏刷新进度条
         isRefreshing(): 检查是否处于刷新状态
         setColorScheme(): 设置进度条的颜色主题，最多能设置四种
         */
        mSwipeRefreshWidget.setColorSchemeResources(R.color.color1);


        SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                if(mDatas!=null){
                    mDatas.clear();
                    for (int i = 0; i < 20; i++)
                    {
                        mDatas.add("下拉刷新"+i);
                    }
                }
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        };
        mSwipeRefreshWidget.setOnRefreshListener(listener);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshWidget.setRefreshing(true);
            }
        });
        listener.onRefresh();

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    //加载更多
                    //mSwipeRefreshWidget.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    Toast.makeText(MainActivity.this,"加载更多",Toast.LENGTH_LONG).show();
                    for (int i = 0; i < 20; i++)
                    {
                        mDatas.add("加载更多"+i);
                    }
                    handler.sendEmptyMessageDelayed(1, 3000);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

        });
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new A1_Adapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);

//        mRecyclerView.addItemDecoration(
//                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
        // handler.sendEmptyMessageDelayed(0, 3000);


    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mSwipeRefreshWidget.setRefreshing(false);
                    break;
                case 1:
                    //如果还有下一页的话就直接刷新，如果没有下一页的话那么久先删除footview后刷新
                    //adapter.notifyItemRemoved(adapter.getItemCount());
                    adapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    protected void initData(){
        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }
        for (int i = 0; i < 20; i++)
        {
            mDatas.add(i+"");
        }
    }


    /**
     * 反射  功能：一开始就出现刷新的动画(太快了。所以这里不用)
     * @param refreshLayout
     * @param refreshing
     * @param notify
     */
//    public static void setRefreshing(SwipeRefreshLayout refreshLayout,boolean refreshing, boolean notify){
//        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
//        if (refreshLayoutClass != null) {
//
//            try {
//                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
//                setRefreshing.setAccessible(true);
//                setRefreshing.invoke(refreshLayout, refreshing, notify);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
