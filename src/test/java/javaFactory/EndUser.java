package javaFactory;

public class EndUser {
    public static void main(String[] args) {
        //Car factory
        Car car = null;

        //morning
        car=getCar("Honda");
        car.viewCar();
        car.driveCar();

        //afternoon
        car=getCar("Toyota");
        car.viewCar();
        car.driveCar();

        //afternoon
        car=getCar("Huyndai");
        car.viewCar();
        car.driveCar();
    }

    public static Car getCar(String carName) {
        Car car = null;
        switch (carName){
            case "Honda":
                car=new Honda();
                break;
            case "Toyota":
                car=new Toyota();
                break;
            case "Huyndai":
                car=new Huyndai();
                break;
            default:
                throw new RuntimeException("Car name is not support");
        }
        return car;
    }
}
