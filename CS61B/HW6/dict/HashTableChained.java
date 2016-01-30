/* HashTableChained.java */

package dict;

import list.*;
import java.util.Random;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected final int INIT_BUCKETS = 103;
  protected final int PRIME = 109345121;
  protected int entries, capacity;
  protected List hashTable[];
  protected long scale, shift;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
  // @SuppressWarnings("unchecked")
  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    entries = 0;
    capacity = findPrime(sizeEstimate);
    hashTable = new SList[capacity];
    for(int i = 0; i < capacity; i++)
      hashTable[i] = new SList();

    Random rand = new Random();
    scale = rand.nextInt(PRIME-1) + 1;
    shift = rand.nextInt(PRIME);
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    entries = 0;
    capacity = INIT_BUCKETS;
    hashTable = new SList[capacity];
    for(int i = 0; i < capacity; i++)
      hashTable[i] = new SList();

    Random rand = new Random();
    scale = rand.nextInt(PRIME-1) + 1;
    shift = rand.nextInt(PRIME);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return (int)(Math.abs(scale * code + shift) % PRIME) % capacity;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entries;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return entries == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    int index = compFunction(key.hashCode());

    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;

    hashTable[index].insertBack(entry);
    if(++entries >= capacity){
      rehash();
    }
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key){
    // Replace the following line with your solution.
    int index = compFunction(key.hashCode());

    int length = hashTable[index].length();
    if(length == 0) return null;
    else{
      ListNode node = findNode(index, key);

      if(node == null) return null;
      return (Entry) node.item();
    }
  }

  private ListNode findNode(int index, Object key) {
    ListNode node = hashTable[index].front();
    int length = hashTable[index].length();

    while( node.isValidNode() ){
      Entry entry = (Entry) node.item();
      if(entry.key().equals(key)){
        return node;
      }
      node = node.next();
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key){
    // Replace the following line with your solution.
    int index = compFunction(key.hashCode());

    int length = hashTable[index].length();
    if(length == 0) return null;
    else{
      ListNode node = findNode(index, key);

      if(node != null){
        entries--;
        Entry entry = (Entry) node.item();
        node.remove();
        return entry;
      } 
      return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for(int i = 0; i < capacity; i++){
      hashTable[i] = new SList();
    }
    entries = 0;
  }

  /**
   *  Doubles the size of the hash table and rehashes all the entries.
   */
  private void rehash() throws InvalidNodeException{
    capacity *= 2;

    List old[] = hashTable;
    hashTable = new SList[capacity];
    for(int i = 0; i < capacity; i++)
      hashTable[i] = new SList();

    Random rand = new Random();
    scale = rand.nextInt(PRIME-1) + 1;
    shift = rand.nextInt(PRIME);

    for (int i = 0; i < capacity/2; i++) {
      int length = old[i].length();
      if(length == 0) continue;

      ListNode node = old[i].front();
      while(node.isValidNode()){
        Entry entry = (Entry) node.item();
        int index = compFunction(entry.key().hashCode());
        hashTable[index].insertBack(entry);

        node = node.next();
      }
    }
  }

  public int findPrime(int size){
    size *= 2;
    boolean notPrime[] = new boolean[size+1];
    for(int i = 2; i*i <= size; i++){
      if(!notPrime[i]){
        for(int j = 2*i; j <= size; j += i){
          notPrime[j] = true;
        }
      }
    }
    int i;
    for(i = size; i > 1; i--){
      if(!notPrime[i]) break;
    }
    return i;
  }

  public String toString(){
    String out = "";
    for(int i = 0; i < capacity; i++){
      out = out + "[" + hashTable[i].length() + "]";
      if(i % 16 == 0) out = out + "\n";
    }
    return out;
  }

  public void getCollision(){
    System.out.println("The number of buckets is " + capacity);
    System.out.println("The number of entries is " + entries);
    System.out.println("The loading factor is " + (double) entries/capacity);

    double expect = entries - capacity + capacity * Math.pow(1-(1/(double)capacity), entries);
    System.out.println("The expect collision is : " + expect);

    int collision = 0;
    for(int i = 0; i < capacity; i++){
      if(hashTable[i].length() > 1)
        collision += hashTable[i].length() - 1;
    }
    System.out.println("The actual collision is : " + collision);
  }

  public static void main(String[] args) {
    int[] testValues = {0,1,64, 1200};
    for (int num : testValues) {
      HashTableChained t;
      if (num == 0) {
        t = new HashTableChained();
      }
      else {
       t = new HashTableChained(num);
      }
      System.out.println("num = " + num + " capacity = " + t.capacity);
      Entry a = t.insert("hi", 4);
      System.out.println(t.size()); // 1
      System.out.println(t.find("hi").value()); // 4
      t.insert("hi", "gah");
      System.out.println(t.size()); // 2
      System.out.println(t.find("hi").value()); // 4
      t.insert("blue", "sky");
      System.out.println(t.size()); // 3
      System.out.println(t.find("blue").value()); // sky
      a = t.remove("hi");
      System.out.println(t.size()); // 2
      System.out.println(a.value()); //4
      a = t.remove("hi");
      System.out.println(t.size()); // 1
      System.out.println(a.value()); //gah
      System.out.println(t.find("hi")); // null
      t.insert(2, "well");
      System.out.println(t.find(2).value()); // well
      t.makeEmpty();
      System.out.println(t.find(2)); // null
      System.out.println(t.remove(2)); // null
      System.out.println(t.size()); // 0
    }
        
  }

}
