import java.util.ArrayList;
import java.util.List;

public class ManagerNotifier implements Subject {
    
   	List<RestaurantEmployee> employees;
	public ManagerNotifier() {
		employees = new ArrayList<>();
	}
	@Override
	public void notifyEmployees() {
		for(RestaurantEmployee employee: employees) {
			employee.update();
		}
		
	}
	@Override
	public void addEmployee(RestaurantEmployee e) {
		employees.add(e);
		
	}
	@Override
	public void removeEmployee(RestaurantEmployee e) {
		employees.remove(e);
	}
	
}
