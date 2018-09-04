/**
 * 抽象工厂模式
 */
interface Food{};
interface FoodFactory{
    Food getFood();
}
class Apple implements Food{}
class AppleFacotry implements  FoodFactory{

    @Override
    public Food getFood() {
        return new Apple();
    }
}
class Fruit implements Food{}
class FruitFactory implements FoodFactory{

    @Override
    public Food getFood() {
        return new Fruit();
    }
}

public class FactoryMode{
    public static void main(String[] args){

    }
}
