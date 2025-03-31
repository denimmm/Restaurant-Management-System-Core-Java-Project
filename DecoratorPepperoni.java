public class DecoratorPepperoni extends PizzaDecorator {
    public DecoratorPepperoni(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Pepperoni");
    }
    public String getDescription() {
        if(tempPizza.getDescription()=="Regular Pizza"){
            return tempPizza.getDescription() + " with Pepperoni";
        }
        else
        return tempPizza.getDescription() + ", Pepperoni";
    }
    public double getPrice() {
        return tempPizza.getPrice() + 1.00;
    }
}

