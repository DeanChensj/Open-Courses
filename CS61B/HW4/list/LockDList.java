/* DList.java */

package list;

public class LockDList extends DList{

  private DListNode head;
  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    return new LockDListNode(item, (LockDListNode) prev, (LockDListNode) next);
  }

  /**
   *  LockDList() constructor for an empty LockDList.
   */
  public LockDList() {
    super();
  }

  /**
   *  remove() removes "node" from this LockDList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    if(node == null || node == head || ((LockDListNode) node).isLocked == true) return;
    node.prev.next = node.next;
    node.next.prev = node.prev;
    size--;
  }

  public void lockNode(DListNode node) {
    ((LockDListNode) node).isLocked = true;
  }

}
