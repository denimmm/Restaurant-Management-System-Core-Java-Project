public class DecoratorBacon extends PizzaDecorator {
    public DecoratorBacon(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Bacon");
    }
    public String getDescription() {
        if(tempPizza.getDescription()=="Regular Pizza"){
            return tempPizza.getDescription() + " with Bacon";
        }
        else
        return tempPizza.getDescription() + ", Bacon";
    }
    public double getPrice() {
        return tempPizza.getPrice() + 2.00;
    }
    
}
