package com.perfma.xlab.xpocket.command.impl;

import com.perfma.xlab.xpocket.spi.command.CommandInfo;
import com.perfma.xlab.xpocket.spi.process.XPocketProcess;
import com.perfma.xlab.xpocket.utils.XpocketLogPrint;

import java.util.Base64;

/**
 * @author xinxian
 * @create 2021-06-01 16:12
 **/
@CommandInfo(name = "request", usage = "Submit question to the community")
public class RequestCommand extends AbstractSystemCommand {

    @Override
    public void invoke(XPocketProcess process) throws Throwable {
        try {
            String filePath = XpocketLogPrint.filePath;
            String url = "https://club.perfma.com/create-question" + "?filePath=" + Base64.getEncoder().encodeToString(filePath.getBytes());
            java.net.URI uri = java.net.URI.create(url);
            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                // 获取系统默认浏览器打开链接
                dp.browse(uri);
                process.output("Please submit a question for help in the community");
            } else {
                process.output("There is no default browser on the system desktop");
            }
        } catch (java.lang.NullPointerException e) {
            // 此为uri为空时抛出异常
            e.printStackTrace();
        } catch (java.io.IOException e) {
            // 此为无法获取系统默认浏览器
            e.printStackTrace();
        } finally {
            process.end();
        }
    }
}
