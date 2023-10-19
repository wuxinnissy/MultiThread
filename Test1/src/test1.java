/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: test1
 * @Arthor: N1ssy2
 * @Create: 2023/10/19 9:32
 * @Version: 1.0
 * 创建两个分线程，让其中一个线程输出1-100之间的偶数，另一个线程输出1-100之间的奇数。
 **/

class EvenNumberRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 2; i <= 100; i += 2) {
            System.out.println(i);
        }
    }
}

class OddNumberRunnable implements Runnable{
    @Override
    public void run(){
        for(int i = 1;i<=100;i+=2){
            System.out.println(i);
        }
    }
}


public class test1 {
    public static void main(String[] args){
        // 创建一个实现了 Runnable 接口的类的实例来输出偶数
        EvenNumberRunnable evenNumberRunnable = new EvenNumberRunnable();
        // 创建一个新的线程，并将实现了 Runnable 接口的类的实例作为参数传递给 Thread 构造函数
        Thread evenThread = new Thread(evenNumberRunnable);

        // 创建一个实现了 Runnable 接口的类的实例来输出奇数
        OddNumberRunnable oddNumberRunnable = new OddNumberRunnable();
        // 创建一个新的线程，并将实现了 Runnable 接口的类的实例作为参数传递给 Thread 构造函数
        Thread oddThread = new Thread(oddNumberRunnable);

        // 启动两个线程
        evenThread.start();
        oddThread.start();
    }
}
