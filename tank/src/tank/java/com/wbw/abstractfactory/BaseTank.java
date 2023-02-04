package com.wbw.abstractfactory;

import com.wbw.Dir;
import com.wbw.Group;
import com.wbw.TankFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class BaseTank {

    protected boolean living = true;

    protected int x, y;
    protected Dir dir;
    protected Group group = Group.BAD;
    protected TankFrame tankFrame;
    private Rectangle rect = new Rectangle();


    public abstract void paint(Graphics g);
    protected void die() {
        this.living = false;
    }
}
