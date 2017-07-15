package com.itheima.vmplayer.ui.fragment;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by wschun on 2016/12/20.
 */

public class BaseFragment extends Fragment {

    protected View rootView;
    protected int offset;                   //起始位置
    protected static final int SIZE=10;     //每一页的个数
    protected int mWidth,mHeight;           //图片的显示宽高
    protected boolean isFresh=false;        //是否刷新
    protected int lastVisibleItemPosition;  //滚动时可视的最后一一个Item
    protected boolean hasMode=true;         //服务器是否还有数据
    private MaterialDialog materialDialog;

    protected void observerView(int width,int height){

        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mWidth=metrics.widthPixels;
        mHeight=mWidth*height/width;
    }

    protected void showDialog(){
        MaterialDialog.Builder builder=new MaterialDialog.Builder(getContext());
        builder.progress(true,-1);
        builder.title("正在加载。。。");
        materialDialog = builder.show();
    }

    protected void dismiss(){
        if (materialDialog.isShowing()){
            materialDialog.dismiss();
        }
    }
}
