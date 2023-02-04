package com.wbw.abstractfactory;

import com.wbw.*;

public abstract class GameFactory {

    public abstract BaseBullet creteateBullet(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode creteateExplode(int x, int y, TankFrame tf);
    public abstract BaseTank creteateTank(int x, int y, Dir dir, Group group, TankFrame tf);


}
