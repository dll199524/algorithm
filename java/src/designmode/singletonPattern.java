package designmode;

public class singletonPattern {
    
}

class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {}
    public EagerSingleton getInstance() {return instance;}
}

class LazySingleton {
    private volatile static LazySingleton instance = null;
    private LazySingleton() {}
    public LazySingleton getInstance() {
        if (instance == null) {
            synchronized(LazySingleton.class) {
                if (instance == null) {instance = new LazySingleton();}
            }
        }
        return instance;
    }
}

class Singleton {
    private Singleton() {}
    private static class HolderClass {
        private static Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {return HolderClass.instance;}
}