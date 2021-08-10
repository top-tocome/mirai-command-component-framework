package top.tocome.mirai

import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import net.mamoe.mirai.console.util.ConsoleExperimentalApi

@ConsoleExperimentalApi
suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

    JavaPluginMain.INSTANCE.load()
    JavaPluginMain.INSTANCE.enable()

//    val bot = MiraiConsole.addBot(123456, "").alsoLogin() // 登录一个测试环境的 Bot

    MiraiConsole.job.join()
}