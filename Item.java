public class Item {
   private ItemType type;                  //attributer eller elementen i classen
   private int value = 0;

  public Item(ItemType type, int value){    
   this.type = type;  
   this.value = value;  
  }

 
  public ItemType getType(){          //eftersom nu är this.type lika med type så hanterar classen
    return this.type;                            //den type vi har gett den. Då är den type vi returnerar i denna
    }                                       //funktion samma.
    
public int getValue(){                  //samma sak för value
    return this.value;    
}

}


//Konstruktörn som ska heta samma som klassen och
//tar in attributerna. this.type reffererar till atributen 
 //type och type till höger om lika med tecknet ska nu vara
 //den nuvarande typen classen ska hantera, vilket kan ges i 
 //mainmetoden som exempelvis "add".