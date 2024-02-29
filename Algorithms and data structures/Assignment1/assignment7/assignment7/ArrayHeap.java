public class ArrayHeap {
    
    int size;
    int[] heap;
    int pos;

    public ArrayHeap(int length){
        size = length;
        pos = 0;
        heap = new int[length];

    }

    public void swap(int a, int b){
        
        int tmp;
        tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    public void add(int item){

       
        if(pos == size)
            throw new IndexOutOfBoundsException(null);
        heap[pos] = item;
        bubble(pos);
        pos += 1; 
        
    }

    public void bubble(int p){
        if(p == 0)
          return;
        int parent = (p-1)/2;
        if(heap[p] < heap[parent])  
            swap(p, parent);
        bubble(parent);
    }

    public int remove(){
       if(pos == 0)
       throw new IndexOutOfBoundsException(null);
       
       int min = heap[0]; 
       pos -= 1;  //varför?
       heap[0] = heap[pos];
       sink(0);
       return min;
    }


    public void sink(int s){
        int left = s*2 + 1;
        int right = s*2 + 2;

        if(right < pos){
            if(heap[left] < heap[right]){
                if(heap[left] < heap[s]){
                    swap(left, s);
                    sink(left);
                }
            }else{
                if(heap[right] < heap[s]){
                    swap(right, s);
                    sink(right);
                }
            }
        }else if (left < pos){
            if(heap[left] < heap[s]){
                swap(left, s);
            }
        }
    }
  

    

    public static void main(String[] args) {
        
        ArrayHeap heap = new ArrayHeap(6);

        int[] sek = {3, 5, 2,4,6,7};
        for (int i = 0; i < sek.length; i++) {
            heap.add(sek[i]);
        }
        heap.remove();
        
        for (int i = 0; i < heap.size; i++) {
           System.out.println(heap.heap[i]); 
        }
        
    }
}
