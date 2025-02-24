package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int step=0;
    //JFrame是窗体的意思
    //子类也表示窗体
    //规定本类就是游戏的主界面
    //与游戏相关的所有逻辑都写在本类中；
    JMenuItem replayItem =new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");


    int[][] win ={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    int[][] data =new int[4][4];
    public GameJFrame(){
        initJFrame();

        initJMeunbar();

        initDate();

        initimage();

        this.setVisible(true);

    }


    int x=0;
    int y=0;
    private void initDate() {
        int[] temparr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for(int i=0;i<temparr.length;i++){
            Random r = new Random();
            int temp=temparr[0];
            int i1 = r.nextInt(temparr.length);
            temparr[0]=temparr[i1];
            temparr[i1]=temp;
        }


        int k=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4;j++) {
                if(temparr[k]==0){
                    x=i;
                    y=j;
                }
                data[i][j]=temparr[k];
                k++;
            }
        }

    }

    private void initimage() {

        this.getContentPane().removeAll();


        Boolean victory = victory();

        if(victory){
            JLabel panel = new JLabel(new ImageIcon("C:\\Users\\legion\\IdeaProjects\\puzzlegame\\PuzzleGame\\image\\win.png"));
            panel.setBounds(203,283,197,73);
            this.getContentPane().add(panel);
        }

        JLabel stepCount = new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j=0;j < 4;j++){
                ImageIcon icon1 = new ImageIcon("C:\\Users\\legion\\IdeaProjects\\puzzlegame\\PuzzleGame\\image\\animal\\animal3\\"+data[i][j]+".jpg");
                JLabel label1 = new JLabel(icon1);
                label1.setBounds(j*105+83, i*105+134, 105, 105);
                label1.setBorder(new BevelBorder(BevelBorder.RAISED));
                this.getContentPane().add(label1);
            }
        }
        ImageIcon bg = new ImageIcon("C:\\Users\\legion\\IdeaProjects\\puzzlegame\\PuzzleGame\\image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initJMeunbar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu abourtdJMenu = new JMenu("关于我们");

        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        abourtdJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(abourtdJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680 );
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon("C:\\Users\\legion\\IdeaProjects\\puzzlegame\\PuzzleGame\\image\\animal\\animal3\\all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            ImageIcon bg = new ImageIcon("C:\\Users\\legion\\IdeaProjects\\puzzlegame\\PuzzleGame\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

        if(victory()){
            return;
        }


        int code = e.getKeyCode();
        //左边37，上38，右边39，下面40
        if(code == 37){
            if (y+1 <= 3) {
                System.out.println("向左移动");
                data[x][y]=data[x][y+1];
                data[x][y+1]=0;
                y++;
                step++;
                initimage();
            }
        }else if(code == 38){
            System.out.println("向上移动");
            if (x+1 <= 3) {
                data[x][y]=data[x+1][y];
                data[x+1][y]=0;
                x++;
                step++;
                initimage();
            }


        }else if(code == 39){
            System.out.println("向右移动");
            if (y-1 >= 0) {
                data[x][y]=data[x][y-1];
                data[x][y-1]=0;
                y--;
                step++;
                initimage();
            }

        }else if(code == 40){
            System.out.println("向下移动");
            if (x-1 >= 0) {
                data[x][y]=data[x-1][y];
                data[x-1][y]=0;
                x--;
                step++;
                initimage();
            }
        }else if(code == 65){
            initimage();
        }else if(code == 87){
            data=new int[][]{
                    {1,2,3,4}
                    ,{5,6,7,8}
                    ,{9,10,11,12}
                    ,{13,14,15,0}
            };
            initimage();
        }
    }

    public Boolean victory(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j]!=win[i][j])
                    return false;
            }
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            step=0;
            initDate();
            initimage();
        }
        if (obj == reloginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }
        if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        }
        if (obj == accountItem) {
            System.out.println("公众号");
            JDialog jd = new JDialog();
            JLabel j =new JLabel(new ImageIcon("PuzzleGame/image/about.png"));
            j.setBounds(0,0,258,258);
            jd.getContentPane().add(j);
            jd.setSize(344,344);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            jd.setModal(true);
            jd.setVisible(true);
        }
    }
}



