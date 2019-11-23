package pattern;

/*
    부모에게 to String 있기 때문에 자식에서 toString 안해 줘도 된다.
    super class와 여러개의 sub class 가 있는 상황에서
    input이 발생하면 하나의 sub class를 반환해야 할 때 사용
    factory class는 client class로부터 인스턴스를 생성하는 책임을 가져온다.

    super class는 interface, abstract class 또는 일반적인 java class
    getProduct를 static으로 선언해서 singleton 유지


 */

public class FactoryPattern {
    public static void main(String[] args) {
        Product t1 = ProductFactory.getProduct("ticket", "한국여행", 300000);
        Product c1 = ProductFactory.getProduct("computer", "pc", 1500000);

        System.out.println(c1.toString());
        System.out.println(t1.toString());
    }
}

class ProductFactory {
    public static Product getProduct(String type, String name, int price) {
        // static으로 안하면 불필요한 인스턴스 가 생성됨.
        // 어떤 인스턴스던지간에 똑같은 일을 하기 때문에 인스턴스 생성 불필요
        if ("ticket".equals(type)) {
            return new Ticket(name, price);
        } else if ("computer".equals(type)) {
            return new Computer(name, price);
        }
        return null;
    }
}

abstract class Product {
    public abstract String getName();

    public abstract int getPrice();

    @Override
    public String toString() {
        return "(product) name : " + getName() + ", price : " + getPrice();
    }
}

class Computer extends Product {
    private String name;
    private int price;

    public Computer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    // 오버라이드
    public String toString() {
        return "항목 : " + getName() + ", price : " + getPrice();
    }
}

class Ticket extends Product {
    private String name;
    private int price;

    public Ticket(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    //    public String toString() {
    //        return "항목 : " + getName() + ", price : " + getPrice();
    //    }
}