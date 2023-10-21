import java.util.concurrent.locks.ReentrantLock;

/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: test_lock
 * @Arthor: N1ssy2
 * @Create: 2023/10/21 20:32
 * @Version: 1.0
 * 使用ReentrantLock来解决线程安全问题
 * 问题：三个售票口售票
 **/

class Ticket implements Runnable{
    int ticket = 100;
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run(){
        try{
            lock.lock();

            while(ticket>0){
                System.out.println(Thread.currentThread().getName()+"："+ticket);
                ticket--;
            }
        }finally {
            lock.unlock();
        }
    }
}
public class test_lock {
    public static void main(String[] args) {
        Ticket s1 = new Ticket();

        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s1);
        Thread t3 = new Thread(s1);

        t1.setName("售票口1");
        t2.setName("售票口2");
        t3.setName("售票口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
