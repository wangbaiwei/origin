package com.wbw;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();
        Object initTankount = PropertyMgr.get("initTankCount");
        int count = initTankount == null ? 5 : Integer.parseInt(initTankount + "");
        // 初始化敌方坦克
        for (int i = 0; i <count; i++) {
            tf.tanks.add(new Tank(200 + i * 80, 200, Dir.DOWN, tf, Group.BAD));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
