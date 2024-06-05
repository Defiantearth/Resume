public class GASingleLinkedListP2<E>
{
  
  //Inner class for node
  private static class Node<E>
  {
    private E data;
    private Node<E> next;
    //Constructor 1
    public Node(E d)
    {
      data = d;
      next = null;
    }
    
    //Constructor 2
    public Node(E data, Node<E> next)
    {
      this.data = data;
      this.next = next;
    }
  }//end of inner class
  
  
  //Data fields
  private Node<E> head = null;
  private int size = 0;
  //end of Data fields
  
 
  //private methods
  
  //add first method
  private void addFirst(E item)
  {
    head = new Node<E>(item, head);
      size++;
  }
  
  //add after method
  private void addAfter(Node<E> node, E item)
  {
    node.next = new Node<E>(item, node.next);
    size++;
  }
  
 //get node method
  private Node<E> getNode(int index)
  {
    Node<E> node = head;
    for (int i = 0; i < index && node != null; i++){
      node = node.next;
    }
    return node;
  }//end of private methods

//public methods
//public add method
public void add(int index, E item){
  if(index < 0 || index > size){
    throw new IndexOutOfBoundsException(Integer.toString(index));
  }
  if(index==0){
    addFirst(item);
  }
  else{
    Node<E> node = getNode(index - 1);
    addAfter(node, item);
  }
}

//size
public int size(){
  return size;
}

//get 
public E get(int index)
{
  if(index < 0 || index >= size){
    throw new IndexOutOfBoundsException(Integer.toString(index));
  }
  Node<E> node = getNode(index);
  return node.data;
}
}

   