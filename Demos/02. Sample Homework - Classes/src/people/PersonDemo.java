package people;

public class PersonDemo {

	public static void main(String[] args) {
		Person[] people = new Person[10];

		Person petko = new Person("Petko", 25, true);
		Person toni = new Person("Toni", 47, true);

		Student ganka = new Student("Ganka", 15, false, 4);
		Student cveti = new Student("Cveti", 28, false, 6);

		Employee ivan = new Employee("Ivan", 26, true, 300);
		Employee tanya = new Employee("Tanya", 24, false, 200);

		people[0] = petko;
		people[1] = toni;
		people[2] = ganka;
		people[3] = cveti;
		people[4] = ivan;
		people[5] = tanya;

		showPeopleInfo(people);
		showEmployeesOvertime(people);
	}

	private static void showPeopleInfo(Person[] people) {
		for (Person person : people) {
			if (person instanceof Employee) {
				((Employee) person).showEmployeeInfo();
			} else if (person instanceof Student) {
				((Student) person).showStudentInfo();
			} else if (person instanceof Person) {
				person.showPersonInfo();
				System.out.println();
			}
		}
	}

	private static void showEmployeesOvertime(Person[] people) {
		for (Person person : people) {
			if (person instanceof Employee) {
				System.out.println(((Employee) person).calculateOvertime());
			}
		}
	}

}
