package people;

public class Student extends Person {

	private double score;

	Student(String name, int age, boolean isMale, double score) {
		super(name, age, isMale);
		this.score = score;
	}

	void showStudentInfo() {
		super.showPersonInfo();
		System.out.print(",score is: " + score);
		System.out.println();
	}

}
