package people;

public class Person {

	private String name;
	int age;
	private boolean isMale;

	Person(String name, int age, boolean isMale) {
		this.name = name;
		this.age = age;
		this.isMale = isMale;
	}

	void showPersonInfo() {
		System.out.print("Name is: " + name + ", age is: " + age + ", is male: " + isMale);
	}

}
