import java.util.*;

public class EntryPoint extends Account{


    private boolean multiplePump;
    protected Sales sales;

    public EntryPoint(){

        takePumpNO("PMS");
        takePumpNO("AGO");
        takePumpNO("DPK");

        System.out.println("\nFor today the sales in amount for \n" + "PMS is " + getPms() +
                                                             " and for AGO is " + getAgo() +
                                                             " and for DPK is " + getDpk());
        setAggregate(getPms(), getAgo(), getDpk());
        System.out.println("\nThe total sales amount of PMS, AGO and DPK is " + getAggregate());

        setExpenses(collectExpenses());
        System.out.println("\nThe total amount of Expenses is " + getExpenses());

        setBox(getAggregate(), getExpenses());
        System.out.println("\nThe expected savings for today's market is " + getBox());
    }

    public void takeInput(String product, int pump){

        String productName;
        if (multiplePump)
             productName = product + " pump " + pump ;
        else
            productName = product;

        System.out.print("Please enter the amount of price you used today for " + productName + " . ");
        int x = 0;
        try {
            x = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You've entered an invalid value");
            takeInput(product, pump);
        } finally {

            switch (x) {
                case 1 -> {
                    onePrice(productName);
                    break;
                }
                case 2 -> {
                    twoPrice(productName);
                    break;
                }
                case 3 -> {
                    threePrice(productName);
                    break;
                }
                default -> {
                    System.out.println("You've entered an invalid value");
                    takeInput(product, pump);
                }
            }
        }
    }

    private void onePrice(String product) {

        setOpeningMeter(userDoubleValue("Please enter the opening meter for " + product + " . "));
        setRtt(userDoubleValue("Please enter RTT for " + product + " if there's any . "), "");
        setClosingMeter(userDoubleValue("Please enter the closing meter for " + product + " . "));
        setPrice(userDoubleValue("Please enter the unit price for " + product + " . "));

        if(meterCorrelation(getOpeningMeter(), getClosingMeter(), getRtt(), getPrice()))
            onePrice(product);
        sales = new Sales(getOpeningMeter(), getClosingMeter(), getPrice(), Optional.of(getRtt()));
    }

    private void twoPrice(String product){

        setOpeningMeter(userDoubleValue("Please enter the opening meter for " + product + " . "));
        double  rtt1 = userDoubleValue("Please input the first RTT for " + product + " if there's any . "),
                closing1 = userDoubleValue("Please enter the first closing meter for " + product + " . "),
                price1 = userDoubleValue("Please enter the first unit price for " + product + " . "),
                rtt2 = userDoubleValue("Please input the second RTT for " + product + " if there's any . "),
                closing2 = userDoubleValue("Please enter the second closing meter for " + product + " . "),
                price2 = userDoubleValue("Please enter the second unit price for " + product + " . ");

        if(meterCorrelation(getOpeningMeter(), closing1, rtt1, price1) || meterCorrelation(closing1, closing2, rtt2, price2))
            twoPrice(product);
        sales = new Sales(getOpeningMeter(), closing1, price1, rtt1,
                                     closing2, price2, rtt2);
    }

    private void threePrice(String product){

        setOpeningMeter(userDoubleValue("Please enter the opening meter for " + product + " . "));
        double  rtt1 = userDoubleValue("Please input the first RTT for " + product + " if there's any . "),
                closing1 = userDoubleValue("Please enter the first closing meter for " + product + " . "),
                price1 = userDoubleValue("Please enter the unit price for " + product + " . "),
                rtt2 = userDoubleValue("Please input the second RTT for " + product + " if there's any . "),
                closing2 = userDoubleValue("Please enter the second closing meter for " + product + " . "),
                price2 = userDoubleValue("Please enter the unit price for " + product + " . "),
                rtt3 = userDoubleValue("Please input the third RTT for " + product + " if there's any . "),
                closing3 = userDoubleValue("Please enter the third closing meter for " + product + " . "),
                price3 = userDoubleValue("Please enter the third unit price for " + product + " . ");

        if(meterCorrelation(getOpeningMeter(), closing1, rtt1, price1) ||
                    meterCorrelation(closing1, closing2, rtt2, price2) ||
                    meterCorrelation(closing2, closing3, rtt3, price3))
            threePrice(product);

        sales = new Sales(getOpeningMeter(), closing1, price1, rtt1,
                closing2, price2, rtt2,
                closing3, price3, rtt3);
    }

    private void takePumpNO(String product){

        System.out.print("How many pump did you used for " + product + " in the station today . ");
        int pumpNo = new Scanner(System.in).nextInt();
        multiplePump = pumpNo > 1;

        for (int i = 0; i < pumpNo  ; i++) {
            takeInput(product, i + 1);

            if (product.trim().equalsIgnoreCase("PMS")){

                setPms(sales.getMeterSales());
            } else if (product.trim().equalsIgnoreCase("AGO")){

                setAgo(sales.getMeterSales());
            } else if (product.trim().equalsIgnoreCase("DPK")){

                setDpk(sales.getMeterSales());
            }
            System.out.println();
        }
    }

    private List<Double> collectExpenses() {

        List<Double> inputList = new ArrayList<>();
        double input;
        String string;

        System.out.println("Enter values (type -1 as expenses or end for expenses name to terminate):");

        while (true) {

            System.out.print("Please enter the name of expenses followed by the amount. \n" +
                    "Name of expenses . . . ");
            string = new Scanner(System.in).next();
            if (string.equals("end"))
                break;

            System.out.print("Amount . . ");
            input = new Scanner(System.in).nextDouble();
            if (input == -1)
                break;

            inputList.add(input);
        }
        return inputList;
    }

    private double userDoubleValue(String string){

        System.out.print(string);
        try {
            return new Scanner(System.in).nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid value");
            userDoubleValue(string);
        }
        return 0;
    }

    private boolean meterCorrelation(double opening, double closing, double rtt, double price){

        if (closing < opening || (closing - rtt) < opening || rtt < 0 || closing < 0 || opening < 0 || price < 0){
            System.out.println("""
                                Closing meter must be (greater than or equal to opening meter) or\s
                                Closing meter minus rtt must be (greater than or equal to opening meter) or \
                                values cannot be negative
                                """);
            return true;
        } else
            return false;
    }

}