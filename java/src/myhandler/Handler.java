package myhandler;


public class Handler {
    private MessageQueue mQueue;
    public Handler() {
        Looper looper = Looper.myLooper();
        if (looper == null) {
            throw new RuntimeException(
                "Only the original thread that created a view hierarchy can touch its views.");
        }
        mQueue = looper.mQueue;
    }

    public void sendMessage(Message message) {
        sendMessageDelay(message, 0);
    }

    private boolean sendMessageDelay(Message message, int delayMillis) {
        if (delayMillis < 0) delayMillis = 0;
        return sendMessageAtTime(message, System.currentTimeMillis() + delayMillis);
    }

    private boolean sendMessageAtTime(Message message, long uptimeMillis) {
        MessageQueue queue = mQueue;
        return enqueueMessage(queue, message, uptimeMillis);
    }

    private boolean enqueueMessage(MessageQueue queue, Message message, long uptimeMillis) {
        message.target = this;
        return queue.enqueueMessage(message, uptimeMillis);
    }

    public void handleMessage(Message message) {

    }

}
