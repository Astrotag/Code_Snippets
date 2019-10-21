package greigmmyles.coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Driver
{

    public static void main(String[] args)
    {
        //Variables
        ArrayList<Coin> specificCoins = new ArrayList<Coin>();
        ArrayList<Coin> coins = new ArrayList<Coin>();
        BigDecimal totalValue = new BigDecimal(38.10); //The value of £3.80 was selected as it should use one of every coin, to empty all coins, use the value £38.00

        popluateCoinsInventory(specificCoins, coins);

        totalValue = giveChange(specificCoins, coins, totalValue);
        
        System.out.println("Remaining Balance : £" + totalValue);
        //        System.out.println(coins.size()); FOR DEBUG PURPOSES
    }

    /**
     * A method that begins to give the client change. Potentially throws a custom exception saying it has no more coins to give out. 
     * 
     * NOTE : This will also have to take the coins input to the machine into consideration TODO
     * 
     * @param specificCoins
     * @param coins
     * @param totalValue
     * @return a BigDecimal that will contain the current balance after change has been given. 
     */
    private static BigDecimal giveChange(ArrayList<Coin> specificCoins, ArrayList<Coin> coins, BigDecimal totalValue)
    {
        /**
         * A try catch that has a nested do while loop, which loops through
         * until the totalValue is < 0 or if the NoCoins exception is thrown.
         */
        try {
            do {
                totalValue = dispenseChange(totalValue, coins, specificCoins);
            }
            while (totalValue.doubleValue() > 0);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return totalValue;
    }

    private static void popluateCoinsInventory(ArrayList<Coin> specificCoins, ArrayList<Coin> coins)
    {
        /*
         * Creating specific coins that will be used to compare later in the
         * program
         */
        specificCoins.add(new Coin(2.0));
        specificCoins.add(new Coin(1.0));
        specificCoins.add(new Coin(0.50));
        specificCoins.add(new Coin(0.20));
        specificCoins.add(new Coin(0.10));

        /*
         * A for loop to get the stock of coins from start
         */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < specificCoins.size(); j++) {
                coins.add(specificCoins.get(j));
            }
        }

        System.out.println(coins.size());
    }

    /**
     * The algorithm that checks to see if there is balance left and then
     * dispenses the coins. This uses an if statement check. Checking if there
     * is an amount left and if there is a specific coin in the ArrayList that
     * can be dispensed If there is less than an amount left, it will move on.
     * As only certain coins can be inserted (via buttons) there should never be
     * a remainder of < 10p. If there is also no coins of that value (ie £2)
     * then it will also move on to the next statement.
     * 
     * It is possible to run out of coins, though come the main program I will
     * be fixing that so that it will be checking if it has enough change every
     * time a coin is entered. If there is a remainder the NoCoins exception
     * will be thrown. If it is zero then the program will exit.
     * 
     * @param totalValue
     * @param coins
     * @param specificCoins
     * @return the totalValue which will have either the remainder or be zero.
     */
    private static BigDecimal dispenseChange(BigDecimal totalValue, ArrayList<Coin> coins,
            ArrayList<Coin> specificCoins)
    {
        if (totalValue.doubleValue() >= 2.00 && coins.contains(specificCoins.get(0))) {
            BigDecimal twoPounds = new BigDecimal(2.00);
            if (dispenseTwoPound(coins)) {
                totalValue = totalValue.subtract(twoPounds);
            }

        }
        else if (totalValue.doubleValue() >= 1.00 && coins.contains(specificCoins.get(1))) {
            BigDecimal onePound = new BigDecimal(1.00);

            if (dispenseOnePound(coins)) {
                totalValue = totalValue.subtract(onePound);
            }
        }
        else if (totalValue.doubleValue() >= 0.50 && coins.contains(specificCoins.get(2))) {
            BigDecimal fiftyPence = new BigDecimal(0.50);

            if (dispenseFiftyPence(coins)) {
                totalValue = totalValue.subtract(fiftyPence);
            }
        }
        else if (totalValue.doubleValue() >= 0.20 && coins.contains(specificCoins.get(3))) {
            BigDecimal twentyPence = new BigDecimal(0.20);

            if (dispenseTwentyPence(coins)) {
                totalValue = totalValue.subtract(twentyPence);
            }
        }
        else if (totalValue.doubleValue() >= 0.10 && coins.contains(specificCoins.get(4))) {
            BigDecimal tenPence = new BigDecimal(0.10);

            if (dispenseTenPence(coins)) {
                totalValue = totalValue.subtract(tenPence);
            }
        }

        else {
            NoCoinsException();
            return totalValue.setScale(2, RoundingMode.HALF_EVEN);
        }

        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * 
     * @param coins
     * @return if the coin has been dispensed
     */
    private static boolean dispenseTenPence(ArrayList<Coin> coins)
    {
        for (Coin coin : coins) {
            if (coin.getCoinValue() == 0.10) {
                coins.remove(coin);
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
    private static boolean dispenseTwentyPence(ArrayList<Coin> coins)
    {
        for (Coin coin : coins) {
            if (coin.getCoinValue() == 0.20) {
                coins.remove(coin);
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
    private static boolean dispenseFiftyPence(ArrayList<Coin> coins)
    {
        for (Coin coin : coins) {
            if (coin.getCoinValue() == 0.50) {
                coins.remove(coin);
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
    private static boolean dispenseOnePound(ArrayList<Coin> coins)
    {
        for (Coin coin : coins) {
            if (coin.getCoinValue() == 1) {
                coins.remove(coin);
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
    private static boolean dispenseTwoPound(ArrayList<Coin> coins)
    {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getCoinValue() == 2) {
                coins.remove(i);
                System.out.println("Dispensed £2 coin");
                return true;
            }
        }
        return false;
    }

    /**
     * A custom excpetion that is thrown if there are no coins left to dispense
     */
    private static void NoCoinsException()
    {
        throw new IllegalArgumentException("There are no coins that can refund the change");

    }
}
