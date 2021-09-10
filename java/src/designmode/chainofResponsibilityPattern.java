package designmode;

public class chainofResponsibilityPattern {
    public static void main(String[] args) {
        Approver wjz, gy, jg, meeting;
        wjz = new Director("张无忌");
        gy = new VicePresident("杨过");
        jg = new President("郭靖");
        meeting = new Congress("董事会");
        wjz.setSuccessor(gy);
        gy.setSuccessor(jg);
        jg.setSuccessor(meeting);

        PurchaseRequest pr1 = new PurchaseRequest(45000,10001,"购买倚天剑");
        wjz.processRequest(pr1);
        PurchaseRequest pr2 = new PurchaseRequest(60000,10002,"购买《葵花宝典》");
        wjz.processRequest(pr2);
        PurchaseRequest pr3 = new PurchaseRequest(160000,10003,"购买《金刚经》");
        wjz.processRequest(pr3);
        PurchaseRequest pr4 = new PurchaseRequest(800000,10004,"购买桃花岛");
        wjz.processRequest(pr4);
    }
}


class PurchaseRequest {
    private double amount;
    private int number;
    private String purpose;
    public PurchaseRequest(double amount, int number, String purpose) {
        this.amount = amount;
        this.number = number;
        this.purpose = purpose;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
}

abstract class Approver {
    protected Approver successor;
    protected String name;
    public Approver(String name) {
        this.name = name;
    }
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }
    public abstract void processRequest(PurchaseRequest request);
}

class Director extends Approver {

    public Director(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("主任" + this.name + "审批采购单：" + request.getNumber() + "，金 额：" 
            + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            this.successor.processRequest(request);
        }
    }

}

class VicePresident extends Approver {

    public VicePresident(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("副董事长" + this.name + "审批采购单：" + request.getNumber() + "，金 额：" 
            + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            this.successor.processRequest(request);
        } 
    }

}

class President extends Approver {

    public President(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            System.out.println("董事长" + this.name + "审批采购单：" + request.getNumber() + "，金 额：" 
            + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            this.successor.processRequest(request);
        }  
    }

}

class Congress extends Approver {

    public Congress(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        System.out.println("召开董事会审批采购单：" + request.getNumber() + "，金额：" 
        + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
    }

}