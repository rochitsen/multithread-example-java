import java.util.*;
import java.util.Random;

class NumberStream extends Thread {

    Stack numStack = new Stack();
    int count;
    int min = 1;
    int max = 10;

    double avg;
    double sum;

    //Method to generate random numbers between 1 and 10 (inclusive).
    //Will generate 60 random numbers.
    synchronized void generateNumbers() {
        System.out.println("Generate numbers thread called:");
        count = 0;
        while (count <= 60) {
            Random rnd = new Random();
            numStack.add(rnd.ints(min, max + 1).findFirst().getAsInt());
            count++;
            System.out.println(numStack.size());
        }
    }

    //Method to check that values have been added in stack.
    //Notifies the waiting thread to run once stack has values.
    synchronized void monitor(){
        System.out.println("Monitor thread called:");
        if(!numStack.isEmpty()) notifyAll();
    }

    //Method to get most recent (30) elements from stack to list
    //Compute the max, min, frequency and avg of elements in list
    synchronized void checkNumbers() {
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("Thread interrupted");
        }
        System.out.println("CheckStream thread called");
        System.out.println("Stack size: " + numStack.size());
        ArrayList<Integer> elementsFromStack = new ArrayList<Integer>();
        //Get the most recent 30 elements from the stack
        for(int i=0; i<30; i++){
            try{
                //Add to array list
                elementsFromStack.add((Integer) numStack.get(i));
            } catch (Exception e){
                System.out.println(e);
            }
        }
        //Max value in list
        System.out.println("Max value element in list: " + Collections.max(elementsFromStack));
        //Min value in list
        System.out.println("Min value element in list: " + Collections.min(elementsFromStack));
        //Frequency of a specific number in list. eg., 5
        System.out.println("Frequency of value 5 in list: " + Collections.frequency(elementsFromStack,5));
        //Average of elements in list
        for(int i=0; i<elementsFromStack.size(); i++){
            sum += elementsFromStack.get(i);
        }
        avg = sum / elementsFromStack.size();
        System.out.println("Average of elements in list: " + avg);
    }
}
