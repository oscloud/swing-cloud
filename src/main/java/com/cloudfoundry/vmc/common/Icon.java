package com.cloudfoundry.vmc.common;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.cloudfoundry.vmc.CloudConstants;

public class Icon {

    public static URL U(String url) {
        return Icon.class.getClass().getResource(CloudConstants.conf.IMG_PATH + url);
    }
    
    public static ImageIcon I(String url) {
        return new ImageIcon(U(url));
    }
    
    public static Image IM(String url) {
        return I(url).getImage();
    }
}
