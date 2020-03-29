# ThreadTest
Java program using threads

<b>`Main class`</b> 

This is the entry point for the program. Gets the user input for number of random number generation threads. The main class also starts three threads.

1.	First thread is started for run ‘n’ number of times (n is user input). This generates random numbers.
2.	Second thread is a monitor thread which checks if the Stack (where random values are stored) is empty or not.
3.	Third thread is to calculate the max, min, frequency and average of values that are in the stack.

<b>`NumberStream class`</b> 

This class contains the synchronized methods that the threads explained above execute.

1.	`generateNumbers()` method: This is run by first thread. Adds 60 random values in the Stack. Run multiple times based on user input.
2.	`monitor()` method: This is run by second thread. Keeps checking if the stack has any values added to it or not. When stack has values in it, this thread notifies the waiting thread to run.
3.	`checkNumbers()` method: This is run by third thread. The thread is waiting to be notified that values have been added in the stack. It then starts running and calculates max, min, frequency and avg for the last 30 random values.

