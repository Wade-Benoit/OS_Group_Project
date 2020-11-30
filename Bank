public class Bank {
    int numThreads;                    //number of current threads
    int numResources;                  //number of current resources
    int minAvailable = 1;              //minimum possible available resources
    int maxAvailable = 10;             //maximum possible available resources
    int minNeed = 0;                   //minimum amount of resources needed


    int[] available;                   //available resources vector
    int[] currentAvailable;            //currently available resources vector
    int[] customerResource;            //customer resources vector
    int[] safeCustomers;               //safe-sequence customer vector
    int[][] maximum;                   //maximum possible resource allocation matrix
    int[][] allocation;                //current allocation matrix
    int[][] need;                      //current resources-needed matrix

    public Bank(int m, int n) {

        //Instantiate resources and threads per command line
        this.numResources = m;
        this.numThreads = n;


        //Initiate available, currentAvailable, and safeCustomers vectors
        available = new int[this.numResources];
        currentAvailable = new int[this.numResources];
        safeCustomers = new int[this.numThreads];


        //Randomly initialize available and currentAvailable vectors - within bounds
        for (int i = 0; i < this.numResources; i++) {
            int allocationOfResource = (int) Math.round(Math.random() *
                    (this.maxAvailable - minAvailable) + minAvailable);
            available[i] = allocationOfResource;
            currentAvailable[i] = allocationOfResource;
        }


        //Instantiate maximum matrix with current amount of threads and resources
        maximum = new int[numThreads][numResources];

        //Initialize maximum matrix
        for (int i = 0; i < maximum.length; i++) {
            for (int j = 0; j < maximum[i].length; j++) {

                int maxs = (int) Math.round(Math.random() *
                        (this.available[j] - minNeed) + minNeed);
                maximum[i][j] = maxs % 4;

            }
        }


        //Instantiate allocation and need matrices
        allocation = new int[numThreads][numResources];
        need = new int[numThreads][numResources];

        //Set the need matrix equal to maximum matrix, so that they are equivalent
        for (int i = 0; i < numThreads; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = maximum[i][j];
            }
        }

        //Instantiate customerResource vector
        customerResource = new int[this.numResources];


        //Get initial state of vectors and matrices to begin program
        getState();

    }



    //Function calculates customer need for that specific customer and displays
    public void calculateCustomerNeed(int customerNum) {

        for (int i = 0; i < this.numResources; i++) {
            int customerNeed = maximum[customerNum][i] - allocation[customerNum][i];

            need[customerNum][i] = customerNeed = Math.abs(customerNeed);

        }

        displayNeed();

    }


    //Function where a specific customer requests resources up to three times, returns if successful
    public synchronized boolean requestResources(int customerNumber, int counter) {


        boolean avail = true;
        boolean last = false;


        if (counter >= 2) {
            last = true;
        }


        //We are over two requests, on the last request for this customer
        if (last) {
            //Check if any of the available resources are less than needed resources
            for (int i = 0; i < this.numResources; i++) {
                if (this.available[i] < need[customerNumber][i]) {
                    avail = false;
                }
            }


            //While an available resource is less than needed, wait
            while (avail == false) {

                try {
                    displayOnCommandLine("\nBank - Safe Sequence Not Found");
                    displayOnCommandLine("\nCustomer " + customerNumber + " must wait\n\n");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Subtract needed resources from available
            System.out.println("Customer " + customerNumber + " requests resources.");
            displayOnCommandLine("[ ");
            for (int i = 0; i < numResources; i++) {
                this.available[i] -= need[customerNumber][i];
                this.allocation[customerNumber][i] += need[customerNumber][i];
                System.out.print(need[customerNumber][i] + " ");
                need[customerNumber][i] -= need[customerNumber][i];

            }
            displayOnCommandLine("]\n");


            System.out.println();

            return false;

            //End if(last)
        } else {

            //When not last request, check if available is less than (1/2) needed resource
            for (int i = 0; i < this.numResources; i++) {
                if (this.available[i] < ((need[customerNumber][i]) / 2)) {
                    avail = false;
                }
            }


            //While an available resource is less than needed, wait
            while (avail == false) {

                try {
                    displayOnCommandLine("\nBank - Safe Sequence Not Found");
                    displayOnCommandLine("\nCustomer " + customerNumber + " must wait\n");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Subtract needed resources from available
            System.out.println("Customer " + customerNumber + " requests resources.");
            displayOnCommandLine("[ ");
            for (int i = 0; i < numResources; i++) {
                this.available[i] -= need[customerNumber][i] / 2;
                this.allocation[customerNumber][i] += need[customerNumber][i] / 2;
                System.out.print(need[customerNumber][i] / 2 + " ");
                need[customerNumber][i] -= need[customerNumber][i] / 2;

            }
            displayOnCommandLine("]\n");


            //Displays the Safe Sequence
            displayOnCommandLine("Bank - Safe Sequence:\n");
            displayOnCommandLine("[ ");
            for (int i = 0; i < numThreads; i++) {
                displayOnCommandLine(i + " ");
            }
            displayOnCommandLine("]\n");


            // displayCustomerRequest(customerNumber);

            displayAllocation();

            return false;
        }
    }



    //Function releases resources of a specific customer after allocation
    public synchronized void releaseRecources(int customerNumber) {


        for (int i = 0; i < numResources; i++) {

            this.available[i] += allocation[customerNumber][i];

            allocation[customerNumber][i] = 0;
        }
        System.out.println();
        System.out.println("Customer " + customerNumber + " has released.");
        System.out.println();

        notifyAll();

    }


    //Function determines if specific customer's allocation request is safe and returns answer
    public boolean safeProcess(int customerNumber) {
        boolean safe = false;

        for (int i = 0; i < this.numResources; i++) {
            if (currentAvailable[i] >= need[customerNumber][i])
                safe = true;

            else {
                safe = false;
                break;
            }

        }

        return safe;
    }



    //Function allocates resources of current process if safe, displays available matrix
    public void runProcess(int customerNumber) {

        if (safeProcess(customerNumber)) {

            for (int j = 0; j < this.numResources; j++) {
                currentAvailable[j] = allocation[customerNumber][j] + currentAvailable[j];

            }
            displayOnCommandLine("Customer " + customerNumber + " request is granted\n");

            displayCurrentlyAvailable();

            displayOnCommandLine("\n");

        }

    }

    //Function displays object to command line
    private static void displayOnCommandLine(Object o) {

        System.out.print(o);

    }


    //Function displays the request vector of specific customer
    public void displayCustomerRequest(int customerNumber) {

        displayOnCommandLine("\n\nCustomer " + customerNumber + " Request: \n");

        for (int b : this.customerResource) {
            displayOnCommandLine(b + " ");
        }

        displayOnCommandLine("\n");


    }


    //Function displays the current allocation matrix in process
    public void displayAllocation() {
        displayOnCommandLine("\nBank - Allocation: \n");


        for (int j = 0; j < this.allocation.length; j++) {
            displayOnCommandLine("[ ");
            for (int k = 0; k < this.allocation[j].length; k++) {
                displayOnCommandLine(this.allocation[j][k] + " ");
            }

            displayOnCommandLine("]\n");

        }
        displayOnCommandLine("\n");

    }


    //Function displays the current Need matrix, used in testing of program
    public void displayNeed() {
        displayOnCommandLine("\nBank - Need: \n");

        for (int j = 0; j < this.need.length; j++) {
            for (int k = 0; k < this.need[j].length; k++) {
                displayOnCommandLine(this.need[j][k] + " ");
            }

            displayOnCommandLine("\n");
        }

        displayOnCommandLine("\n");
    }



    //Function displays current maximum matrix
    public void displayMax() {

        displayOnCommandLine("\nBank - Max:\n");

        for (int i = 0; i < this.maximum.length; i++) {
            displayOnCommandLine("[ ");
            for (int j = 0; j < this.maximum[i].length; j++) {
                displayOnCommandLine(this.maximum[i][j] + " ");
            }
            displayOnCommandLine("]\n");
        }
    }


    //Function displays the initial resources available vector
    public void displayAvailable() {
        displayOnCommandLine("\nBank - Initial Resources Available:\n");

        displayOnCommandLine("[ ");
        for (int i = 0; i < this.available.length; i++) {
            displayOnCommandLine(this.available[i] + " ");
        }
        displayOnCommandLine("]\n");
    }



    //Function displays the current resources available vector
    public void displayCurrentlyAvailable() {
        displayOnCommandLine("\nCurrent Available Work: \n");

        for (int c : this.currentAvailable) {
            displayOnCommandLine(c + " ");
        }

        displayOnCommandLine("\n");
    }


    //Function calls to display the current available, max, and allocation matrices
    public void getState() {

        displayAvailable();
        displayMax();
        displayAllocation();

    }

    //Function displays the current/final available vector and allocation matrix
    public void getFinalState() {


        //Display the Final Available Vector
        displayOnCommandLine("\n\nFinal Available Vector:\n");
        displayOnCommandLine("[ ");
        for (int i = 0; i < this.available.length; i++) {
            displayOnCommandLine(this.available[i] + " ");
        }
        displayOnCommandLine("]\n");


        //Display the Final Allocation Matrix
        displayOnCommandLine("\nFinal Allocation Matrix:\n");


        for (int j = 0; j < this.allocation.length; j++) {
            displayOnCommandLine("[ ");
            for (int k = 0; k < this.allocation[j].length; k++) {
                displayOnCommandLine(this.allocation[j][k] + " ");
            }
            displayOnCommandLine("]\n");

        }

    }

}
