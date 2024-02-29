/*import java.rmi.server.Operation;

import javafx.beans.binding.When;

public class latex {
    \documentclass[a4paper,11pt]{article}

\usepackage[utf8]{inputenc}

\usepackage{minted}

\begin{document}

\title{\textbf{Algorithms and datastructures}

    \textbf{Assignment 1}
}
\author{Saina Shamshirdar}
\date{August 2023}

\maketitle

\section*{Introduction}

In this assignment we have discussed implementing a calculator using the \textit{Reverse Polish Notation} and stacks. However, the main focus of the task was to learn how to implement a stack and understand the relationship between the concepts named above. 

\section*{An expression}

The concept of the \textit{Reverse Polish Notation} and a stack are quite compatible which helps us implement stacks. In order to represent an expression, for instance "10 2 5 * +", we create a class called "Item". Each Item has now a type and a value. 

\subsection*{The calculator}

The calculator has a simple design consisting of an arithmetic expression, an instruction pointer and a stack. When running the program for the calculator, we look at the type of the Item and jump to the relevant operation code. For instance, to subtract two elements we need to pop them one by one. Do the subtraction and then push the result back in the stack as the code snippet shows down below:

\begin{minted}{java}
case SUB : {
        int y = stack.pop();
        int x = stack.pop();
        stack.push(x - y);
        break;
\end{minted}

\section*{Implementing the stack}

To implement the stack we need three files. One general file where we initiate the abstract methods of push and pop. One file for the static stack and one file for the dynamic stack. In the static stack we have an array, a pointer and a size because we want the stack to have a fixed size. 

\section*{Static stack}

To  implement the push function of the stack we simply put the given value in the array as long as the pointer is in the stack, also has an index less than the array size.
\begin{minted}{java}
public void Push(int Value){

   if(pointer < size)
    {
    mainstack[pointer] = Value;
    pointer++;
    }  
}    
\end{minted}

As long as the pointer points somewhere, which means that it does not hold the value "-1" it is possible to pop. So we basically return the value in the array where the pointer is pointing. 

\section*{Dynamic stack}

The difference between the static stack and the dynamic stack is that the dynamic stack starts with a determined size and adjusts its size depending on the demand. If there are more elements than the size to push, it will increase its size and vice versa. To do this we need to copy the array into a new larger array. I have chosen to duplicate the size for every time the stack becomes full.

\begin{minted}{java}
public void push(int Value){

   if(pointer == SIZE)
    {
    int[] newstack = new int[SIZE * 2];
       for(int i = 0; i < SIZE; i++)
        {
          newstack[i]=dynstack[i];
        }
    dynstack = newstack;   
    }
    dynstack[pointer++] = Value;
}    
\end{minted}

The logic of the pop function is that every time the number of the empty places is equal to the half of the array size, we copy the array into a new one. This new array should be one fourth smaller than the old array. So we save some space, in case we want to push more elements. An exception must also be thrown when the function "pop" tries to pop when the stack is empty. 



\subsection*{Benchmarks}

We set up a benchmark to estimate the cost of either stacks. To do this, we need an outer loop that it gonna determine the number of times we run the program. After that we need two inner loops; one to loop the push function and the other one to loop the pop function as you can see down below:

\begin{minted}{java}    
        for(int i = 0; i < loop; i++){
            
           
            for(int n = 0; n < ops; n++){
                stack.push(n);
            }
            for(int n = 0; n < ops; n++){
            stack.pop();
            }
        }
\end{minted}

It is important to note that the bench function receives three parameters loop, operation and stack. 
The loop speaks for how many times we acquire the outer loop to loop. Operation decides the number of operations (push and pop) to be performed in each loop. The stack can either be the static stack or the dynamic stack that we have created. 

In the main method we calculate the benchmark for the static stack and the dynamicstack. We divide the estimated time by the number of loops to  the run time for each 

\begin{table}[h]
\begin{center}
\begin{tabular}{l|c|c}
\textbf{prgm} & \textbf{runtime} & \textbf{ratio}\\
\hline
  first      &  115 &     1.0\\
  second      &  535 &     4.6\\
  third      &  420 &     3.6\\
\end{tabular}
\caption{Union and friends, list of 50000 elements, runtime in microseconds}
\label{tab:table1}
\end{center}
\end{table}

As you see in the table above, the run time per se might not be
interesting. The interesting thing is how it relates to something
else. Look at the ratios above, it gives you the information that we
are looking for. So when you include numbers, ask your self why you
have these numbers in the report. What is the purpose, can you
describe it in a better way?











\end{document}
 
}
*/