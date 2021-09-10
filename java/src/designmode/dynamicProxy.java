// package designmode;

// import java.lang.reflect.InvocationHandler;
// import java.lang.reflect.Method;

// public class dynamicProxy {
//     public static void main(String[] args) {
        
//     }
// }

// interface Subject {
//     void request();
// }

// class RealSubject implements Subject {

//     @Override
//     public void request() {
//         System.out.println("------Real------Request------");
        
//     }

// }

// class ProxyHandler implements InvocationHandler {

//     private Subject subject;
//     public ProxyHandler(Subject subject) {
//         this.subject = subject;
//     }
//     @Override
//     public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
//         Object result = arg1.invoke(arg0, arg1);
//         return result;
//     }
   

// }