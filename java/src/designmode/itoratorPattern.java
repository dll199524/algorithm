package designmode;

import java.util.ArrayList;
import java.util.List;

public class itoratorPattern {
    public static void main(String[] args) {
        List products = new ArrayList<>();
        products.add("葵花宝典");
        products.add("四十二章经");
        products.add("天龙八部");
        products.add("北冥神功");

        AbstractObjectList list = new ProductList(products);
        AbstractIterator iterator = list.createIterator();

        System.out.println("正向遍历");
        while (!iterator.isLast()) {
            System.out.printf(iterator.getNextItem() + ",");
            iterator.next();
        }

        System.out.println();
        System.out.println("-----------------------------------------");

        System.out.println("逆向遍历");
        while (!iterator.isFirst()) {
            System.out.printf(iterator.getPreviousItem() + ",");
            iterator.previous();
        }

    }
}

abstract class AbstractObjectList {

    protected List<Object> objectsList = new ArrayList<>();

    public AbstractObjectList(List<Object> objects) {
        this.objectsList = objects;
    }

    public void addObject(Object object) {
        objectsList.add(object);
    }

    public void remove(Object object) {
        objectsList.remove(object);
    }

    public List getObject() {
        return objectsList;
    }

    public abstract AbstractIterator createIterator();

}

class ProductList extends AbstractObjectList {

    public ProductList(List<Object> objects) {
        super(objects);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }

}

interface AbstractIterator {
    public void next();

    public boolean isLast();

    public void previous();

    public boolean isFirst();

    public Object getNextItem();

    public Object getPreviousItem();
}

class ProductIterator implements AbstractIterator {

    private ProductList productList;
    private List products;
    private int cursor1;
    private int cursor2;

    public ProductIterator(ProductList list) {
        productList = list;
        products = productList.getObject();
        cursor1 = 0;
        cursor2 = products.size() - 1;
    }

    @Override
    public void next() {
        if (cursor1 < products.size())
            cursor1++;
    }

    @Override
    public boolean isLast() {
        return cursor1 == products.size();
    }

    @Override
    public void previous() {
        if (cursor2 > -1)
            cursor2--;

    }

    @Override
    public boolean isFirst() {
        return cursor2 == -1;
    }

    @Override
    public Object getNextItem() {
        return products.get(cursor1);
    }

    @Override
    public Object getPreviousItem() {
        return products.get(cursor2);
    }

}