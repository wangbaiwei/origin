package com.wbw;

import java.awt.*;

public class Explored {
    private int x, y;
    public static int WIDTH = ResourceMgr.exploreds[0].getWidth();
    public static int HEIGHT = ResourceMgr.exploreds[0].getHeight();
    private TankFrame tf;
    private int step = 0;


    public Explored(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.exploreds[step++], x, y, null);
        if (step >= ResourceMgr.exploreds.length) {
            tf.getExploreds().remove(this);
        }
    }
}
