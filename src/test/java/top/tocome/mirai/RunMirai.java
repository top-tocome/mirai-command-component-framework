package top.tocome.mirai;

/**
 * 不作为 mirai-console-plugin 使用
 */
public class RunMirai {
    public static void main(String[] args) {
        FrameworkEntry.Instance
                //可登录多个qq
                .loginBot(123456, "")
//                .loginBot(123456, "")
                //注册组件
//                .loadComponent(c1)
//                .loadComponent(c2)
                //运行
                .run();
    }
}
