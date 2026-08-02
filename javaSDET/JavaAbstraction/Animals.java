package JavaAbstraction;

public abstract class Animals {
    // A, B dùng chung (non abstract method)
    public void eat(){
        System.out.println("Eating");
    }

    public void sleep(){
        System.out.println("Sleeping");
    }

    //C t implement lại (abstract method)
    public abstract void run();
}
