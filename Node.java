/**
 *Class node representing a node for a binary Tree
 */
public class Node{

   /** The term associated with this node. */
   String term;
   
   /** The sentence associated with this node. */

   String sentence;
   
   /** The confidence score associated with this node. */

   String cscore;
   
   /** The left node. */

   Node left;
   
   /** The right node. */

   Node right;
   /** The height */
   
   int height;

   
   /**
    *Constructor with a specified term, sentence, and cscore
    *
    * @param term The term associated with this node.
    * @param sentence The sentence associated with this node.
    * @param cscore The cscore associated with this node.
    */
    
    public Node(String term, String sentence, String cscore, Node l, Node r ) 
    {
        this.term = term;
        this.sentence = sentence;
        this.cscore = cscore;
        left = l;
        right = r;
        height = 0;
    }
}
  


