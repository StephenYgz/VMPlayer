package com.itheima.vmplayer.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.vmplayer.R;
import com.itheima.vmplayer.model.HomeBean;
import com.itheima.vmplayer.ui.activity.PlayerActivity;
import com.itheima.vmplayer.ui.activity.WebViewAcitivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/21.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private List<HomeBean> homeBeanList;
    private Context context;
    private int picWidth, picHeight;
    private HomeViewHolder homeViewHolder;

    public HomeAdapter(List<HomeBean> homeBeanList, Context context, int picWidth, int picHeight) {
        this.homeBeanList = homeBeanList;
        this.context = context;
        this.picWidth = picWidth;
        this.picHeight = picHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final HomeBean homeBean = homeBeanList.get(position);
        this.homeViewHolder = (HomeViewHolder) viewHolder;
        ViewGroup.LayoutParams layoutParams = this.homeViewHolder.ivContentimg.getLayoutParams();
         layoutParams.width=picWidth;
         layoutParams.height=picHeight;
         this.homeViewHolder.viewbg.setLayoutParams(layoutParams);

         this.homeViewHolder.tvTitle.setText(homeBean.getTitle());
         this.homeViewHolder.tvDescription.setText(homeBean.getDescription());

        Glide.with(context).load(homeBean.getPosterPic()).into(this.homeViewHolder.ivContentimg);

        String type=homeBean.getType();
        final int tag;
        if ("ACTIVITY".equalsIgnoreCase(type)) {//打开页面
            tag = 0;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_activity);
        } else if ("VIDEO".equalsIgnoreCase(type)) {//首播，点击进去显示MV描述，相关MV
            tag = 1;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_video);
        } else if ("WEEK_MAIN_STAR".equalsIgnoreCase(type)) {//(悦单)点击进去跟显示悦单详情一样
            tag = 2;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_star);
        } else if ("PLAYLIST".equalsIgnoreCase(type)) {//(悦单)点击进去跟显示悦单详情一样
            tag = 3;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_playlist);
        } else if ("AD".equalsIgnoreCase(type)) {
            tag = 4;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_ad);
        } else if ("PROGRAM".equalsIgnoreCase(type)) {//跳到MV详情
            tag = 5;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_program);
        } else if ("bulletin".equalsIgnoreCase(type)) {
            tag = 6;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_bulletin);
        } else if ("fanart".equalsIgnoreCase(type)) {
            tag = 7;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_fanart);
        } else if ("live".equalsIgnoreCase(type)) {
            tag = 8;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_live);
        } else if ("LIVENEW".equalsIgnoreCase(type) || ("LIVENEWLIST".equals(type))) {
            tag = 9;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_live_new);
        } else if ("INVENTORY".equalsIgnoreCase(homeBean.getType())) {//打开页面
            tag = 10;
            homeViewHolder.ivType.setImageResource(R.mipmap.home_page_project);
        } else {
            tag = -100;
            homeViewHolder.ivType.setImageResource(0);
        }

        homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent();
                switch (tag){
                    case 0:
                    case 4:
                    case 10:
                        //打開Html頁面
                       mIntent.setClass(context, WebViewAcitivity.class);
                        mIntent.putExtra("url",homeBean.getUrl());
                        mIntent.putExtra("title",homeBean.getTitle());
                        context.startActivity(mIntent);

                        break;
                    case 1:
                    case 5:
                    case 7:
                       //打开播放器界面
                        mIntent.setClass(context, PlayerActivity.class);
                        mIntent.putExtra("id",homeBean.getId());
                        mIntent.putExtra("tag",1);
                        context.startActivity(mIntent);


                        break;
                    case 2:
                    case 3:
                        //打开播放器界面
                        mIntent.setClass(context, PlayerActivity.class);
                        mIntent.putExtra("id",homeBean.getId());
                        mIntent.putExtra("tag",2);
                        context.startActivity(mIntent);

                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeBeanList == null ? 0 : homeBeanList.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_logo)
        ImageView ivItemLogo;
        @BindView(R.id.iv_contentimg)
        ImageView ivContentimg;
        @BindView(R.id.viewbg)
        View viewbg;
        @BindView(R.id.iv_type)
        ImageView ivType;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        HomeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
