package Data_Structures;

/**
 * @class   LinkedList
 * @param   <E>
 * @desc    Implementation of LinkedList
 */
public class LinkedList<E> {
    Node head;
    int size = 0;

    /**
     * @class   Node
     * @desc    Implementation of Node for LinkedList
     */
    class Node {
        E data;
        Node next;

        Node (E e) {
            this.data = e;
            this.next = null;
        }
    }

    /**
     * @function    Return size of linked list
     * @return      size
     */
    public int size() {
        return this.size;
    }

    /**
     * @function    Add element to linked list
     * @return      boolean
     */
    public boolean add(E e) {
        Node new_node = new Node(e);

        if (this.head == null) {
            this.head = new_node;
        } else {
            Node last = this.head;

            while (last.next != null) {
                last = last.next;
            }

            last.next = new_node;
        }

        size++;

        return true;
    }

    /**
     * @function    Remove current head
     * @return      Removed node
     */
    public E remove() {
        Node nodeToBeRemoved = this.head;

        if (this.head.next != null) {
            this.head = this.head.next;
        } else {
            this.head = null;
        }

        return nodeToBeRemoved.data;
    }

    /**
     * @function    Remove node at given index
     * @param       index
     * @return      Removed node
     */
    public E remove(int index) {
        Node nodeToBeRemoved = this.head;
        Node prevNode = null;
        int count = 0;

        if (index == 0) {
            if (this.head.next != null) {
                this.head = this.head.next;
            } else {
                this.head = null;
            }
            this.size--;
        } else {
            while (nodeToBeRemoved.next != null && count <= index) {
                /* Redirect curr.prev.next to curr.next */
                if (count == index - 1) {
                    prevNode = nodeToBeRemoved;
                }
                if (count == index) {
                    if (nodeToBeRemoved.next != null) {
                        prevNode.next = nodeToBeRemoved.next;
                    } else {
                        System.out.println("test");
                        prevNode.next = null;
                    }
                    this.size--;
                    return nodeToBeRemoved.data;
                }

                nodeToBeRemoved = nodeToBeRemoved.next;
                count++;
            }
        }

        return nodeToBeRemoved.data;
    }

    /**
     * @function    Return whether element exists in linked list
     * @return      boolean
     */
    public boolean contains(E e) {
        Node target = this.head;

        if (target.data == e) return true;

        while (target.next != null) {
            target = target.next;

            if (target.data == e) return true;
        }

        return false;
    }

    /**
     * @function    Get element from specified index
     * @return      <E>
     */
    public E get(int index) {
        if (index == 0) return this.head.data;
        int count = 0;

        Node target = this.head;

        while (target.next != null && count != index) {
            target = target.next;
            count++;
        }

        if (count == index) {
            return target.data;
        }

        return null;
    }

    /**
     * @function    Get first element
     * @return      <E>
     */
    public E getFirst() {
        return this.head.data;
    }

//    E unlink(Node node) {
//
//
//
//    }


}
