package people;

public class Employee extends Person {

	private double daySalary;
	private static final int OVERTIME_AGE = 18;
	private static final double OVERTIME_RATE = 1.5;

	Employee(String name, int age, boolean isMale, double daySalary) {
		super(name, age, isMale);
		this.daySalary = daySalary;
	}

	void showEmployeeInfo() {
		super.showPersonInfo();
		System.out.print(", daySalary is: " + daySalary);
		System.out.println();
	}

	double calculateOvertime() {
		if (age < OVERTIME_AGE) {
			return 0;
		}
		return daySalary * OVERTIME_RATE;
	}
}
