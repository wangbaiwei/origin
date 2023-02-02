package com.wbw;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public BufferedImage badTankL, badTankU, badTankR, badTankD;
    public BufferedImage bulletL, bulletU, bulletR, bulletD;
    public BufferedImage[] exploreds = new BufferedImage[16];

    private ResourceMgr() {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);


            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                exploreds[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class Instance{
        private static final ResourceMgr instance = new ResourceMgr();
    }

    public static ResourceMgr getInstance() {
        return Instance.instance;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                ResourceMgr instance = ResourceMgr.getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
