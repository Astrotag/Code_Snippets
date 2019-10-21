package greigmmyles.coins;

public class Coin
{

    private double coinValue;
    
    public Coin(double coinValue) {
        this.setCoinValue(coinValue);
    }

    /**
     * @return the coinValue
     */
    public double getCoinValue()
    {
        return coinValue;
    }

    /**
     * @param coinValue the coinValue to set
     */
    public void setCoinValue(double coinValue)
    {
        this.coinValue = coinValue;
    }
    
    public String toString() {
        return "\n" + getCoinValue();
    }
}
