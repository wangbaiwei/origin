package com.wbw;


import com.wbw.abstractfactory.BaseTank;
import com.wbw.abstractfactory.RectBullet;

public class DefaultFireStrategy implements  FireStrategy{
    @Override
    public void fire(BaseTank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - RectBullet.WIDTH / 2;
        int by = t.getY()+ Tank.HEIGHT / 2 - RectBullet.HEIGHT / 2;
        new RectBullet(bx, by, t.getDir(), t.getTankFrame(), t.getGroup());
        if (t.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav"));
    }
}
