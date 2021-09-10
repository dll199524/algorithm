package designmode;

import java.util.HashMap;

//享元模式
public class flyWeightPattern {

    private static final String colors[] = 
      { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Circle circle = (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int)(Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int)(Math.random() * 100);
    }

    private static int getRandomY() {
        return (int)(Math.random() * 100);
    }


}

interface Shape {
    public void draw();
}

class Circle implements Shape {

    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {this.color = color;}
    
    public int getX() {return x;}

    public void setX(int x) {this.x = x;}

    public int getY() {return y;}

    public void setY(int y) {this.y = y;}

    public int getRadius() {return radius;}

    public void setRadius(int radius) {this.radius = radius;}

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color 
         +", x : " + x +", y :" + y +", radius :" + radius);
    }
    
}

class ShapeFactory {
    private final static HashMap<String, Shape> circleMap = new HashMap<>();
    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("create circle of color : " + color);
        }
        return circle;
    }
}
