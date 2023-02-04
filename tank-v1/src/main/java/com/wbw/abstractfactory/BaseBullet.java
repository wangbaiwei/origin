package com.wbw.abstractfactory;


import java.awt.*;

public abstract class BaseBullet{

    protected boolean living = true;

    public abstract void paint(Graphics g);

    public abstract void collideWith(RectTank rectTank);

    protected void die() {
        this.living = false;
    }

}
