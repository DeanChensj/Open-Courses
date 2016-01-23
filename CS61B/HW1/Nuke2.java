/* Nuke2.java */

import java.io.*;

/** a class called Nuke2 whose main
 * method reads a string from the keyboard and prints the same string, with its
 * second character removed. 
 */

class Nuke2 {

  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));
    inputLine = keyboard.readLine();

    String outString = inputLine;
    if(inputLine.length() > 2){

      outString = inputLine.charAt(0) + inputLine.substring(2);
    }else if(inputLine.length() == 2){

      outString = inputLine.substring(0, 1);
    }

    System.out.println(outString);
    
  }
}
