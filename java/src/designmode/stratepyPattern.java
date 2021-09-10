package designmode;

//策略模式
public class stratepyPattern {
    public static void main(String[] args) {
        MovieTicket ticket = new MovieTicket();
        ticket.setPrice(20);
        Discount discount = new VipDiscount();
        ticket.setDiscount(discount);
        double price = ticket.getPrice();
        System.out.println(price);
    }
    
    
}

class MovieTicket {
    private double price;
    private Discount discount;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return discount.calculate(price);
    }

}

interface Discount {
    double calculate(double price);
}

class StudentDiscount implements Discount {

    @Override
    public double calculate(double price) {
        System.out.println("学生票");
        return price * 0.8;
    }

}

class VipDiscount implements Discount {

    @Override
    public double calculate(double price) {
        System.out.println("vip票");
        return price * 0.5;
    }

}