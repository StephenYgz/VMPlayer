package com.itheima.vmplayer.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.dagger.component.fragment.DaggerMusicComponent;
import com.itheima.vmplayer.dagger.module.fragment.MusicModule;
import com.itheima.vmplayer.presenter.fragment.MusicPresenter;
import com.itheima.vmplayer.ui.activity.MusicPlayerActivity;
import com.itheima.vmplayer.utils.CommUtils;
import com.itheima.vmplayer.utils.MyCursorAdapter;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/20.
 */

public class MusicFragment extends BaseFragment {
    private static final int READ_SD_CODE = 0;
    //让Dagger2工具知道musicPresenter需要注入
    @Inject
    public MusicPresenter musicPresenter;
    @BindView(R.id.lv_music)
    ListView lvMusic;
    private MyCursorAdapter myCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;

    }

    private void initView() {
        myCursorAdapter = new MyCursorAdapter(getActivity(), null);
        lvMusic.setAdapter(myCursorAdapter);

        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) myCursorAdapter.getItem(position);

                Intent mIntent=new Intent(getActivity(), MusicPlayerActivity.class);
                mIntent.putExtra("musicBeanList", (Serializable) CommUtils.getMusicLists(cursor));
                mIntent.putExtra("position",position);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerMusicComponent.builder().musicModule(new MusicModule(this)).build().in(this);
        //判断读取SD的权限是否已经获取，已经获取就直接查询数据，申请权限
        // 6.0开始对于危险的权限需要用户确认
//           1，普通的权限
//                网络权限，
//           2，危险的权限
//                读取SD卡的权限，读取联系人的权限(6.0后需要通过用户的确认)

        int permission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            //申请权限
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_SD_CODE);
        } else {
            musicPresenter.queryData(getActivity(),myCursorAdapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_SD_CODE) {
            int result = grantResults[0];
            if (result == PackageManager.PERMISSION_GRANTED) {
                musicPresenter.queryData(getActivity(),myCursorAdapter);
            } else {
                Toast.makeText(getActivity(), "权限未通过", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
