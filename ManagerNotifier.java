import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
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
	// save message to the file 
	public void saveMessageToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.txt", true))) {
            writer.write(message);
            writer.newLine();
            writer.write("----");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
	}
	

