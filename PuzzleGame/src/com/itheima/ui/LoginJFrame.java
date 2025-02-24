package com.itheima.ui;

import javax.swing.*;
import java.awt.*;

public class LoginJFrame extends JFrame {
    //本类表示登录界面，用于存放登录相关逻辑


    public LoginJFrame(){
        this.setSize(488,430 );
        this.setTitle("拼图 登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
