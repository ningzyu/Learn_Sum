package com.example.zm.learn.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zm.learn.R;

import java.util.List;

/**适配器
 * Created by Administrator on 2016/1/9.
 */
public class A1_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    List<String> mDatas;

    public void setmDatas(List<String> Datas) {
        this.mDatas = Datas;
    }

    public A1_Adapter(Context contex, List<String> Datas) {
        this.mContext=contex;
        this.mDatas=Datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_ITEM) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.a1_ivitem, parent,
                    false));
            return holder;
        }else if(viewType == TYPE_FOOTER){// type == TYPE_FOOTER 返回footerView
//            FooterViewHolder footerViewHolder = new FooterViewHolder(LayoutInflater.from(
//                    mContext).inflate(R.layout.item_footview, parent,
//                    false));
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_footview, null);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            ((MyViewHolder)holder).tv.setText(mDatas.get(position));
        }
    }

//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position)
//    {
//        holder.tv.setText(mDatas.get(position));
//    }

    // RecyclerView的count设置为数据总条数+ 1（footerView）
    @Override
    public int getItemCount(){

        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }
}
