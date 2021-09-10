package myhandler;

public class ActivityThread {

    final H mH = new H();
    public void attach(boolean b) {
        Activity mainActivity = new MainActivity();
        mainActivity.onCreate();
        Message message = new Message();
        message.object = mainActivity;
        mH.sendMessage(message);

    }



    private static class H extends Handler {
        @Override
        public void handleMessage(Message message) {
            Activity mActivity = (Activity) message.object;
            mActivity.onResume();
        }
    }
    
}
