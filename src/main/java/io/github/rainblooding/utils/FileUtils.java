package io.github.rainblooding.utils;

import java.io.File;

public class FileUtils {

    public static File getFileByResource(String filename) {
        String path = FileUtils.class.getClassLoader().getResource("").getPath();
        return new File(path + filename);
    }
}
