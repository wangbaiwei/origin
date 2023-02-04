package com.wbw.abstractfactory;

import com.wbw.Dir;
import com.wbw.Group;
import com.wbw.TankFrame;

public class RectFactory extends GameFactory{
    @Override
    public BaseBullet creteateBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x, y, dir, tf, group);
    }

    @Override
    public BaseExplode creteateExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }

    @Override
    public BaseTank creteateTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return null;
    }
}
