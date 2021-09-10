package myhandler;

public class MainActivity extends Activity{

    private TextView mTextView;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            mTextView.setText((String)message.object);
            System.out.println("线程名称2: " + Thread.currentThread());
        };
    };

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate 执行了");
        mTextView = findViewById(R.id.text_view);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程名称1 " + Thread.currentThread());
                Message message = new Message();
                message.object = "后台数据";
                mHandler.sendMessage(message);
            };
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume 执行了");
    }
    
}
