package top.tocome.mirai.component.contact;

public class Bot extends ContactOrBot {

    public Bot(long id) {
        super(id);
    }

    @Override
    protected void commandSetting() {

    }

    @Override
    protected boolean common() {
        return false;
    }
}
