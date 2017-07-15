package com.itheima.vmplayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.model.MusicBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/23.
 */

public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(view);
        MusicBean musicBean = MusicBean.getMusicBean(cursor);
        viewHolder.tvTitle.setText(CommUtils.formatName(musicBean.getTitle()));
        viewHolder.tvArtist.setText(musicBean.getArtist());
        viewHolder.tvSize.setText(Formatter.formatFileSize(context,musicBean.getSize()));
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_artist)
        TextView tvArtist;
        @BindView(R.id.tv_size)
        TextView tvSize;

        public static ViewHolder getViewHolder(View view){
            ViewHolder viewHolder= (ViewHolder) view.getTag();
            if (viewHolder==null){
                viewHolder=new ViewHolder(view);
                view.setTag(viewHolder);

            }
            return viewHolder;
        }

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
