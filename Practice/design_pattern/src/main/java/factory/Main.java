package factory;

public class Main {

    public static void main(String[] args) {
        AbstractFactory factory = new ModernFactory();
        Food food = factory.createFood();
        Vehicle vehicle = factory.createVehicle();
        Weapon weapon = factory.createWeapon();



    }
}
