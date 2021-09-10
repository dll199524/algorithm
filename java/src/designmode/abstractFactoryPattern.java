package designmode;

public class abstractFactoryPattern {
    public static void main(String[] args) {
        
    }
}

interface Button {
    void display();
}

class SpringButton implements Button {

    @Override
    public void display() {
        System.out.println("显示绿色按钮");
    }
    
}

class SummerButton implements Button {

    @Override
    public void display() {
        System.out.println("显示蓝色按钮");
    }

}

interface TextField {
    void display();
}

class SpringTextField implements TextField {

    @Override
    public void display() {
        System.out.println("显示绿色文本框");
    }

}

class SummerTextField implements TextField {

    @Override
    public void display() {
        System.out.println("显示蓝色文本框");
    }

}

interface Combox {
    void display();
}

class SpringCombox implements Combox {

    @Override
    public void display() {
        System.out.println("显示绿色组合框");
    }

}

class SummerCombox implements Combox {

    @Override
    public void display() {
        System.out.println("显示蓝色组合框");
    }

}

interface skinFactory {
    public Button creaButton();
    public TextField creaTextField();
    public Combox createCombox();
}

class SpringFactory implements skinFactory {

    @Override
    public Button creaButton() {
        return new SpringButton();
    }

    @Override
    public TextField creaTextField() {
        return new SpringTextField();
    }

    @Override
    public Combox createCombox() {
        return new SpringCombox();
    }

}

class SummerFacotry implements skinFactory {

    @Override
    public Button creaButton() {
        return new SummerButton();
    }

    @Override
    public TextField creaTextField() {
        return new SummerTextField();
    }

    @Override
    public Combox createCombox() {
        return new SummerCombox();
    }

}