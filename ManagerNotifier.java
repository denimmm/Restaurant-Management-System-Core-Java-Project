import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class ManagerNotifier implements Subject {
    
   	List<Staff> employees;
	public ManagerNotifier(Database db) {
		employees = new ArrayList<>();
		employees = db.getStaffList();
	}
	@Override
	public void notifyEmployees() {
		for(Staff employee: employees) {
			employee.update();
		}
		
	}
	@Override
	public void addEmployee(Staff e) {
		// add it to arraylist of staff in database
		employees.add(e);
		
	}
	@Override
	public void removeEmployee(Staff e) {
		// remove it from arraylist of staff in database
		employees.remove(e);
	}
	
		
	}
	

