package greigmmyles.coins;

public class CoinInventoryEmptyException extends Exception
{

    public CoinInventoryEmptyException(String message)
    {
        super(message);
    }
    
    public CoinInventoryEmptyException()
    {
        super();
        System.out.println("No coins in the machine");
    }
    
    
    
    

}
