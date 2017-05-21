package com.ordermate.utils;

import javax.swing.*;
import java.net.URL;

/**
 * Created by edwin on 20/05/17.
 */
public class Utility {

    public static ImageIcon newIcon(String imageName) {
        ImageIcon icon=null;
        URL url=Utility.class.getClass().getResource(imageName);
        if(url==null) {
            icon=new ImageIcon();
        }
        else {
            icon=new ImageIcon(url);
        }
        return icon;
    }

}
