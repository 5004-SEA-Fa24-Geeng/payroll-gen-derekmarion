package main.java.student;

import student.ITimeCard;

public class TimeCard implements ITimeCard {

    private String employeeID;
    private double hoursWorked;

    public TimeCard(String employeeID, float hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeID() {
        return this.employeeID;
    }

    @Override
    public double getHoursWorked() {
        return this.hoursWorked;
    }
}
