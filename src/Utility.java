public class Utility {

    boolean getBarChart(){
        return false;
    }

    boolean getPieChart(){
        return getBarChart();
    }

    boolean getLineGraph(){
        return getBarChart();
    }

    boolean getColumnChart(){
        return getBarChart();
    }

    String getMarquee(){
        return null;
    }

    public boolean print(String statementType){

        if (statementType.trim().equalsIgnoreCase("")) {
            briefStatement();
        }
        else if (statementType.trim().equalsIgnoreCase(" ")) {
            fullStatement();
        }

        return false;
    }

    public String[] getNews(){
        return new String[]{"",""};
    }

    public void  briefStatement(){

    }
    public void  fullStatement(){

    }
    public boolean isLoss(){
        return Account.class.isAnonymousClass();
    }
    public boolean isProfit(){
        return false;
    }

    public void saveStatement(){



    }

}
