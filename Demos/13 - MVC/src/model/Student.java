package model;

public class Student {

    private String rollNo;
    private String name;

    public Student() {
    }

    public Student(final String rollNo, final String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public String getRollNo() {
        return this.rollNo;
    }

    public void setRollNo(final String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
