package os.multiThread;

/*
    멀티스레드는 동시성 또는 병렬성으로 실행
    - 동시성 : 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행하는 성질
    - 병렬성 : 멀티 코어에서 개별 스레드를 동시에 실행하는 성질

    - 스레드 스케줄링
    ( 스레드의 개수 > 코어의 수 ) -> 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정하는 것
        1. 우선순위 방식 : 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링
            - 스레드 객체에 우선순위 번호를 부여할 수 있기 때문에 개발자가 코드로 제어 가능
            - 우선순위 변경은 thread.setPriority(Thread.MAX_PRIORITY) 와 같이 이용
        2. 시간할당량 ( time slice ) 를 정해서
           하나의 스레드를 정해진 시간만큼 실행시키고 다시 다른 스레드를 실행하는 방식
            - JVM에 의해 정해지기 때문에 코드로 제어 불가
 */

// 동기화 없는 멀티 스레드
public class withoutSynchronization {
    public static void main(String[] args) {
        PrintDemo printDemo = new PrintDemo();
        ThreadDemo t1 = new ThreadDemo("Thread - 1 ", printDemo);
        ThreadDemo t2 = new ThreadDemo("Thread - 2 ", printDemo);

        t1.start();
        t2.start();

        try {
            t1.join(); // 스레드가 종료될 때 까지 대기
            t2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}

class PrintDemo {
    public void printCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Counter --- " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted");
        }
    }

    public void printSyncCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Sync Counter --- " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted");
        }
    }
}

class ThreadDemo extends Thread {
    PrintDemo printDemo;
    private Thread thread;
    private String threadName;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        printDemo = pd;
    }

    public void run() {

        //동기화 하지 않는 경우
        printDemo.printCount();

        // 동기화 하는 경우
        synchronized (printDemo) {
            //            printDemo.printSyncCount();
        }

        System.out.println("Thread " + threadName + " end.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}