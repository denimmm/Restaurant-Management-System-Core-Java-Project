import java.util.ArrayList;
import java.util.List;

/**
 * Kazunori Hayashi
* Version 1.0 29/7/2013
 */

public class OrderDetail
{
    private int itemID;
    private String itemName;
    private double price;
    private byte quantity;
    private double totalPrice;

    /// @brief
    /// List of toppings selected for the item (used for Decorator design pattern).
    /// @author Alex Ratchev
    private List<String> toppings;

    /**
     * Constructor for objects of class OrderDetail
     */
    public OrderDetail(MenuItem newMenuItem, byte newQuantity)
    {
        this.itemID     = newMenuItem.getID();
        this.itemName   = newMenuItem.getName();
        this.price      = newMenuItem.getPrice();
        this.quantity   = newQuantity;
        this.totalPrice = this.price * this.quantity;

        //Intialize an empty list to store user-selected toppings
        this.toppings = new ArrayList<>();
    }
    
    /**************************************************
    * Getter
    *************************************************/
    public int getItemID()
    {
        return this.itemID;
    }
    public String getItemName()
    {
        return this.itemName;
    }
    public double getPrice()
    {
        return this.price;
    }
    public byte getQuantity()
    {
        return this.quantity;
    }
    public double getTotalPrice()
    {
        return this.totalPrice;
    }

    /// @brief Gets the list of selected toppings for the order item.
    /// @return List of topping names.
    /// @author Alex Ratchev
    public List<String> getToppings() {return this.toppings;}
    
    public void addQuantity(byte add)
    {
        quantity += add;
        totalPrice = price * quantity;
    }
    /// @brief Replaces the current toppings with a new list of selected toppings.
    /// @param newToppings A list of new toppings to set.
    /// @author Alex Ratchev
    public void setToppings(List<String> newToppings) {
        this.toppings = new ArrayList<>(newToppings); // copy for safety
    }
}
