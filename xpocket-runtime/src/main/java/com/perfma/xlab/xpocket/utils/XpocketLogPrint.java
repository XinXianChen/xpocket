package com.perfma.xlab.xpocket.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * It is used to record the user's behavior trajectory and is used when submitting for help
 * @author xinxian
 * @create 2021-06-01 14:14
 **/
public class XpocketLogPrint {

    private static FileWriter fileWriter;

    public static String filePath;

    private static final Pattern ansiRemovePattern = Pattern.compile("(@\\|\\w* )|( ?\\|@)");

    private static final Pattern colorRemovePattern = Pattern.compile("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]");

    static {
        try {
            filePath = System.getProperty("user.dir") + File.separator + ".xpocket.log";
            fileWriter = new FileWriter(filePath,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write2log(String msg) {
        try {
            fileWriter.write(replaceMsg(msg));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceMsg(String msg) {
        final String tmp = ansiRemovePattern.matcher(msg)
                .replaceAll("");
        return colorRemovePattern.matcher(tmp)
                .replaceAll("");
    }

    public static void release() {
        try {
            FileUtils.forceDelete(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
