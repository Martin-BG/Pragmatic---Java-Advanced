package controller;

import model.Student;
import view.StudentView;

public class StudentController {

    private final Student model;
    private final StudentView view;

    public StudentController(final Student model, final StudentView view) {
        this.model = model;
        this.view = view;
    }

    public String getStudentName() {
        return this.model.getName();
    }

    public void setStudentName(String name) {
        this.model.setName(name);
    }

    public String getStudentRollNo() {
        return this.model.getRollNo();
    }

    public void setStudentRollNo(String rollNo) {
        this.model.setRollNo(rollNo);
    }

    public void updateView() {
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}
