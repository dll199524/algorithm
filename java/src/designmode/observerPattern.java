package designmode;

import java.util.ArrayList;

public class observerPattern {
    public static void main(String[] args) {
        AllyControlCenter acc;
        acc = new ContentControlCenter("金庸群侠");
        Observer player1, player2, player3, player4;
        player1 = new Player("郭靖");
        player2 = new Player("黄蓉");
        player3 = new Player("杨过");
        player4 = new Player("小龙女");
        acc.join(player1);
        acc.join(player2);
        acc.join(player3);
        acc.join(player4);
        player1.beAttacked(acc);

    }
}

interface Observer {
    public String getName();

    public void setName(String name);

    public void help();

    public void beAttacked(AllyControlCenter acc);
}

class Player implements Observer {

    public String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void help() {
        System.out.println("坚持住" + this.name + "来救你");
    }

    @Override
    public void beAttacked(AllyControlCenter acc) {
        System.out.println(this.name + "被袭击");
        acc.notifyObserver(this.name);
    }

}

abstract class AllyControlCenter {

    protected String allyName;
    protected ArrayList<Observer> players = new ArrayList<>();

    public String getAllyName() {
        return allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    public void join(Observer obs) {
        System.out.println(obs.getName() + "加入" + this.allyName + "战队");
        players.add(obs);
    }

    public void remove(Observer obs) {
        System.out.println(obs.getName() + "离开" + this.allyName + "战队");
        players.remove(obs);
    }

    public abstract void notifyObserver(String name);
}

class ContentControlCenter extends AllyControlCenter {

    public ContentControlCenter(String allyName) {
        this.allyName = allyName;
        System.out.println(this.allyName + "战队组件成功");
        this.allyName = allyName;
    }

    @Override
    public void notifyObserver(String name) {
        for (Observer obs : players) {
            if (!obs.getName().equalsIgnoreCase(name))
                obs.help();
        }
    }

}
