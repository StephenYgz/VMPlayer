package com.itheima.vmplayer.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.itheima.vmplayer.R;
import com.itheima.vmplayer.ui.fragment.HomeFragment;
import com.itheima.vmplayer.ui.fragment.MeiNvFragment;
import com.itheima.vmplayer.ui.fragment.MusicFragment;
import com.itheima.vmplayer.ui.fragment.MvFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private SparseArray<Fragment> fragmentHashMap;
    private float lastX;
    private float lastY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //SparseArray的核心算法是折半查找
        fragmentHashMap = new SparseArray<>();

        fragmentHashMap.put(R.id.tab_home,new HomeFragment());
        fragmentHashMap.put(R.id.tab_mv,new MvFragment());
        fragmentHashMap.put(R.id.tab_music,new MusicFragment());
        fragmentHashMap.put(R.id.tab_meinv,new MeiNvFragment());
        //bottomBar设置条目选中监听
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                //根据tabId从map集合中找到与之对应的Fragment
                Fragment fragment = fragmentHashMap.get(tabId);
                //拿到fragment显示到中间内容区域flContent
                showContent(fragment);

            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                int id=tabId;
            }
        });
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        //显示对话框，提示用户是否退出
        MaterialDialog.Builder builder=new MaterialDialog.Builder(this);

        builder.title("退出提示");
        builder.content("亲，真的确定离开吗？");

        builder.negativeText("点错了");
        builder.positiveText("确定");

        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取手指按下的距离屏幕的坐标
                lastX = ev.getRawX();
                lastY = ev.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
//                获取手指移动的距离屏幕的坐标
                float downX=ev.getRawX();
                float downY=ev.getRawY();
                //计算X,Y的偏移量
                float offsetX=downX-lastX;
                float offsetY=downY-lastY;
                //计算绝对值判断滑动方向
                float absX = Math.abs(offsetX);
                float absY = Math.abs(offsetY);

                if (absX < absY && offsetY>10 && !isShowing){
                    //向下，显示底部栏
                    showBottomBar();
                }else if (absX < absY && offsetY<-10 && isShowing){
//                    向上，隐藏底部栏
                    hideBottomBar();
                }


//                赋值操作获取偏移量
                lastX=downX;
                lastY=downY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShowing=true;

    /**
     * 隐藏底部栏
     */
    private void hideBottomBar() {
        isShowing=false;
        ObjectAnimator animator=ObjectAnimator.ofFloat(bottomBar,"translationY",0.0f,(float) bottomBar.getHeight());
        animator.setDuration(300);
        animator.start();
    }

    /**
     * 显示底部栏
     */
    private void showBottomBar() {
        isShowing=true;
        ObjectAnimator animator=ObjectAnimator.ofFloat(bottomBar,"translationY",(float) bottomBar.getHeight(),0.0f);
        animator.setDuration(300);
        animator.start();
    }

    private Fragment mFragment=null;
    /**
     * 切换中间内容
     * @param fragment
     */
    private void showContent(Fragment fragment) {
        if (fragment==null) return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mFragment==null){
            //app第一次运行的时候给mFragment赋值HomeFramgent
           mFragment=fragment;
        }

        if (mFragment==fragment){
//            当前是首页被加载到界面中，只会执行一次
            if (!fragment.isAdded()){
                transaction.add(R.id.fl_content,fragment).commit();
            }
        }else {
            //切换Framgent
            if (fragment.isAdded()){
                transaction.hide(mFragment).show(fragment).commit();
            }else {
                transaction.hide(mFragment).add(R.id.fl_content,fragment).commit();
            }
            mFragment=fragment;
        }


    }


}
