package com.wbw.abstractfactory;

import com.wbw.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class RectTank extends BaseTank{

    final static int SPEED = 5;
    public boolean moving = true;
    public static int WIDTH = ResourceMgr.getInstance().goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().goodTankU.getHeight();
    private boolean living = true;
    private Random random = new Random();

    private Rectangle rect = new Rectangle();
    private FireStrategy fireStrategy;

    public RectTank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.group = group;
        this.tankFrame = tankFrame;
        this.x = x;
        this.y = y;
        this.dir = dir;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if (group == Group.GOOD) {
            String goodFs = (String)PropertyMgr.get("goodFs");
            try {
                fireStrategy = (FourDirFireStrategy) Class.forName(goodFs).getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String badFs = (String)PropertyMgr.get("badFs");
            try {
                fireStrategy = (DefaultFireStrategy)Class.forName(badFs).getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        if (!living) tankFrame.tanks.remove(this);

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(this.x, this.y, 40, 40);
        g.setColor(color);

        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }


        if (this.group == Group.BAD && random.nextInt(100) > 80) {
            this.fireStrategy.fire(this);
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        // 边界检查
        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundsCheck() {
        if (this.x < 0) x = 2;
        if (this.y < 30) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGH - Tank.HEIGHT) y = TankFrame.GAME_HEIGH - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        if (group == Group.BAD) {
            this.dir = Dir.values()[random.nextInt(4)];
        }
    }


}
