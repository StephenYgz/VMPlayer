package com.itheima.vmplayer.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itheima.vmplayer.R;
import com.itheima.vmplayer.model.MeiNvBean;
import com.itheima.vmplayer.ui.activity.MainActivity;
import com.itheima.vmplayer.ui.activity.PhotoDatailActivity;
import com.itheima.vmplayer.utils.CommUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/24.
 */

public class MeiNvAdapter extends RecyclerView.Adapter {

    private List<MeiNvBean.ItemsBean> datas;
    private Context context;

    public MeiNvAdapter(List<MeiNvBean.ItemsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meinv_item, parent, false);
        return new MnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MeiNvBean.ItemsBean itemsBean = datas.get(position);
        MnViewHolder mnViewHolder= (MnViewHolder) holder;
        ViewGroup.LayoutParams layoutParams = mnViewHolder.ivMeinv.getLayoutParams();
        int picWidth=Integer.parseInt(itemsBean.getWidth());
        int picHeight=Integer.parseInt(itemsBean.getHeight());
        int halfScreenWidth= CommUtils.screenWidth((MainActivity)context)/2;
        layoutParams.width=halfScreenWidth;
        layoutParams.height=halfScreenWidth*picHeight/picWidth;
        Glide.with(context).load(itemsBean.getSmallThumbUrl()).into(mnViewHolder.ivMeinv);


        mnViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(context, PhotoDatailActivity.class);
                mIntent.putExtra("url",itemsBean.getSmallThumbUrl());
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    static class MnViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_meinv)
        ImageView ivMeinv;

        MnViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
