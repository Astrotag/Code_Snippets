package greigmmyles.coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Driver
{

    public static void main(String[] args) throws CoinInventoryEmptyException
    {
        ArrayList<Coin> specificCoins = new ArrayList<Coin>();
//        ArrayList<Coin> coins = new ArrayList<Coin>();

        ArrayList<Coin> twoPounds = new ArrayList<Coin>();
        ArrayList<Coin> onePound = new ArrayList<Coin>();
        ArrayList<Coin> fiftyPence = new ArrayList<Coin>();
        ArrayList<Coin> twentyPence = new ArrayList<Coin>();
        ArrayList<Coin> tenPence = new ArrayList<Coin>();
        

        BigDecimal totalValue = new BigDecimal(38.10); //The value of £3.80 was selected as it should use one of every coin, to empty all coins, use the value £38.00
        totalValue.setScale(2, RoundingMode.HALF_EVEN);
        
        System.out.println(totalValue);
        
        VendingMachineManager vmm = new VendingMachineManager();
        vmm.popluateCoinsInventory(twoPounds, onePound, fiftyPence, twentyPence, tenPence);

        totalValue = vmm.giveChange(twoPounds, onePound, fiftyPence, twentyPence, tenPence, totalValue);
        
        System.out.println("Remaining Balance : £" + totalValue);
    }
}
