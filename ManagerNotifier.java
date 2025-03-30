import java.util.ArrayList;
import java.util.List;

public class ManagerNotifier implements Subject {
    
   	List<EmployeeObserver> employees;
	public ManagerNotifier() {
		employees = new ArrayList<>();
	}
	@Override
	public void notifyEmployees() {
		for(EmployeeObserver employee: employees) {
			employee.update();
		}
		
	}
	@Override
	public void addEmployee(EmployeeObserver e) {
		employees.add(e);
		
	}
	@Override
	public void removeEmployee(EmployeeObserver e) {
		employees.remove(e);
	}
	
}
