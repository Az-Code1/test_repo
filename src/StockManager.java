public abstract class StockManager {

    private double closingMeter, openingMeter, rtt, litresSold, appreciation, balance, price;
    int dipping;
    String reasonForRTT;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int previousDipping, double sales) {
        this.balance = previousDipping - sales;
    }

    public double getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(double lastDipping, double litresSold) {
        this.appreciation = lastDipping - litresSold;
    }

    public double getLitresSold() {
        return litresSold;
    }

    public void setLitresSold(double litresSold) {
        this.litresSold = getClosingMeter() - getOpeningMeter() - getRtt();
    }

    public double getClosingMeter() {
        return closingMeter;
    }

    public void setClosingMeter(double closingMeter) {
        this.closingMeter = closingMeter;
    }

    public double getOpeningMeter() {
        return openingMeter;
    }

    public void setOpeningMeter(double openingMeter) {
        this.openingMeter = openingMeter;
    }

    public double getRtt() {
        return rtt;
    }

    public void setRtt(double rtt, String reasonForRTT) {
        this.reasonForRTT = reasonForRTT;
        this.rtt = rtt;
    }

    public int getDipping() {
        return dipping;
    }

    public void setDipping(int dipping) {
        this.dipping = dipping;
    }

    public String getReasonForRTT() {
        return reasonForRTT;
    }
}
