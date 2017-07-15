package itheima.mvp;

/**
 * Created by wschun on 2016/12/20.
 */

public class MainPresenter {
    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void login(String username, String password){
        final User user=new User();
        user.username=username;
        user.password=password;
        new Thread(){
            @Override
            public void run() {
                UserLoginNet net=new UserLoginNet();

                if(net.sendUserLoginInfo(user)){
                    // 登陆成功
                    mainActivity.success();
                }else{
                    //登陆失败
                    mainActivity.failed();
                }

            }
        }.start();
    }
}
