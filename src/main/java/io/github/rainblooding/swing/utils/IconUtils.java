package io.github.rainblooding.swing.utils;

import io.github.rainblooding.utils.FileUtils;

import javax.swing.*;

public class IconUtils {

    public static ImageIcon getIcon(String path) {
        try {
            return new ImageIcon(FileUtils.getFileByResource(path).toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
