/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: Test_NumPrint
 * @Arthor: N1ssy2
 * @Create: 2023/10/21 21:04
 * @Version: 1.0
 * 两个线程交替打印数字
 **/

class num implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();//唤醒线程

                if (count <= 100) {
                    System.out.println(Thread.currentThread().getName() + "：" + count);
                    count++;
                    try {
                        wait();//线程进入等待状态
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}

public class Test_NumPrint {
    public static void main(String[] args) {
        num n = new num();

        Thread t1 = new Thread(n, "线程1");
        Thread t2 = new Thread(n, "线程2");

        t1.start();
        t2.start();
    }
}
