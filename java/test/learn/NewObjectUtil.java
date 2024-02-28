package com.njust.learn;

import java.io.File;

public class NewObjectUtil {

    public String getFileName(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            return file.getName();
        }
        return null;
    }

}
