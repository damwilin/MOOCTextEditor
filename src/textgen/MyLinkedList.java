package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        size = 0;
        head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        } else {
            LLNode<E> addedElement = new LLNode<E>(element);
            if (size == 0) {
                head.next = addedElement;
                tail.prev = addedElement;
                addedElement.next = tail;
                addedElement.prev = head;
                size++;
            } else {
                tail.prev.next = addedElement;
                addedElement.prev = tail.prev;
                addedElement.next = tail;
                tail.prev = addedElement;
                size++;
            }
        }
        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        //LLNode<E> answer;
        LLNode<E> temp = head.next;
        if (index > size - 1 || index < 0 || size == 0)
            throw new IndexOutOfBoundsException("Check out of bounds");
        else if (index == 0)
            return temp.data;
        else if (index > 0) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }
        return temp.data;
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        if (element == null)
            throw new NullPointerException();
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        LLNode<E> toAdd = new LLNode<E>(element);
        if (index == size - 1) {
            toAdd.prev = tail.prev;
            tail.prev.next = toAdd;
            toAdd.next = tail;
            tail.prev = toAdd;
        } else {
            LLNode<E> temp = head.next;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            toAdd.next = temp;
            toAdd.prev = temp.prev;
            temp.prev.next = toAdd;
            temp.prev = toAdd;
        }
        size++;
    }


    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        if (index > size - 1)
            throw new IndexOutOfBoundsException();
        LLNode<E> temp = head.next;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
        return temp.data;
    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {
        if (element == null)
            throw new NullPointerException();
        if (index > size - 1)
            throw new IndexOutOfBoundsException();
        LLNode<E> temp = head.next;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        E oldvalue = temp.data;
        temp.data = element;
        return oldvalue;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}
