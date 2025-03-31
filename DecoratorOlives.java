public class DecoratorOlives extends PizzaDecorator {
    public DecoratorOlives(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Olives");
    }
    public String getDescription() {
        if(tempPizza.getDescription()=="Regular Pizza"){
            return tempPizza.getDescription() + " with Olives";
        }
        else
        return tempPizza.getDescription() + ", Olives";
    }
    public double getPrice() {
        return tempPizza.getPrice() + 0.60;
    }
    
}
