# Linear-Equations-Solver
Run program with args like -in input.txt -out output.txt else program will seek for test2.txt and output.txt files  
Input should be like:  
"Count of arguments(n)" "Count of equations(m)"  
c11 c21 ... c1n b1  
...  
cm1 cm2 ... cmn bm  
  
E.g.
intput.txt:  
3 3  
1+2i +1.5-1.1i 2.12 91+5i  
-1+3i 1.2+3.5i -3.3 1+15i  
12.31 1.3-5i 12.3i -78.3i  
  
output.txt:  
6.7333528614668126-22.997542234040576i  
-1.7976070999255387+2.084049185255297i  
-1.7976070999255387+2.084049185255297i  
  
I didn’t specifically round, because the result will be better to round those who will use it. Might be artifacts like 0.166666666666667 due to the specifics of computer computing.
  
#Classes
There are two structural classes: Matrix that contains List of Rows  
1) Matrix contains several methods to work with that list(get, set, size, etc)  
2) Row contains List of Complex and has methods to work with the list  
3) Complex - class that has 2 values: real and image, and besides getters and setters implements primitive algebraic operations(add, subtract, multiply and divide)  
  
4) LinearEquation - class with static methods to work with matrices:  
a) Complex[] solve(Matrix matrix) - solve linear equation in matrix form.  
b) void rowReduce(Matrix matrix) - makes the matrix triangular  
c) boolean checkOnCorrectness(Matrix matrix) - check whether matrix doesn't have solutions   
d) boolean checkOfInfinity(Matrix matrix) - check whether matrix has infinitely many solutions.  
e) Complex[] normalizeOrder(Complex[] complex) - normalize order of an answer in case when we swap columns  
  
5) interface Command and it's implementations to work with Matrix and Row. Because of this kind of interaction with classes we can return each state of Matrix and Row using undo. Class Main contains static Deque<Command> commandHistory that contains history of using commands.  
a) void execute()  
b) void undo()
