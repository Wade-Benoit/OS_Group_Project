
public class BankerThread extends Thread {

    //Instantiate Bank object
    Bank bank;
    int counter = 0;
    int customerNum;



    //BankerThread constructor
    public BankerThread(Bank b1, int customerNum) {
        this.bank = b1;
        this.customerNum = customerNum;
    }

    //Override the run method to be executed by start() method in Driver class
    @Override
    public void run() {


        //Initial request of resources by customer
        bank.requestResources(customerNum, counter);
        counter++;
        try {
            Thread.sleep(250);
        }catch(Exception e) {}


        //Second request of resources by customer
        bank.requestResources(customerNum, counter);
        counter++;
        try {
            Thread.sleep(250);
        }catch(Exception e) {}

        //Third request of resources by customer
        bank.requestResources(customerNum, counter);


        //Release of resources by customer after three requests
        bank.releaseRecources(customerNum);



    }



}
