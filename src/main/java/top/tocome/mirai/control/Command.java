package top.tocome.mirai.control;

import top.tocome.mirai.component.CommandComponent;

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

    public Command(CommandComponent component, String key, Action action) {
        this.component = component;
        this.key = key;
        this.action = action;
    }

    /**
     * 供构造二级及以下指令使用
     *
     * @param key    指令关键词
     * @param action 指令行为
     */
    Command(String key, Action action) {
        this.key = key;
        this.action = action;
    }

    /**
     * 属于哪一个命令组件
     */
    protected CommandComponent component;
    /**
     * 触发指令的关键词
     */
    protected String key;
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
     */
    protected String[] paramsHint = new String[0];
    /**
     * 指令描述
     */
    protected String describe = "";

    public boolean match(String message) {
        if (message.startsWith(key)) {
            matchAction(message.replaceFirst(key, "").trim());
            return true;
        }
        return false;
    }

    protected void matchAction(String message) {
        String[] params = getParams(message);
        if (params.length == paramsHint.length) {
            action.run(params);
        } else
            component.getSubject().sendMessage(startRegex + "error:params not match\n");
    }

    protected String getHelp() {
        return getKey() + showParamsHint() + describeRegex + describe + "\n";
    }

    protected String getKey() {
        if (parentSet == null) {
            return startRegex + key;
        }
        return parentSet.getKey() + secondRegex + key;
    }

    protected String[] getParams(String message) {
        if (message.equals("")) return new String[0];
        return message.split(paramRegex);
    }

    protected String showParamsHint() {
        StringBuilder sb = new StringBuilder();
        for (String param : paramsHint)
            sb.append(paramRegex).append(param);
        return sb.toString().replaceFirst(paramRegex, "  ") + "  ";
    }

    public interface Action {
        void run(String[] params);
    }
}
