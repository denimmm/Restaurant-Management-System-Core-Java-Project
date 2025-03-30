public interface Subject {
    void notifyEmployees();
	void addEmployee(EmployeeObserver e);
	void removeEmployee(EmployeeObserver e);
}
