package com.wbw.abstractfactory;

import com.wbw.Audio;
import com.wbw.ResourceMgr;
import com.wbw.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode{
    private int x, y;
    public static int WIDTH = ResourceMgr.getInstance().exploreds[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().exploreds[0].getHeight();
    private TankFrame tf;
    private int step = 0;


    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
//        g.drawImage(ResourceMgr.getInstance().exploreds[step++], x, y, null);

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;

        if (step >= 15) {
            tf.getExploreds().remove(this);
        }
        g.setColor(color);
    }
}
