/* BadAccountException.java */

/**
 *  Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadTransactionException extends Exception {

  public int amount;  // The invalid amount number.

  /**
   *  Creates an exception object for invalid amount "badAmount".
   **/
  public BadTransactionException(int badAmount) {
    super("Invalid amount: " + badAmount);

    amount = badAmount;
  }
}
