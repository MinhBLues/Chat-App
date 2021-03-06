package com.constraints;

import javax.swing.*;
import java.awt.*;

public class ImageConstraints {
    public static final Image img_logo = new ImageIcon("src/main/java/com/view/image/penguin-icon.png").getImage().getScaledInstance(188, 200, Image.SCALE_SMOOTH);
    public static final  Image img_send = new ImageIcon("src/main/java/com/view/image/icon-send.png").getImage().getScaledInstance(45, 32, Image.SCALE_SMOOTH);
    public static final  Image img_file = new ImageIcon("src/main/java/com/view/image/icons-file.png").getImage().getScaledInstance(45, 32, Image.SCALE_SMOOTH);
    /**
     * Logo LoginView and SignUpView
     * */
    public static final Image img_logo1 = new ImageIcon("src/main/java/com/view/image/penguin.png").getImage().getScaledInstance(293, 462, Image.SCALE_SMOOTH);
    public static final Image img_emoji = new ImageIcon("src/main/java/com/view/image/emoji.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    public static final Image img_logout = new ImageIcon("src/main/java/com/view/image/logout.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
}
