package pattern;

/*
    싱글톤 패턴은 한번의 메모리 적재로 여러 차례 이용할 수 있어
    메모리 효율이 좋다.

    싱글톤 패턴을 이용하면 다른 클래스으 인스턴스간의 결합도가 높아진다.
    이는 객체지향언어로 적합하지 않기 때문에 싱글톤은 지양하도록 한다.

 */

public class Singleton {
    public static void main(String[] args) {

    }
}

class Single {
    private Single() {
    }

    public static Single getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        // 인스턴스 변수로 사용하면 클래스가 로드될 때, 사용하지 않아도 메모리에 할당된다.
        // 내부 클래스에 할당하면 getInstance()로 접근 할 떄 할당 되기 때문에 메모리 낭비를 방지
        public static final Single INSTANCE = new Single();
    }
}