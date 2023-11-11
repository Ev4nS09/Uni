package aed.collections;

import java.util.Iterator;

public class QueueArray<Item> implements Iterable<Item> {

  private class QueueArrayIterator implements Iterator<Item> {
    private int curruntIndex;

    public QueueArrayIterator() {
      this.curruntIndex = 0;
    }

    public boolean hasNext() {
      return (this.curruntIndex < size());
    }

    public Item next() {
      Item result;
        result = items[this.curruntIndex + firstIndex];
        this.curruntIndex++;
      return result;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove does not exist");
     }
    }
  

  private Item items[];
  private int firstIndex;
  private int lastIndex;
  private static final int INITIAL_LENGTH = 10;

  @SuppressWarnings("unchecked")
  public QueueArray() {
    items = (Item[]) new Object[INITIAL_LENGTH];
    firstIndex = 0;
    lastIndex = 0;
  }

  @SuppressWarnings("unchecked")
  public void resize(int NEW_LENGTH) {
    Item[] resizeArray = (Item[]) new Object[NEW_LENGTH];
    int j = 0;
    for (int i = firstIndex; i < lastIndex; i++) {
      resizeArray[j] = this.items[i];
      j++;
    }
    this.items = resizeArray;
    this.lastIndex = size();
    this.firstIndex = 0;
  }

  public void enqueue(Item item) {
    if (lastIndex == this.items.length) {
      resize(2 * this.items.length);
    }
    this.items[lastIndex] = item;
    this.lastIndex++;
  }

  public Item dequeue() {
    Item result = null;
    if (!isEmpty()) {
      result = this.items[firstIndex];
      this.items[firstIndex] = null;
      firstIndex++;
      if (size() < this.items.length / 2) {
        resize(this.items.length / 2);
      }
    }
    return result;
  }

  public Item peek() {
    Item result = null;
    if (!isEmpty()) {
      result = items[firstIndex];
    }
    return result;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size() {
    return lastIndex - firstIndex;
  }

  public int lastIndex() {
    return lastIndex;
  }

  public int firstIndex() {
    return firstIndex;
  }

  public QueueArray<Item> shallowCopy() {
    QueueArray<Item> shallowCopy = new QueueArray<>();
    shallowCopy.items = this.items.clone();
    shallowCopy.firstIndex = this.firstIndex;
    shallowCopy.lastIndex = this.lastIndex;
    return shallowCopy;
  }

  public Iterator<Item> iterator() {
    return new QueueArrayIterator();
  }

  public static void main(String[] args) {
    QueueArray<Integer> a = new QueueArray<>();
    a.enqueue(1);
    a.enqueue(2);
    a.enqueue(3);
    a.enqueue(4);
    a.dequeue();
    a.dequeue();
    a.dequeue();
    a.dequeue();
    a.enqueue(4);
    System.out.println(a.firstIndex());
    System.out.println(a.lastIndex());
    // Iterator<Integer> it = a.iterator();
    // System.out.println(it.next());
    // System.out.println(it.next());
    // System.out.println(a.firstIndex());
    // System.out.println(a.lastIndex());
    // System.out.println(a.size());
    // a.dequeue();
    // System.out.println(it.next());
    // System.out.println(it.next());
    // System.out.println(a.size());
    // System.out.println(a.firstIndex());
    // System.out.println(a.lastIndex());
    // System.out.println(a.peek());

  }

}