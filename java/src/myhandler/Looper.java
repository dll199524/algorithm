package myhandler;

public class Looper {

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    MessageQueue mQueue;
    
    public Looper() {
        mQueue = new MessageQueue();
    }


    public static void prepare() {
        sThreadLocal.set(new Looper());
    }
    
    
    public static void loop() {
        Looper looper = myLooper();
        for (;;) {
            MessageQueue queue = looper.mQueue;
            Message message = queue.next();
            if (message == null) return;
            message.target.handleMessage(message);
        }
    }


    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    
}
