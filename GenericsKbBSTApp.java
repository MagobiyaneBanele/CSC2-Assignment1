
import java.util.*;
import java.io.*;

/** 
 * Represents a Binary Search Tree App for managing knowledge base
 * This program allows reading data, adding it to a binary tree, updating/sesrching for terms.
 *
 * @author Banele Magobiyane
 */
 
public class GenericsKbBSTApp{
   
   private Node root ;
   
   /** 
    * Private method for inserting a new node into the binary tree
    *
    * @param root The node of the binary tree
    * @param term Term of the line
    * @param sentence sentence of the line
    * @param cscore cscore of the line   
    * @return  The root node   
    */
   
   private Node insert(Node root, String term,String sentence,String cscore)  {
  
      if (root == null){
         return new Node(term, sentence, cscore);
      }
     
      int compare = term.compareTo(root.term);
      

      if (compare<0){
         root.left = insert(root.left,term, sentence, cscore);
      
      }
        else if(compare>0){
         root.right = insert(root.right,term,sentence,cscore);
      
      }
      return root;
      
   }
   
   /** 
    * Public method to insert a new term into the binary tree
    *
    * @param term Term of the line
    * @param sentence sentence of the line
    * @param cscore cscore of the line   
    */

   public void insertLine(String term,String sentence,String cscore){

      root = insert(root,term, sentence, cscore);
   }
   
   /** 
    * Private method to search for a term in the binaryTree
    *
    * @param root The node of the binary tree
    * @param term Term of the line
    * @return root node matching the one searched   
    */

   private Node search(Node root, String term){
      if (root == null || root.term.equals(term)){
         return root;
      
      }
      int compare = term.compareTo(root.term);
      
      if (compare < 0){
         return search(root.left, term);
      
      }
      else{
      
         return search(root.right, term) ;
         
         
      }
   }
   /** 
    *Public method to search for a term in the binary tree
    *
    *@param term Term of the line
    *@return The root node matching the searched term    
    */

   public Node searchTerm(String term){
     return search(root, term) ;
   }

   /** 
    * Private Method to search for a term in the binaryTree and by comparing cscores
    * @param root The node of the binary tree
    * @param term Term of the line
    * @param sentence sentence of the line
    * @return root node matching the searched term and sentence  
    */

   private Node searchSentence(Node root, String term, String sentence){
      if (root == null || root.term.equals(term) && root.sentence.equals(sentence)){
         return root;
      
      }
      int compareTerm = term.compareTo(root.term);
      int compareSentence = sentence.compareTo(root.sentence);
      
      if (compareTerm < 0 || compareTerm==0 && compareSentence < 0){
         return searchSentence(root.left, term, sentence);
      
      }
      else{
      
         return searchSentence(root.right, term, sentence) ;
         
         
      }
   }
   
    /** 
     * public method to search for a term and sentence in the binary tree
     * @param term Term of the line
     * @param sentence sentence of the line
     * @return root node matching the one searched  
     */


   public Node searchTermSentence(String term, String sentence){
     return searchSentence(root, term, sentence) ;
   }
    
   /** Method to insert a new node into the binary tree or update existing Node if it has less confodence score
      *@param root The node of the binary tree
      *@param term Term of the line
      *@param sentence sentence of the line
      *@param cscore cscore of the line 
  
   */

    private Node insertNode(Node root, String term,String sentence,String cscore)  {
  
      if (root == null){
         return new Node(term, sentence, cscore);
      }
      int compare = term.compareTo(root.term);
      
      if (compare<0){
         root.left = insertNode(root.left,term, sentence, cscore);
      
      }
      else if(compare>0){
         root.right = insertNode(root.right,term,sentence,cscore);
      
      }
      else{
      //if the term exists, compare cscore
         int compareScore = cscore.compareTo(root.cscore);
         if (compareScore >0){
         //if new cscore is higher, update the score and sentence.
            root.cscore = cscore;
            root.sentence = sentence;
            System.out.println("Statement for "+root.term+" has been updated.");
         }
         else if (compareScore <0 ){
            System.out.println("Statement for "+root.term+" has lower confidence score than the existing sentence so it is not updated.");
         }
      }
      return root;
   }
   
  
   /** 
    * Public method to add a new term into the binary tree
    * @param term Term of the line
    * @param sentence sentence of the line
    * @param cscore cscore of the line 
  
    */
   public void insertNewTerm(String term,String sentence,String cscore){

      root = insertNode(root,term, sentence, cscore);   
   }
   
  /** 
   * Public Method to add a new term into the binary tree
   * @param term Term of the line
   * @param sentence sentence of the line
   * @param cscore cscore of the line   
   */

    public void addTerm(String term, String sentence, String cscore){
      insertNewTerm(term, sentence, cscore);
        }
        
   /**
    * Method to prompt the user to enter the options and calls a method that goes with that case
    */

   public void showMenu(){
      
      Scanner sc = new Scanner(System.in);
      GenericsKbBSTApp app = new GenericsKbBSTApp();
      ReadFile fileReader = new ReadFile();
      

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
            fileReader.readFile(filename,app);
            
            break;
         case 3:
            System.out.println("Enter the term to search: ");
            String term = sc.nextLine();
            Node result = app.searchTerm(term);
            if (result !=null){
              System.out.println("Statement found: "+ result.sentence+" "+result.cscore  ); 
            }
            
            break;
         case 2:
            System.out.println("Enter the term: ");
            String terrm = sc.nextLine();
            System.out.println("Enter the statement: ");
            String sentencee = sc.nextLine();
            System.out.println("Enter confidence score: ");
            String cscore = sc.nextLine();
            app.addTerm(terrm,sentencee, cscore );
            System.out.println("Statement for "+terrm+" has been updated.");

            break;
         case 4:
            System.out.println("Enter the term: ");
            String termm = sc.nextLine();
            System.out.println("Enter the statement to search for: ");
            String sentence = sc.nextLine();
            Node searchResults = app.searchTermSentence(termm, sentence);
            if (searchResults !=null){
              System.out.println("Statement was found and has a confidence score of : "+ searchResults.cscore  ); 
            }
            break;
         case 5:
            break;
      
        }
      }
   }
   /**
     * This is a program for adding two numbers in java, and this  amin method that calls the showMenu method
     * @param args for main method
     */

   public static void main(String [] args){
   GenericsKbBSTApp app = new GenericsKbBSTApp();
    app.showMenu();

       }
  }