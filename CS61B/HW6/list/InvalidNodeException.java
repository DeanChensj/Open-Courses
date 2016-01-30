/* InvalidNodeException.java */

package list;

/**
 *  Implements an Exception that signals an attempt to use an invalid ListNode.
 *  Converted dlist checked exception to unchecked
 */

public class InvalidNodeException extends RuntimeException {
  protected InvalidNodeException() {
    super();
  }

  protected InvalidNodeException(String s) {
    super(s);
  }
}
