public  abstract class PizzaDecorator implements Pizza {
    protected Pizza tempPizza;
    
    public PizzaDecorator(Pizza newPizza){
        this.tempPizza = newPizza;
    }  
}
