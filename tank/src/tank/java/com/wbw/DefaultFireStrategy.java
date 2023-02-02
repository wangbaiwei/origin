package com.wbw;


public class DefaultFireStrategy implements  FireStrategy{
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bx, by, t.dir, t.getTankFrame(), t.getGroup());
        if (t.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav"));
    }
}
