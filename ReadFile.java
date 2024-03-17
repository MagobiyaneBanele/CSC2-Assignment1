import java.util.*;
import java.io.*;

/**
 * Represents a utility class for reading data from a file and populate a binary search tree.
 * It reads lines from the file, splits them into parts, insert them into the binary search tree.
 * 
 * @author Banele Magobiyane
 */
 
public class ReadFile{
    /**
    * Calling constructor of the BST; he binary search tree instance to populate with data from the file.
    */
    
   GenericsKbBSTApp btree= new GenericsKbBSTApp();
   
   /**
    * Reads data from the specified file and populates the binary search tree.
    * 
    * @param filename The name of the file to read from 
    * @param btree Object of binary search tree
    */
    
   public void readFile(String filename, GenericsKbBSTApp btree){
      try{
         File myObj = new File(filename);
         Scanner reader = new Scanner(myObj);
         while (reader.hasNextLine()){
            String line = reader.nextLine();   
            String[ ]parts = line.split("\t");
            String term = parts[0];
            String sentence = parts[1];
            String cscore = parts[2];          
            btree.insertLine(term, sentence, cscore);
            
         } 
         System.out.println("Knowledge base loaded successfully."); 
         //btree.printSentences();
         reader.close(); 
                    
         }
         
       catch(FileNotFoundException e){
          System.out.println("File not found");
          e.printStackTrace();
       }
   }



























}