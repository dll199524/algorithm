package designmode;

//装饰器模式
public class decoratorPattern {
    public static void main(String[] args) {
        Component component, component2;
        component = new Window();
        component2 = (new ScollorBarDecorator(component));
        component2.display();
    }
}

abstract class Component {
    public abstract void display();
}


class Window extends Component {

    @Override
    public void display() {
        System.out.println("显示窗体");
    }

}

class TextBox extends Component {

    @Override
    public void display() {
       System.out.println("显示文本"); 
    }

}

class ComponentDecorator extends Component {

    private Component component;
    public ComponentDecorator (Component component) {
        this.component = component;
    }
    @Override
    public void display() {
        component.display();
    }

}

class ScollorBarDecorator extends ComponentDecorator {


    public ScollorBarDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        setScollorBar();
        super.display();
    }

    public void setScollorBar() {
        System.out.println("为构件增加滚动条");
    }

}

class BlackBorderDecorator extends ComponentDecorator {

    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        setBlackBorder();
        super.display();
    }

    public void setBlackBorder() {
        System.out.println("为构件添加黑色边框");
    }

}
