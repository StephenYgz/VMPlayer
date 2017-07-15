package itheima.mvp.dagger;

import dagger.Component;
import itheima.mvp.MainActivity;

/**
 * Created by wschun on 2016/12/20.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void in(MainActivity mainActivity);
}
