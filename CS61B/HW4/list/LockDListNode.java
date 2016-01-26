/* LockDListNode.java */

package list;

/**
 *  A LockDListNode is a node in a LockDList (doubly-linked list).
 */

public class LockDListNode extends DListNode{

  protected LockDListNode prev;
  protected LockDListNode next;
  protected boolean isLocked;

  /**
   *  LockDListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  LockDListNode(Object i, DListNode p, DListNode n) {
    super(i, p, n);
    isLocked = false;
  }
}
