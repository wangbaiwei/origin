package com.wbw;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Data
@Setter
@Getter
public class Tank {

    private int x, y;
    private Dir dir;
    final static int SPEED = 5;
    public boolean moving = true;
    private TankFrame tankFrame;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    private Rectangle rect = new Rectangle();

    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.group = group;
        this.tankFrame = tankFrame;
        this.x = x;
        this.y = y;
        this.dir = dir;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {

        if (!living) tankFrame.tanks.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
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
            this.fire();
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

    /**
     * 随机方向
     */
    private void randomDir() {
        if (group == Group.BAD) {
            this.dir = Dir.values()[random.nextInt(4)];
        }
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        this.tankFrame.bullets.add(new Bullet(bx, by, this.dir, tankFrame, this.group));
    }

    public void die() {
        this.living = false;
    }
}
