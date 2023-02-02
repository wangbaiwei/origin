package com.wbw;

import com.wbw.abstractfactory.BaseExplode;

import java.awt.*;


public class Explored extends BaseExplode {
    private int x, y;
    public static int WIDTH = ResourceMgr.getInstance().exploreds[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().exploreds[0].getHeight();
    private TankFrame tf;
    private int step = 0;


    public Explored(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.getInstance().exploreds[step++], x, y, null);
        if (step >= ResourceMgr.getInstance().exploreds.length) {
            tf.getExploreds().remove(this);
        }
    }
}
