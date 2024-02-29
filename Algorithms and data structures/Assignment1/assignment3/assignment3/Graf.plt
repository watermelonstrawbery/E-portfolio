set terminal png
set terminal pngcairo
#set terminal epslatex color colortext
#set output "mygraph.tex"

set output "assignment3.png"
set termoption enhanced
set title ""
set autoscale

set key left center

set logscale xy
set xrange [1000:16000]


set xlabel "n"

set ylabel "time in {/symbol m}s"

plot "graph1.txt" u 1:2 w linespoints title "Selectionsort", \
     "graph1.txt" u 1:3 w linespoints title "Insertionsort", \
     "graph1.txt" u 1:4 w linespoints title "Mergesort" 