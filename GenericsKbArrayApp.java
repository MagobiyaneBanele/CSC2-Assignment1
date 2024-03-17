import java.util.*;
import java.io.*;
/**
 *Represents an application for managing a knowledge base using 2d array.
 * This program allows reading datat from a file, adding new term and search for terms/sentences.
 * It provides functionalities such as loading a knowledge base, adding statements, and searching.
 * 
 * Each row in 2D array represents a statement with columns containg term, sentence, and confidence score.
 *
 * @author Banele Magobiyane
 */
public class GenericsKbArrayApp{
   String [][] data;
   int lineCount;
   /**
    * Constructs a GenericsKbArrayApp object with initial 2D array and line count.
    */
   public GenericsKbArrayApp(){
      data = new String[1000000][3];
     int lineCount = 0;
   } 
   /**
    * Reads in data from a file and populates the 2D array.
    *
    * @param filename The name of the file to read from
    */
    
   public void readFile(String filename){
      try{
         File myObj = new File(filename);
         Scanner reader = new Scanner(myObj);
         while (reader.hasNextLine()){
            if (lineCount >= data.length) {
                    // Resize the array if it's full
                    data = Arrays.copyOf(data, data.length * 2);
            }
            String line = reader.nextLine();
            String[] splitData = line.split("\\t");
            data[lineCount] = splitData;
            lineCount++;
            
         } 
         System.out.println("Knowledge base loaded successfully.");    
               
         reader.close();                
       }
       catch(FileNotFoundException e){
          System.out.println("File not found");
       }
   }
   
   /**
    * Searches for a term in the knowledge base and prints matching statements.
    * @param term The term to search for
    */
   public void searchTerm(String term){
      if (data == null){
         System.out.println("File is empty");       }
       else{
          for (int i =0; i < lineCount; i++){ 
               if (data[i][0].equals(term)){
                  System.out.println("Statement found: "+data[i][1]+"(Confidence score: "+data[i][2]+")");
               }
          }
       }
    } 
    /**
     * Searches for a term and sentence in the knowledge base ad prints matching statement
     * @param term The term to search for
     * @param sentence The sentence to search for
     */
    public void searchTermSentence(String term, String sentence){
         if (data == null){
            System.out.println("File is empty");
            
          }
          else{
             for (int i =0; i < lineCount; i++){ 
                  if (data[i][0].equals(term) && data[i][1].equals(sentence)){
                  System.out.println("Statement was found and has a confidence score: "+data[i][2]+".");
                  }
             }
          }
     }
     
     /**
      * Adds a new statement to the knowledge base if its confidence score is higher than  the existing one.
      *
      * @param searchTerm The term of the statement to add
      * @param sentence The sentence of the statement
      * @param cscore The confidence score of the statement.
      */
     public void addTerm(String searchTerm, String sentence, String cscore){
       if (data == null){
            System.out.println("File is empty");
            
          }
          else{
            for (int i =0; i < lineCount; i++){ 
            
               if (data[i][0].equals(searchTerm)&&(Double.valueOf(cscore))>(Double.valueOf(data[i][2]))){
                  data[i][1] = sentence;
                  data[i][2] = cscore;
                  System.out.println("Statement for "+searchTerm+" has been updated.");
               }
               else if (data[i][0].equals(searchTerm)&&(Double.valueOf(cscore))<(Double.valueOf(data[i][2]))){
                  System.out.println("Statement for "+searchTerm+" has lower confidence score than the existing sentence so it is not updated.");
                }
            }
         }
      }
      
      /** 
       * Displays a menu and executes actions based on user input.
       */
   
      public void showMenu(){
         
         Scanner sc = new Scanner(System.in);
         GenericsKbArrayApp app = new GenericsKbArrayApp();
         int menu = 0;
          
         while(menu != 5){
         System.out.println("Choose an action from the menu:"+'\n'+"1. Load a knowledge base from a file"+'\n'+"2. Add a new statement to the knowledge base"+'\n'+"3. Search for an item in the knowledge base by term"+'\n'+"4. Search for a item in the knowledge base by term and sentence"+'\n'+"5. Quit");
         try{
         System.out.println("Enter your choice: ");
         menu = sc.nextInt();
         }catch(InputMismatchException e){
            System.out.println("Please enter an enteger.");
             showMenu();
         }
         sc.nextLine();
         
         switch (menu){
            case 1:
               System.out.println("Enter  filename: ");
               String filename = sc.nextLine();
               app.readFile(filename);
               
               break;
            case 3:
               System.out.println("Enter the term to search: ");
               String term = sc.nextLine();
               app.searchTerm(term);
               break;
            case 2:
               System.out.println("Enter the term: ");
               String terrm = sc.nextLine();
               System.out.println("Enter the statement: ");
               String sentencee = sc.nextLine();
               System.out.println("Enter confidence score: ");
               String cscore = sc.nextLine();
               app.addTerm(terrm,sentencee, cscore );
   
               break;
            case 4:
               System.out.println("Enter the term: ");
               String termm = sc.nextLine();
               System.out.println("Enter the statement to search for: ");
               String sentence = sc.nextLine();
               app.searchTermSentence(termm, sentence);
               break;
            case 5:
               break;
               
            }
      
         }
     }
     /**
      * Main function that calls showMenu method.
      * @param args for main method
      */
      public static void main(String [] args){
         GenericsKbArrayApp app = new GenericsKbArrayApp();
         app.showMenu();
      }
   
   }