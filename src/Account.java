import java.util.List;

public abstract class Account extends StockManager{
    private double pms, ago, dpk, aggregate, box, expenses;

    public double getAggregate() {
        return aggregate;
    }

    public void setAggregate(double PMS, double AGO, double DPK) {

        this.aggregate = PMS + AGO + DPK;
    }

    public double getBox() {

        return box;
    }

    public void setBox(double aggregate, double expenses) {
        this.box = roundAmount( aggregate - expenses );
    }

    public double getPms() {

        return pms;
    }

    public void setPms(double pms) {
        this.pms += roundAmount(pms);
    }

    public double getAgo() {
        return ago;
    }

    public void setAgo(double ago) {

        this.ago += roundAmount(ago);
    }

    public double getDpk() {
        return dpk;
    }

    public void setDpk(double dpk) {

        this.dpk += roundAmount(dpk);
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Double> expenses) {

        for (double expense : expenses) {
            this.expenses += expense;
        }
    }

    public double roundAmount(double amount){

        double remainder = Math.ceil(amount) % 5;
        if (remainder == 1) {
            return amount + 4;
        } else if (remainder == 2) {
            return amount + 3;
        } else if (remainder == 3) {
            return amount + 2;
        } else if (remainder == 4) {
            return amount + 1;
        } else
            return amount;

    }
}