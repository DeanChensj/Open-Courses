/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   */

  public short red;
  public short green;
  public short blue;
  public int runLength;
  public DListNode prev;
  public DListNode next;

  /**
   *  DListNode() constructor.
   */
  DListNode() {
    red = 0;
    green = 0;
    blue = 0;
    runLength = 0;
    prev = null;
    next = null;
  }

  DListNode(int l) {
    red = 0;
    green = 0;
    blue = 0;
    runLength = l;
    prev = null;
    next = null;
  }

  DListNode(short r, short g, short b, int l) {
    red = r;
    green = g;
    blue = b;
    runLength = l;
    prev = null;
    next = null;
  }

}
