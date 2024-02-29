\documentclass[a4paper,11pt]{article}

\usepackage[utf8]{inputenc}

\usepackage{minted}

\begin{document}

\title{
    \textbf{Assignment 2}
}
\author{Saina Shamshirdar}
\date{August 2023}

\maketitle

\section*{Introduction}

Searching for an element is usually inconvenient and expensive in an unsorted array, since the program has to go through the whole array. Searching through a sorted array costs much less and makes the program more effective. In this assignment more optimized searching methods will be studied along with the relationship between the array size and the execution time. 

\section*{A first try}

We set up a bench mark for the "search-unsorted" method to search through an unsorted array. In order to provide unsorted arrays, we create a method to  generate random unsorted arrays. 

Now if we know that we have sorted arrays we can write a more optimized code which is shown down below:

\begin{minted}{java}
 public static boolean search_sorted(int[] array, int key){
        for(int index = 0; index < array.length; index++){
            if(array[index] >= key)
                if(array[index] == key )
                    return true;
                else 
                    return false;
        }
        return false;
    }        
\end{minted}

In the code snippet we check whether the element in the array is larger or equal to the key. If the answer is yes we should check if it is the key and return true. Otherwise, we return false because the array is sorted and anything larger than the key means that the key doesn't exist in the array. The sorted arrays that this method is going to use are built up by the "sorted" method given in the assignment. 

\begin{table}[h]
\begin{center}
\begin{tabular}{l|c|c}
\textbf{array size} & \textbf{unsorted} & \textbf{sorted}\\
\hline
  100      &  16 &     15\\
  200      &  31 &     30\\
  400      &  62 &     57\\
  600      &  90 &     78\\
  800      &  115 &     94\\
  1000      &  140 &     106\\
  
\end{tabular}
\caption{Runtime over searching for a key in sorted arrays verses unsorted arrays in microseconds.  }
\label{tab:table1}
\end{center}
\end{table}

The table above shows that the method for searching over unsorted arrays has a longer runtime than the search method for sorted arrays. As the arrays grow, the runtime increases too.

 

\subsection*{Binary and linear search}
\subsection*{Binary}

To prevent wasting time and searching for the key in every element of the array we can implement an algorithm that will find the key faster, in a more optimized way. It is called the binary search algorithm. It will begin in the middle of the array, searching for the key. If the key is not the middle,  it is either in the left half or the right half of the array. We should also take into consideration that if none of these cases is true then the code should return false.  
\begin{minted}{java}
if(first >= last || first <= last){
                return false; 
\end{minted}

That is when the pointer for first points to the same element as the pointer for last or vice versa. This happens naturally either at the end of the array or the beginning when the key doesn't exist and the pointers continue to be placed closer to the beginning and the end of the array depending on which half we are at.

\subsection*{Linear}

The linear search algorithm can both search through a sorted and an unsorted array. That is because it holds two methods, one method to search through sorted arrays and the other one to search through unsorted arrays. 

\begin{table}[h]
\begin{center}
\begin{tabular}{l|c|c|c}
\textbf{array size} & \textbf{linear unsorted} & \textbf{linear sorted} & \textbf{binary}\\
\hline
  100      &  16 &  15  & 15\\
  200      &  32 &   31  &30\\
  400      &  61 &   60  &57\\
  600      &  86 &  85   &78\\
  800      &  111 &  109   &94\\
  1000      &  137 &  134   &49\\
  1200    & 162  &  158   &51\\
  1400    &188  &  182   &52\\
   1600     &213   &  206   &53\\
  
\end{tabular}
\caption{Runtime over three methods  }
\label{tab:table1}
\end{center}
\end{table}

As shown in table 2, the three methods cost almost equally for smaller arrays. As the arrays grow the linear methods cost more, specially the unsorted method. While, the binary algorithm increases in the runtime in the beginning, but it stabilises around 50 microseconds as the arrays grow larger in size. The execution time for the linear method follows the O(n) function which indicates a linear relationship between array length and runtime. In contrast, the binary search performs in O(log n) which is highly efficient, specially for large array sizes as the runtime start to become more constant. 


\subsection*{Even better}

One way to search for duplicates in two unsorted arrays is to look for a duplicate in the second array for every element in the first array. However, using the binary search to look for duplicates in the second array makes the code more optimized whenever the two arrays are sorted. To optimize even more, every element from each array is compared to each other. Either, a duplicate is found or the array with a smaller element should increment and search for duplicates again. For easier comprehension these methods are now called unsorted, binary search and the better version. The time complexity relating to the unsorted method is \(O(N^2)\) where N is the number of elements in the first array. The function grows really fast by only a small increase in array size which is why the method is not so efficient. The good news is, the binary search operates in a O(N * log n) time complexity where N is the number of elements in the first array and n is the number of elements in the second array. This time complexity results in a much faster operation which is extremely better than the unsorted method. Finally, the best and most beneficial method, the "better version", performs with a time complexity of O(i + n). Each array has its own pointer that travel through the array while the counter updates every time a duplicate is found. This is so far the most efficient method to search for duplicates.

\begin{table}[h]
\begin{center}
\begin{tabular}{l|c|c|c}
\textbf{array size} & \textbf{unsorted} & \textbf{binary search} & \textbf{better version}\\
\hline
  100       &  2.9  &  1.4     & 0.7  \\
  200       &  11.5 &   2.7    &0.4  \\
  400       &  34.8 &   6.3    &1.0   \\
  800       &  131  &  26.3    &1.8  \\
  1600      &  505  &  71.3    &3.7  \\
  3200      &  1996 &  160     &6.9   \\
  6400      & 7908  &  344     &22   \\

  
\end{tabular}
\caption{Runtime over three methods to search for duplicates in two arrays}
\label{tab:table3}
\end{center}
\end{table}


Table 3 shows the runtime using the three methods: unsorted , binary search and the most optimized version. The unsorted method is as imagined the least effective and takes the longest time. The binary search is much more optimal when it comes to execution time compared to the unsorted method. As the arrays grow larger, the unsorted method start to cost more and more. As we reach array length 6400 the runtime has increased with thousands of microseconds while the execution time for binary search has only reached approximately 300 microseconds.

\subsection*{Conclusion}

The main focus of this assignment was to develop the code step by step in order to achieve optimized super efficient searching methods that cost the least and have super short runtime. The binary algorithm is absolutely a key when trying to do so as it decreases the options to half of the amount in each and every step which is why the search becomes rapid and the result is obtained in a  shorter amount of time. 
















\end{document}
