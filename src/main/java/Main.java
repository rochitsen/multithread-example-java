import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Enter number of threads: ");

        //Get user input for number of threads
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        final NumberStream stream = new NumberStream();

        //Generate random numbers thread
        for(int i=0; i<n; i++){
            new Thread(){
                public void run(){
                    stream.generateNumbers();
                }
            }.start();
        }

        //Monitor stack thread
        new Thread(){
            public void run(){
                stream.monitor();
            }
        }.start();

        //Check numbers in list thread
        new Thread(){
            public void run(){
                stream.checkNumbers();
            }
        }.start();
    }
}
