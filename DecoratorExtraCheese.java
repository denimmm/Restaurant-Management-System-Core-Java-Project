public class DecoratorExtraCheese extends PizzaDecorator {
    public DecoratorExtraCheese(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Extra Cheese");
    }
    public String getDescription() {
        if(tempPizza.getDescription()=="Regular Pizza"){
            return tempPizza.getDescription() + " with Extra Cheese";
        }
        else
        return tempPizza.getDescription() + ", Extra Cheese";
    }
    public double getPrice() {
        return tempPizza.getPrice() + 1.50;
    }
    
}
