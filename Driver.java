import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        int lowerBound = 1;                 //Lower bound of possible number of resources
        int upperBound = 10;                //Upper bound of possible number of resources
        int resourceCount = 1;              //Initialize resource count default value
        int threadCount = -1;               //Initialize thread count default value




        //Check for valid Input by user on Command Line
        if (args.length >= 2) {
            boolean inputValid = true;

            try {
                resourceCount = Integer.parseInt(args[0]);
                threadCount = Integer.parseInt(args[1]);


            } catch (NumberFormatException e) {
                displayOnCommandLine("Input Could Not Be Converted To Int./n");
                inputValid = false;
            }

            String recourceCountInvalidString = String.format(
                    "Invalid Resource Count", resourceCount,
                    lowerBound, upperBound);
            String threadCountInvalidString = String.format(
                    "Invalid Thread Count", threadCount,
                    lowerBound, upperBound);

            if (resourceCount < lowerBound || resourceCount > upperBound) {
                displayOnCommandLine(recourceCountInvalidString);
                inputValid = false;
            }

            if (threadCount < lowerBound || threadCount > upperBound) {
                displayOnCommandLine(threadCountInvalidString);
                inputValid = false;
            }

            if (!inputValid) {
                displayOnCommandLine("Invalid Parameters \n");
                System.exit(0);
            }

        } else if (args.length == 0) {

            Scanner commandlineScanner = new Scanner(System.in);

            String recourceCountRequestString = String.format("Enter Resource Count (%s-%s): ",
                    lowerBound, upperBound);
            String threadCountRequestString = String.format("Enter Thread Count (%s-%s): ", lowerBound,
                    upperBound);

            while (resourceCount < lowerBound || resourceCount > upperBound) {
                displayOnCommandLine(recourceCountRequestString);
                resourceCount = commandlineScanner.nextInt();
            }

            displayOnCommandLine("\n");

            while (threadCount < lowerBound || threadCount > upperBound) {
                displayOnCommandLine(threadCountRequestString);
                threadCount = commandlineScanner.nextInt();
            }
            commandlineScanner.close();

        } else {
            displayOnCommandLine("Command Line Error\n");
            System.exit(0);
        }


        //Run the bankers algorithm with thread and resource numbers, m and n
        runBankersAlgorithm(3, 8);

    }


    //Display object on command line
    private static void displayOnCommandLine(Object o) {
        System.out.print(o);
    }



    //Function driver for Bankers Algorithm
    private static void runBankersAlgorithm(int resourceCount, int threadCount) {

        String displayInputsString = String.format
                ("Running...",
                        resourceCount, threadCount);
        displayOnCommandLine(displayInputsString);


        //Bank object Instantiated, bank
        Bank bank = new Bank(resourceCount, threadCount);

        //Bank Thread Instantiated, customers
        BankerThread[] customers = new BankerThread[threadCount];


        //Initialize all customers as bank threads
        for (int p = 0; p < customers.length; p++) {
            customers[p] = new BankerThread(bank, p);
        }

        //Start all Customer Threads
        for (BankerThread p : customers) {
            p.start();
        }


        //Join all Customer Threads
        for (BankerThread z : customers) {
            try {
                z.join();
            }

            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }


        //Display Final State of Resource Vector and Allocation Matrix
        bank.getFinalState();
    }


}
