# OS_Group_Project
Bankers Algorithm Group Project 


CSCI 310


The purpose of this project is to increase your knowledge of the Bankers Algorithm, 
threads and synchronization by solving a problem using Java.  
Project Requirements
You are to 
implement the project described on pages 
344–346
of 
the textbook subject 
to the additional requirements outlined below:
1.
This assignment is a group assignment
.  You are expected to work in a group 
with one or two other students.
2.
It must be possibl
e to specify the values of 
m
(number of resource types) and 
n
(number of threads) via command line arguments.  The program must work 
correctly for values of m and n in the range 1 to 10.
3.
You are not expected to implement the Bank interface specified in
the textbook; 
rather, you can implement a different definition of the Bank class that meets 
these additional requirements.
4.
You are not expected to use the TestHarness.java file mentioned in the textbook.  
Instead, you 
must
randomly generate test scenarios by using random numbers, such as 
those available via the Math.random method.  You should randomly generate the 
maximum number of resources of each type that are available as well as the 
maximum demand by each of the custom
ers
for each resource type
; the latter demand 
should be generated to be less than or equal to the maximum number of resources.
5
.
In addition to requesting resources, it must be possible to release (or return) 
resources.
6.
Each customer thread must go 
through at least 
three
cycles of requesting 
resources and holding them for a random period of time (1 
–
5 seconds).  The 
second
and third
request
s
must
be for resources in addition to those 
previously 
held
.  In satisfying this requirement, you are expected
to use the Math.random 
and Thread.sleep methods.
The request
s
for 
a
number of instances of 
each 
resource type should be randomly generated so that 
they
are
less than or equal to 
the maximum 
need
by
the customer.
CSCI 310
2
Fall
2020
7
.
You must use the following Java 
keywords to help synchronize your program:
synchronized
, 
wait
, 
notify
(or 
notifyAll
in place of 
notify
).
8
.
While the program is executing, display messages that correspond to important 
events.  These messages are useful for debugging and should help to
demonstrate 
that the requests are processed correctly.
9
.
Run your program for at least two different sets of initial resources, number of 
customers
and requests.  This must include one execution with four resource 
types and five customer threads.  The o
utput must demonstrate that multiple 
customers can hold resources at the same time in a safe manner, and that 
additional requests that would not be safe must wait.
10
.
Your program output must demonstrate that when the program ends all instances 
of all 
resources types have been returned by the customer threads.
11.
Your program must be compatible with the version of Java that is installed on the 
Virtual Machine.  The version is:  
1.8.0_252
.  This version works well for the 
Java that has been discussed i
n this class.
Sample output:
The following output demonstrates some of the items / events that you might need to 
display to understand if your program is functioning correctly.  You may need to display 
additional information.
C:
\
RUNBANK>java RunBank 4 
5
Bank 
-
Initial Resources Available:
[ 6, 7, 6, 3 ]
Bank 
-
Max:
[ 4, 0, 0, 3 ]
[ 5, 2, 4, 0 ]
[ 2, 5, 5, 1 ]
[ 6, 2, 5, 2 ]
[ 5, 5, 4, 3 ]
Customer 0 making request:
[ 1, 0, 0, 0 ]
Bank 
-
Safe Sequence:
[ 0, 1, 2, 3, 4 ]
Bank 
-
Allocation matrix:
[ 1, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
Customer 0 request 0 granted
Customer 1 making request:
[ 0, 1, 2, 0 ]
Bank 
-
Safe Sequence:
[ 0, 1, 2, 3, 4 ]
CSCI 310
3
Fall
2020
Bank 
-
Allocation matrix:
[ 1, 0, 0, 0 ]
[ 0, 1, 2, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
Customer 2 making request:
[ 1, 2, 0, 0 ]
Bank 
-
Safe Sequence:
[ 0, 1, 2, 3, 4 ]
Bank 
-
Allocation matrix:
[ 1, 0, 0, 0 ]
[ 0, 1, 2, 0 ]
[ 1, 2, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
Customer 2 request 0 granted
Customer 3 
making request:
[ 3, 0, 3, 1 ]
Bank 
-
Safe Sequence not found
Bank:  Customer 3 must wait
Customer 4 making request:
[ 0, 3, 3, 0 ]
Bank 
-
Safe Sequence not found
Bank:  Customer 4 must wait
Customer 1 request 0 granted
Customer 2 making request:
[ 0, 0, 4
, 0 ]
Bank 
-
Safe Sequence not found
Bank:  Customer 2 must wait
Customer 1 making request:
[ 2, 0, 0, 0 ]
Bank 
-
Safe Sequence not found
Bank:  Customer 1 must wait
Customer 0 making request:
[ 1, 0, 0, 2 ]
Bank 
-
Safe Sequence:
[ 0, 1, 2, 3, 4 ]
Bank 
-
A
llocation matrix:
[ 2, 0, 0, 2 ]
[ 0, 1, 2, 0 ]
[ 1, 2, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
Customer 0 request 1 granted
Customer 0 releasing resources:
[ 2, 0, 0, 2 ]
<Requests of waiting customers may be retried>
.
.
.
CSCI 310
4
Fall
2020
Final Available vector:
[ 6, 7, 6, 3 ]
Final Allocation matrix:
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
[ 0, 0, 0, 0 ]
