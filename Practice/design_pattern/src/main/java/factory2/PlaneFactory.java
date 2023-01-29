package factory2;

public class PlaneFactory extends Factory{
    @Override
    Movable create() {
        return new Plane();
    }
}
