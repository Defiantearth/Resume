//project 2
import javax.swing.*;
public class Project_2
{
  //gets values for term
  public static void getTerm(GASingleLinkedListP2<AddTerm> poly, int index,int position){
    
    //variables
    int c,e;
    String sc,se;
      
    position += 1;
    sc = JOptionPane.showInputDialog("Enter coefficient for term " + position);
        c = Integer.parseInt(sc);
    se = JOptionPane.showInputDialog("Enter exponenent for term " + position);
        e = Integer.parseInt(se);
    AddTerm term = new AddTerm(c,e);
    //echo
    System.out.println("you entered coefficient = " + c + " and exponent = " + e + " for term " + position);
    poly.add(index,term); 
  }
  
  
  public static void AddPolys(GASingleLinkedListP2<AddTerm> firstPoly, GASingleLinkedListP2<AddTerm> secondPoly, GASingleLinkedListP2<AddTerm> result){
    
    int firstLeft,secondLeft,indexFirst,indexSecond,indexResult,total;
    firstLeft = firstPoly.size();
    secondLeft = secondPoly.size();
    indexFirst = 0; indexSecond = 0; indexResult = 0;
  
   //put everything into result as long as both polys have terms
    while( firstLeft > 1 && secondLeft > 1){
      
      //case where exponents are equal
      if((firstPoly.get(indexFirst)).getExp() == (secondPoly.get(indexSecond)).getExp()){
     
     total = (firstPoly.get(indexFirst)).getCoef() + (secondPoly.get(indexSecond)).getCoef();
        
     AddTerm term = new AddTerm(total,(firstPoly.get(indexFirst)).getExp());
     result.add(indexResult,term);
     
    
     indexFirst += 1; indexSecond += 1; indexResult += 1;
     firstLeft -= 1; secondLeft -= 1;
      }
      
      //case where first poly exponent is bigger than second poly
      if((firstPoly.get(indexFirst)).getExp() > (secondPoly.get(indexSecond)).getExp()){
      
     result.add(indexResult,firstPoly.get(indexFirst));
     
     indexFirst += 1; indexResult +=1; firstLeft -=1;
        
      }
      //oppisate of last case
      if((firstPoly.get(indexFirst)).getExp() < (secondPoly.get(indexSecond)).getExp()){
      
     result.add(indexResult,secondPoly.get(indexSecond));
     
     indexSecond += 1; indexResult +=1; secondLeft -=1;
      }
}
      //case where both terms are to the same power
      if((firstPoly.get(indexFirst)).getExp() == (secondPoly.get(indexSecond)).getExp()){
     
     total = (firstPoly.get(indexFirst)).getCoef() + (secondPoly.get(indexSecond)).getCoef();
        
     AddTerm term = new AddTerm(total,(firstPoly.get(indexFirst)).getExp());
     result.add(indexResult,term);
     
    
     indexFirst += 1; indexSecond += 1; indexResult += 1;
     firstLeft -= 1; secondLeft -= 1;
      }
   //Add any terms left from first poly
    while(firstLeft > 0){
     
      result.add(indexResult,firstPoly.get(indexFirst));
     
     indexFirst += 1; indexResult +=1; firstLeft -=1;
    }
    //Add any terms left in second polly
    while(secondLeft > 0){
       result.add(indexResult,secondPoly.get(indexSecond));
     
     indexSecond += 1; indexResult +=1; secondLeft -=1;
    }
  }
 
  
  //Poly print method
  public static void PrintPoly(GASingleLinkedListP2<AddTerm> poly){
    
    int counter;
    counter = poly.size()-2;
    if(counter != -1){
    for(int i = 0; counter>i; i++){
       
       System.out.print((poly.get(i)).getCoef() + "x^" + (poly.get(i)).getExp() + " + ") ;
     }
      
    //checks to see if second to last term is to power of 1
   if((poly.get(counter)).getExp() == 1){
       System.out.print((poly.get(counter)).getCoef() + "x" + " + ");
   }
   else{
       System.out.print((poly.get(counter)).getCoef() + "x^" + (poly.get(counter)).getExp() + " + ");
   }
    }
   counter += 1;
    //checks last term to check if term is to power 1 or 0
    if((poly.get(counter)).getExp() == 0){
       System.out.println((poly.get(counter)).getCoef());
    }
   if((poly.get(counter)).getExp() == 1){
       System.out.println((poly.get(counter)).getCoef() + "x");
   }
   if((poly.get(counter)).getExp() != 0 && (poly.get(counter)).getExp() != 1){
       System.out.println((poly.get(counter)).getCoef() + "x^" + (poly.get(counter)).getExp());
   }
  }
  

  
  
  public static void main(String[] args)
  {
    //variables
    int counter,answer;
    String sanswer;
    answer =1;
    counter = 0;
    //linked lists
    GASingleLinkedListP2<AddTerm> firstPoly = new GASingleLinkedListP2<AddTerm>();
    GASingleLinkedListP2<AddTerm> secondPoly = new GASingleLinkedListP2<AddTerm>();
    GASingleLinkedListP2<AddTerm> result = new GASingleLinkedListP2<AddTerm>();
    
     //First Polynomial    
    System.out.println("Enter terms for first Polynomil(In Standard Form):");
    
    //adds another term to poly if user wants
     while(answer != 0){
       if(answer == 1){
           getTerm(firstPoly,counter,counter);
          counter += 1;
       }
      sanswer  = JOptionPane.showInputDialog("Would you like to add another term?(1 for yes:0 for no)"); 
          answer = Integer.parseInt(sanswer);
     }
 
   //print out first Polynomial
   System.out.print("Your first Polynomial is ");
    PrintPoly(firstPoly);
     System.out.println("");
   //Second Polynomial    
   System.out.println("Enter terms for second Polynomil(In Standard Form):");
     answer =1;
     counter = 0;
    
    //adds another term to poly if user wants
     while(answer != 0){
       if(answer == 1){
           getTerm(secondPoly,counter,counter);
          counter += 1;
       }
      sanswer  = JOptionPane.showInputDialog("Would you like to add another term?(1 for yes:0 for no)"); 
          answer = Integer.parseInt(sanswer);
     }
    
   //print out second Polynomial
   System.out.print("Your second Polynomial is "); 
    PrintPoly(secondPoly);
       System.out.println("");
   //Add the polys together
   AddPolys(firstPoly,secondPoly,result);
       
   //print out the result
   System.out.print("Your result is ");
    PrintPoly(result);
  }
}