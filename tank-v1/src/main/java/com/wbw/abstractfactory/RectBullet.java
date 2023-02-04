package com.wbw.abstractfactory;

import com.wbw.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
    private static final int SPEED = 50;
    private int x, y;
    private Dir dir;

    public static int WIDTH = ResourceMgr.getInstance().bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().bulletL.getHeight();
    private TankFrame tf;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();


    public RectBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.group = group;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        this.tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
//        switch (dir) {
//            case LEFT:
//                g.drawImage(ResourceMgr.getInstance().bulletL, x, y, null);
//                break;
//            case RIGHT:
//                g.drawImage(ResourceMgr.getInstance().bulletR, x, y, null);
//                break;
//            case UP:
//                g.drawImage(ResourceMgr.getInstance().bulletU, x, y, null);
//                break;
//            case DOWN:
//                g.drawImage(ResourceMgr.getInstance().bulletD, x, y, null);
//                break;

        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, 20, 20);
        g.setColor(color);


//        }
        move();
    }

    @Override
    public void collideWith(RectTank tank) {
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
            int ex = this.x + Tank.WIDTH / 2 - RectExplode.WIDTH / 2;
            int ey = this.y + Tank.HEIGHT / 2 - RectExplode.HEIGHT / 2;
            tf.getExploreds().add(tf.gameFactory.creteateExplode(ex, ey, tf));
        }
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


}
