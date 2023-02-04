package com.wbw.abstractfactory;

import com.wbw.*;

public class DefaultFacotry extends GameFactory{
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
        return new Tank(x, y, dir, tf, group);
    }
}
