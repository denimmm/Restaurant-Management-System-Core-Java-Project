public class RestaurantEmployee implements EmployeeObserver {
    String Name;
	
	public RestaurantEmployee(Employee e) {
		this.Name = e.getFirstName() + " " + e.getLastName();
	}
	@Override
	public void update() {

		
		
	}

	@Override
	public void update(String message) {

		System.out.println("Hello "+ this.Name +",");
		
		System.out.println(message);
		
	}

}
