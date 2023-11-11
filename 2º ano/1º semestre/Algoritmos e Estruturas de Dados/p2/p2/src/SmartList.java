package aed.collections;

import java.util.Comparator;
import java.util.Iterator;

public class SmartList<Item> implements Iterable<Item> {
    int activeMTF = 0;
    int activeTrans = 0;
    int compareMTF = 0;
    int compareTrans = 0;

    private class Node {
        private Item item;
        private Node next;
    }

    private class SmartListIterator implements Iterator<Item> {

        private Node nextNode;

        public SmartListIterator() {
            nextNode = first;
        }

        public boolean hasNext() {
            if (nextNode != null)
                return true;
            else
                return false;
        }

        public Item next() {
            Item result;
            if (hasNext()) {
                result = nextNode.item;
                nextNode = nextNode.next;
            } else
                result = null;
            return result;
        }
    }

    private Node first;
    private int size;

    public SmartList() {
        this.first = null;
        this.size = 0;
    }

    public void add(Item item) {
        if (item != null) {
            Node oldFirst = this.first;
            this.first = new Node();
            this.first.item = item;
            this.first.next = oldFirst;
            this.size++;
        } else
            throw new IllegalArgumentException();
    }

    public Item get(int a) {
        Node x = this.first;
        Item result = x.item;
        while (a > 0) {
            x = x.next;
            result = x.item;
            a--;
        }
        return result;
    }



    public Item searchMTF(Item item) {
        Item result = null;
        Node x = this.first;
        this.activeMTF += 1;
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                this.compareMTF += 1;
                if (x.item.equals(item)) {
                    result = x.item;
                    Item a = x.item;
                    remove(x.item);
                    add(a);
                    return result;
                }
                x = x.next;
            }
        }

        return result;
    }

    public Item searchTrans(Item item) {

        Item result = null;
        Node x = this.first;
        activeTrans += 1;
        if (!isEmpty()) {
            compareTrans += 1;
            if (!x.item.equals(item)) {
                for (int i = 0; i < size - 1; i++) {
                    compareTrans += 1;
                    if (x.next.item.equals(item)) {
                        result = x.next.item;
                        Item a = x.next.item;
                        x.next.item = x.item;
                        x.item = a;
                        return result;
                    }
                    x = x.next;
                }
            } else {
                result = x.item;
                return result;
            }
        }

        return result;
    }

    public Item search(Item item) {
        return null;
    }

    public Item search(Item item, Comparator<Item> c) {
        return null;
    }

    public Item remove(Item item) {
        Item result = null;
        if (!isEmpty() && !this.first.item.equals(item) && size > 1) {
            Node x = this.first;
            for (int i = 0; i < size - 1; i++) {
                if (x.next.item.equals(item)) {
                    x.next = x.next.next;
                    this.size--;
                    result = item;
                    return result;
                } else
                    x = x.next;
            }
        } else if (!isEmpty() && size > 1) {
            result = this.first.next.item;
            this.first = this.first.next;
            this.size--;
        } else if (size == 1) {
            clear();
        }
        return result;
    }

    public float getAvgMTFCompares() {
        if (activeMTF != 0)
            return (float) (compareMTF) / (float) (activeMTF);
        else
            return 0;
    }

    public float getAvgTransCompares() {
        if (activeTrans != 0)
            return (float) (compareTrans) / (float) (activeTrans);
        else
            return 0;
    }

    public void clear() {
        this.first = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        if (size != 0)
            return false;
        else
            return true;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] object = new Object[this.size];
        Node x = this.first;
        for (int i = 0; i < size; i++) {
            object[i] = x.item;
            x = x.next;
        }
        return object;
    }

    void addLast(Item item) {
        this.first.next = new Node();
        this.first.next.item = item;
        size++;
    }

    public SmartList<Item> shallowCopy() {
        SmartList<Item> shallowcopy = new SmartList<>();
        Node x = this.first.next;
        shallowcopy.add(x.item);
        Node Original = shallowcopy.first;
        x = x.next;
        for (int i = 0; i < size - 1; i++) {
        shallowcopy.addLast(x.item);
        x = x.next;
        shallowcopy.first = shallowcopy.first.next;
        }
        shallowcopy.first = Original;
        return shallowcopy;
    }

    public Iterator<Item> iterator() {
        return new SmartListIterator();
    }

    public static void main(String[] args) {
        SmartList<Integer> SmartList = new SmartList<>();
        SmartList.remove(7);
        SmartList.searchTrans(5);
        SmartList.add(5);
        SmartList.searchTrans(5);
        SmartList.add(2);
        SmartList.add(7);
        SmartList.add(9);
        SmartList.add(3);
        SmartList.add(11);
        SmartList.size();
        SmartList.search(9);
        SmartList.search(9);
        System.out.println(SmartList.getAvgTransCompares());
        System.out.println(SmartList.get(0));
        System.out.println(SmartList.get(1));
        System.out.println(SmartList.get(2));
        System.out.println(SmartList.get(3));
        System.out.println(SmartList.get(4));
        System.out.println(SmartList.get(5));
        System.out.println(SmartList.searchTrans(7));
        System.out.println(SmartList.searchTrans(null));
        System.out.println(SmartList.searchTrans(77));
        for (int i = 0; i < SmartList.size(); i++) {
            System.out.print(SmartList.get(i) + " ");
        }
        System.out.println();

        SmartList<Integer> copy = SmartList.shallowCopy();
        for (int i = 0; i < SmartList.size(); i++) {
            System.out.print(copy.get(i) + " ");

        Iterator<Integer> a = SmartList.iterator();
        a.next();
        a.next();
        a.next();
    }
}
}