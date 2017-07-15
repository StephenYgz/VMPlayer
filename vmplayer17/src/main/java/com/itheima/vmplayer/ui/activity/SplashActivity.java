package com.itheima.vmplayer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.itheima.vmplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
       /*
        检测版本更新
        初始化数据库
        设置配置信息

       */

        //加载动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            /**
             * 动画开始执行的时候回调
             * @param animation
             */
            @Override
            public void onAnimationStart(Animation animation) {

            }

            /**
             * 动画结束的时候回调
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            /**
             * 动画重复执行的时候回调
             * @param animation
             */
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //给ImageView设置动画
        ivSplash.startAnimation(animation);
    }
}
