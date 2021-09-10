package algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class threadPool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable(){

                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程，执行耗时任务，线程是：" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                }
                
            });
        }
    }
}
