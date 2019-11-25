package functional;

// 함수형 인터페이스의 선언
@FunctionalInterface
interface Math {
    int value(int x, int y);
}

public class FunctionalInterfaceEx {
    // 내 생각인데, 반복되는 람다식을 표현할 때는 함수형 인터페이스를 선언하면 간결해 질 것 같음
    public static void main(String[] args) {
        Math min = (x, y) -> x < y ? x : y;    // 추상 메소드의 구현
        Math max = (x, y) -> x > y ? x : y;    // 추상 메소드의 구현

        // 함수형 인터페이스의 사용
        System.out.println(min.value(3, 4));
        System.out.println(max.value(3, 4));
        System.out.println(max.value(3, 5));
    }
}
