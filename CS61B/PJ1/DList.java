/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public DListNode head;
  protected long size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList, x.next != null.
   *  3)  For any DListNode2 x in a DList, x.prev != null.
   *  4)  For any DListNode2 x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList() constructor for an empty DList2.
   */
  public DList() {
    head = new DListNode();
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(short r, short g, short b, int l) {
    head = new DListNode();
    head.next = new DListNode(r, g, b, l);
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }


  /**
   *  insertEnd() inserts an item at the end of a DList.
   */
  public void insertEnd(short r, short g, short b, int l) {
    // Your solution here.
    DListNode tmpNode = new DListNode(r, g, b, l);
    tmpNode.prev = head.prev;
    tmpNode.next = head;
    tmpNode.prev.next = tmpNode;
    head.prev = tmpNode;
    size++;
  }

   /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + " ( ";
      result = result + current.red + "  ";
      result = result + current.green + "  ";
      result = result + current.blue + "  ";
      result = result + current.runLength + "  ";
      result = result + " ) ";
      current = current.next;
    }
    return result + "]";
  }

}
