package top.tocome.mirai.control;

import top.tocome.mirai.component.CommandComponent;
import top.tocome.mirai.utils.Logger;

/**
 * 单条指令
 */
public class Command {
    /**
     * 一级指令开始符号
     */
    public static String startRegex = ">";
    /**
     * 二三四... 级指令开始符号
     */
    public static String secondRegex = "-";
    /**
     * 参数分割符号
     */
    public static String paramRegex = ";;";
    /**
     * 描述开始符号
     */
    public static String describeRegex = "%";

    protected Command(CommandComponent component, String key, Action action) {
        this.component = component;
        this.key = key;
        this.action = action;
    }

    /**
     * 归属的命令组件
     */
    protected CommandComponent component;
    /**
     * 触发指令的关键词
     */
    public String key;
    /**
     * 指令匹配成功后执行的动作
     */
    protected Action action;
    /**
     * 上级指令
     */
    protected CommandSet parentSet = null;
    /**
     * 指令参数个数
     * 默认一个参数
     */
    protected String[] paramsHint = new String[]{""};
    /**
     * 指令描述
     */
    public String describe = "";

    /**
     * 匹配指令
     *
     * @param message 指令消息
     * @return 匹配结果
     */
    public boolean match(String message) {
        if (message.startsWith(key)) {
            matchAction(message.replaceFirst(key, "").trim());
            return true;
        }
        return false;
    }

    /**
     * 匹配成功后的行为
     *
     * @param message 参数消息
     */
    protected void matchAction(String message) {
        String[] params = message.split(paramRegex);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
        }
        if (params.length == paramsHint.length) {
            action.run(params);
            Logger.info(getTotalKey() + "  command matched and run");
        } else
            component.getSubject().sendMessage(startRegex + "error:params not match\n");
    }

    /**
     * 获取整条指令完整描述
     *
     * @return totalKey+paramsHint+describe
     */
    protected String getHelp() {
        return getTotalKey() + showParamsHint() + describeRegex + describe + "\n";
    }

    /**
     * 获取完整的指令关键词
     *
     * @return parentKey+key
     */
    protected String getTotalKey() {
        if (parentSet == null) {
            return startRegex + key;
        }
        return parentSet.getTotalKey() + secondRegex + key;
    }

    /**
     * 参数展示
     */
    protected String showParamsHint() {
        StringBuilder sb = new StringBuilder();
        for (String param : paramsHint)
            sb.append(paramRegex).append(param);
        return sb.toString().replaceFirst(paramRegex, "  ") + "  ";
    }

    /**
     * 指令行为接口
     */
    public interface Action {
        void run(String[] params);
    }
}
