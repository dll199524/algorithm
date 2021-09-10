package myhandler;

public class MessageQueue {

    private Message mMessage;

    public boolean enqueueMessage(Message msg, long when) {
        synchronized(this) {
            msg.when = when;
            Message p = mMessage;
            if (p == null || when == 0 || msg.when < p.when) {
                msg.next = p;
                mMessage = msg;
            } else {
                Message prev;
                for(;;) {
                    prev = p;
                    p.next = p;
                    if (p == null || when < p.when) break;
                }
                msg.next = p;
                prev.next = msg;
            }
        }
        return true;
    }

    public Message next() {
        int pendingIdleHandlerCount = -1;
        for(;;) {
            synchronized(this) {
                final long now = System.currentTimeMillis();
                Message prev = null;
                Message msg = mMessage;
                if (msg != null && msg.target == null) {
                    do {
                        prev = msg;
                        msg = msg.next;
                    } while (msg != null);
                }
                if (msg != null) {
                    if (now < msg.when) {

                    } else {
                        if (prev != null) {
                            prev.next = msg.next;
                        } else {
                            mMessage = msg.next;
                        }
                        msg.next = null;
                        return msg;
                    }
                } else {

                }

                if (pendingIdleHandlerCount <= 0) continue;
            }
        } 

    }
    
}
