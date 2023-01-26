package com.wbw;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 50;
    private int x, y;
    private Dir dir;

    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.bulletL.getHeight();
    private boolean living = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();



    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.group = group;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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

        rect.x = this.x;
        rect.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGH) {
            this.living = false;
        }
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }

        // TODO: 用一个rect来记录子弹的位置
//        Rectangle rectOfBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        rect.x = this.x;
        rect.y = this.y;
        if (rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
            int ex = this.x + Tank.WIDTH / 2 - Explored.WIDTH / 2;
            int ey = this.y + Tank.HEIGHT / 2 - Explored.HEIGHT / 2;
            tf.getExploreds().add(new Explored(ex, ey, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
