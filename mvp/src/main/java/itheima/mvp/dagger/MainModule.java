package itheima.mvp.dagger;

import dagger.Module;
import dagger.Provides;
import itheima.mvp.MainActivity;
import itheima.mvp.MainPresenter;

/**
 * 生成P实例的容器
 * Created by wschun on 2016/12/20.
 */
@Module
public class MainModule {
    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    MainPresenter provideMainPresenter(){
        return new MainPresenter(mainActivity);
    }
}
