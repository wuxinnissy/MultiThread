/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: ProducerConsumer
 * @Arthor: N1ssy2
 * @Create: 2023/10/21 21:36
 * @Version: 1.0
 * 案例2: 生产者&消费者生产者(Productor)将产品交给店员(CLerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20)，如果生产者试图生产更多的产品，店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产，
 * 如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 * 分析:
 * 1.是否是多线程问题? 是，生产者、消费者
 * 2.是否有共享数据? 有! 共享数据是: 产品
 * 3.是否有线程安全问题? 有!因为有共享数据
 * 4.是否需要处理线程安全问题? 是! 如何处理? 使用同步机制
 * 5.是否存在线程间的通信? 在在
 **/

class Clerk{
    //店员
    private int product = 0;

    public synchronized void addProduct(){
        //生产
        if(product>=20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product++;
        System.out.println(Thread.currentThread().getName()+"生产了第"+product+"个产品");

        notify();
    }

    public synchronized void redProduct(){
        //消费
        if(product<=0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+"消费了第"+product+"个产品");
        product--;

        notify();
    }
}

class Producer extends Thread{
    //生产者
    private Clerk clerk;

    public Producer(Clerk c){
        this.clerk = c;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

class Consumer extends Thread{
    //消费者
    private Clerk clerk;

    public Consumer(Clerk c){
        this.clerk = c;
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.redProduct();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);

        p1.start();
        c1.start();
    }
}
