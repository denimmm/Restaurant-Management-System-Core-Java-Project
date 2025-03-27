public class DecoratorMushroom extends PizzaDecorator {
    public DecoratorMushroom(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Mushroom");
    }
    public String getDescription() {
        if(tempPizza.getDescription()=="Regular Pizza"){
            return tempPizza.getDescription() + " with Mushrooms";
        }
        else
            return tempPizza.getDescription() + ", Mushrooms";
    }
    public double getPrice() {
        return tempPizza.getPrice() + 1.25;
    }
}
