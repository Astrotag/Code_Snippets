package greigmmyles.coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class VendingMachineManager
{
    //Variables

    public VendingMachineManager()
    {

    }

    /**
     * A method that begins to give the client change. Potentially throws a
     * custom exception saying it has no more coins to give out.
     * 
     * NOTE : This will also have to take the coins input to the machine into
     * consideration TODO
     * 
     * @param specificCoins
     * @param coins
     * @param totalValue
     * @return a BigDecimal that will contain the current balance after change
     *         has been given.
     */
    BigDecimal giveChange(ArrayList<Coin> twoPounds, ArrayList<Coin> onePounds, ArrayList<Coin> fiftyPences,
            ArrayList<Coin> twentyPences, ArrayList<Coin> tenPences, BigDecimal totalValue)
            throws CoinInventoryEmptyException
    {
        do {
            totalValue = dispenseChange(totalValue, twoPounds, onePounds, fiftyPences, twentyPences, tenPences);

            //            if (twoPounds.size() < 1 || onePounds.size() < 1 || fiftyPences.size() < 1 || twentyPences.size() < 1
            //                    || tenPences.size() < 1) {
            //                throw new CoinInventoryEmptyException();
            //            }
        }
        while (totalValue.doubleValue() > 0);
        return totalValue;
    }

    void popluateCoinsInventory(ArrayList<Coin> twoPounds, ArrayList<Coin> onePound, ArrayList<Coin> fiftyPence,
            ArrayList<Coin> twentyPence, ArrayList<Coin> tenPence)
    {
        /*
         * Creating specific coins that will be used to compare later in the
         * program
         */
        //        specificCoins.add(new Coin(2.0));
        //        specificCoins.add(new Coin(1.0));
        //        specificCoins.add(new Coin(0.50));
        //        specificCoins.add(new Coin(0.20));
        //        specificCoins.add(new Coin(0.10));

        /*
         * A for loop to get the stock of coins from start
         */
        for (int i = 0; i < 10; i++) {
            twoPounds.add(new Coin(2.00));
            onePound.add(new Coin(1.00));
            fiftyPence.add(new Coin(0.50));
            twentyPence.add(new Coin(0.20));
            tenPence.add(new Coin(0.10));
        }

        System.out.println(twoPounds.size());
    }

    /**
     * A method that checks if there is still a balance left and there are coins
     * left to give back as change. The if statement checks the highest
     * denomination first, to the lowest.
     * 
     * IF there is not enough change to give the user, then an exception will be
     * called. This will void any transaction and return the coins that had been
     * input. It will then prompt the administrator to refill the coins so the
     * machine can work properly TODO
     * 
     * 
     * @param totalValue
     * @param coins
     * @param specificCoins
     * @return the totalValue which will have either the remainder or be zero.
     */
    private static BigDecimal dispenseChange(BigDecimal totalValue, ArrayList<Coin> twoPounds,
            ArrayList<Coin> onePounds, ArrayList<Coin> fiftyPences, ArrayList<Coin> twentyPences,
            ArrayList<Coin> tenPences)
    {
        if (totalValue.doubleValue() >= 2.00 && twoPounds.size() > 0) {
            BigDecimal twoPound = new BigDecimal(2.00);
            if (dispenseTwoPound(twoPounds)) {
                totalValue = totalValue.subtract(twoPound);
            }

        }
        else if (totalValue.doubleValue() >= 1.00 && onePounds.size() > 0) {
            BigDecimal onePound = new BigDecimal(1.00);

            if (dispenseOnePound(onePounds)) {
                totalValue = totalValue.subtract(onePound);
            }
        }
        else if (totalValue.doubleValue() >= 0.50 && fiftyPences.size() > 0) {
            BigDecimal fiftyPence = new BigDecimal(0.50);

            if (dispenseFiftyPence(fiftyPences)) {
                totalValue = totalValue.subtract(fiftyPence);
            }
        }
        else if (totalValue.doubleValue() >= 0.20 && twentyPences.size() > 0) {
            BigDecimal twentyPence = new BigDecimal(0.20);

            if (dispenseTwentyPence(twentyPences)) {
                totalValue = totalValue.subtract(twentyPence);
            }
        }
        else if (totalValue.doubleValue() >= 0.10 && tenPences.size() > 0) {
            BigDecimal tenPence = new BigDecimal(0.10);
            if (dispenseTenPence(tenPences)) {
                totalValue = totalValue.subtract(tenPence);
            }
        }
        
        else {
            //TODO - Alert the admin for the machine to restock the machine with coins and refund the previous transaction.
        }

        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseTenPence(ArrayList<Coin> tenPences)
    {
        for (Coin coin : tenPences) {
            if (tenPences.size() > 0) {
                tenPences.remove(coin);
                System.out.println("Dispensed 10p coin");
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseTwentyPence(ArrayList<Coin> twentyPences)
    {
        for (Coin coin : twentyPences) {
            if (twentyPences.size() > 0) {
                twentyPences.remove(coin);
                System.out.println("Dispensed 20p coin");
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseFiftyPence(ArrayList<Coin> fiftyPences)
    {
        for (Coin coin : fiftyPences) {
            if (fiftyPences.size() > 0) {
                fiftyPences.remove(coin);
                System.out.println("Dispensed 50p coin");
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseOnePound(ArrayList<Coin> onePounds)
    {
        for (Coin coin : onePounds) {
            if (onePounds.size() > 0) {
                onePounds.remove(coin);
                System.out.println("Dispensed £1 coin");
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseTwoPound(ArrayList<Coin> twoPounds)
    {
        for (Coin coin : twoPounds) {
            if (twoPounds.size() > 0) {
                twoPounds.remove(coin);
                System.out.println("Dispensed £2 coin");
                return true;
            }
        }
        return false;
    }

}
