package pattern;

interface AbstractProductFactory {
    Product2 createProduct();
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        Product2 t1 = ProductFactory2.getProduct(new ComputerFactory("pc", 300000));
        Product2 c1 = ProductFactory2.getProduct(new TicketFactory("pc", 1500000));

        System.out.println(c1.toString());
        System.out.println(t1.toString());
    }
}

class ProductFactory2 {
    public static Product2 getProduct(AbstractProductFactory productFactory) {
        return productFactory.createProduct();
    }
}

class ComputerFactory implements AbstractProductFactory {
    private String name;
    private int price;

    public ComputerFactory(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Product2 createProduct() {
        return new Computer2(name, price);
    }
}

class TicketFactory implements AbstractProductFactory {
    private String name;
    private int price;

    public TicketFactory(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product2 createProduct() {
        return new Ticket2(name, price);
    }
}

abstract class Product2 {
    //    public abstract String getName();
    //
    //    public abstract int getPrice();
    //
    //    @Override
    //    public String toString() {
    //        return "(product) name : " + getName() + ", price : " + getPrice();
    //    }
}

class Computer2 extends Product2 {
    private String name;
    private int price;

    public Computer2(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //
    //    @Override
    //    public String getName() {
    //        return this.name;
    //    }
    //
    //    @Override
    //    public int getPrice() {
    //        return this.price;
    //    }
}

class Ticket2 extends Product2 {
    private String name;
    private int price;

    public Ticket2(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //
    //    @Override
    //    public String getName() {
    //        return this.name;
    //    }
    //
    //    @Override
    //    public int getPrice() {
    //        return this.price;
    //    }
}