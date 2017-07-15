package com.itheima.vmplayer.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.vmplayer.R;
import com.itheima.vmplayer.model.MvBean;
import com.itheima.vmplayer.ui.activity.PlayerActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvItemAdapter extends RecyclerView.Adapter {
    private List<MvBean.VideosBean> videosBeanList;
    private Context context;
    private int mWidth, mHeight;

    public MvItemAdapter(List<MvBean.VideosBean> videosBeanList, Context context, int mWidth, int mHeight) {
        this.videosBeanList = videosBeanList;
        this.context = context;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_mvitem, parent, false);
        return new MvItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MvBean.VideosBean videosBean = videosBeanList.get(position);
        MvItemViewHolder mvItemViewHolder= (MvItemViewHolder) holder;
        ViewGroup.LayoutParams layoutParams = mvItemViewHolder.ivPostimg.getLayoutParams();
        layoutParams.width=mWidth;
        layoutParams.height=mHeight;

        mvItemViewHolder.viewbgs.setLayoutParams(layoutParams);
        mvItemViewHolder.title.setText(videosBean.getTitle());
        mvItemViewHolder.tvDescription.setText(videosBean.getDescription());

        Glide.with(context).load(videosBean.getPosterPic()).into(mvItemViewHolder.ivPostimg);
        mvItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent();
                mIntent.setClass(context, PlayerActivity.class);
                mIntent.putExtra("id",videosBean.getId());
                mIntent.putExtra("tag",1);
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosBeanList == null ? 0 : videosBeanList.size();
    }

    static class MvItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_postimg)
        ImageView ivPostimg;
        @BindView(R.id.viewbgs)
        View viewbgs;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.rl_item_rootView)
        RelativeLayout rlItemRootView;

        MvItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
