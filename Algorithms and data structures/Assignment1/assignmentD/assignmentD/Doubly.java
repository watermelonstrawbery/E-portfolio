public class Doubly{

    public static Cell first;

   
     
    public Doubly(){
         first = null;

    }

    public Doubly(int n) {
      Cell last = null;
      for (int i = 0; i < n; i++) {
        // Create a new cell with proper references
      Cell newCell = new Cell(i, last, null);
      
      // If 'last' is not null, set the 'tail' reference of the previous cell
      if (last != null) {
        last.nxt = newCell; 
      }

      last = newCell; // Update 'last' to the newly created cell
    }
  first = last; // Update 'first' to the first cell
  }
 
    public Cell add(int item){
      Cell newitem = new Cell(item, first, null);

      if(first != null){
        first.prv = newitem;
      }
      first = newitem;

      return newitem;
    }
    
   
    public void remove(int item){

      Cell current = first;

      while(current != null){
          if(current.head == item){
            if(current.prv != null){
              current.prv.nxt = current.nxt;
            }
            else
            first = current.nxt;
            if(current.nxt != null)
              current.nxt.prv = current.prv;
          }
          current = current.nxt;
      }
    }


    public static void unlink(Cell cell) {
      Cell current = first;
  
      while (current != null) {
          if (current == cell) {
              if (cell.prv != null) {
                  cell.prv.nxt = cell.nxt;
              } else {
                  // If cell is the first node, update 'first'
                  first = cell.nxt;
              }
  
              if (cell.nxt != null) {
                  cell.nxt.prv = cell.prv;
              }
  
              // Exit the loop once the cell is unlinked
              break;
          }
  
          current = current.nxt;
      }
  }
  
/* 
    public static void unlink(Cell cell){
        
      Cell current = first;
      while(current != null){
          if(current == cell){
            if(current.prv != null && cell.nxt != null){
             cell.prv.nxt = cell.nxt;
             cell.nxt.prv = cell.prv;
            } 
            if(cell.nxt != null && current.prv == null){
              first = cell.nxt;
              cell.prv = null;
            }
            if(cell.nxt == null && cell.prv != null)
              cell.prv.nxt = null;

          }
         current = current.nxt;
           
      }
    }
*/

    public static void insert(Cell cell){
       
      
      if(first != null){
        cell.nxt = first;
        first.prv = cell;
        first = cell;

      } else{
        first = cell;
        cell.nxt = null;
        cell.prv = null;
      }     
    }

}
