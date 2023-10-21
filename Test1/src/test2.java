/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: test2
 * @Arthor: N1ssy2
 * @Create: 2023/10/21 19:47
 * @Version: 1.0
 * 银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 **/

class bank implements Runnable{
    static int money = 0;
    @Override
    public void run(){
        synchronized (this){
            for(int i=0;i<3;i++)
            {
                money += 1000;
                System.out.println(Thread.currentThread().getName()+"存了1000元，账户余额为："+money);
            }
        }
    }
}

public class test2 {
    public static void main(String[] args){
        bank b1 = new bank();

        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b1);

        t1.setName("账户1");
        t2.setName("账户2");

        t1.start();
        t2.start();
    }
}
