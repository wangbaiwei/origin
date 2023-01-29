package factory2;

public class Main {
    public static void main(String[] args) {
        Factory carFactory = new CarFactory();
        Movable movable = carFactory.create();
        movable.go();
    }
}
