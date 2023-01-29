package factory2;

public class CarFactory extends Factory{
    @Override
    Movable create() {
        return new Car();
    }
}
