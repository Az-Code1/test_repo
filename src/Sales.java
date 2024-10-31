import java.util.Optional;

public class Sales {

    private double meterSales;

    public Sales(double openingMeter, double closingMeter, double unitPrice, Optional<Double> rtt){

        double unitSales = (closingMeter - openingMeter - rtt.orElse(0.0)) * unitPrice;

        setMeterSales(unitSales);
    }

    public Sales(double openingMeter, double closingMeter1, double unitPrice1, double rtt1,
                                      double closingMeter2, double unitPrice2, double rtt2){

        double  unitSales1 = (closingMeter1 - openingMeter  - rtt1) * unitPrice1,
                unitSales2 = (closingMeter2 - closingMeter1 - rtt2) * unitPrice2;

        setMeterSales( unitSales1 + unitSales2 );
    }

    public Sales(double openingMeter, double closingMeter1, double unitPrice1, double rtt1,
                                      double closingMeter2, double unitPrice2, double rtt2,
                                      double closingMeter3, double unitPrice3, double rtt3){

        double  unitSales1 = (closingMeter1 - openingMeter  - rtt1) * unitPrice1,
                unitSales2 = (closingMeter2 - closingMeter1 - rtt2) * unitPrice2,
                unitSales3 = (closingMeter3 - closingMeter2 - rtt3) * unitPrice3;

        setMeterSales( unitSales1 + unitSales2 + unitSales3);
    }

    public double getMeterSales()
    {
        return meterSales;
    }

    public void setMeterSales(double meterSales) {

        this.meterSales = meterSales;

    }
}

//enum TestEnum{
//    ADMIN, MANAGER, SUPERVISOR, USER
//}
