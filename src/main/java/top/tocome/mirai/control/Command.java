package top.tocome.mirai.control;

import top.tocome.mirai.component.AttachedComponent;

/**
 * 单条指令
 */
public class Command {

    public static String startRegex = ">";
    public static String secondRegex = "-";
    public static String paramRegex = ";;";
    public static String describeRegex = "%";

    public Command(AttachedComponent component, String key) {
        this.component = component;
        this.key = key;
    }

    protected AttachedComponent component;
    protected CommandSet parentSet;
    protected String key;
    protected String[] paramsHint = new String[0];
    protected String describe;
    protected Action action;

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
        return parentSet.getKey() + startRegex + key;
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
