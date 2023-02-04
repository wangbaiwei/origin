package com.wbw;

import com.wbw.abstractfactory.BaseTank;
import com.wbw.abstractfactory.RectBullet;
import com.wbw.abstractfactory.RectExplode;

public class FourDirFireStrategy implements  FireStrategy{
    @Override
    public void fire(BaseTank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - RectBullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - RectExplode.HEIGHT / 2;

        Dir[] dirs = Dir.values();

        for (Dir dir : dirs) {
            t.getTankFrame().getGameFactory().creteateBullet(bx, by, dir, t.getGroup(), t.getTankFrame());
        }
        if (t.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav"));
    }
}
