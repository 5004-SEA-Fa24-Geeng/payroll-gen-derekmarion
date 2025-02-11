package student;

/**
 * Class representing a time card.
 */
public class TimeCard implements ITimeCard {

    /**
     * The ID of the employee.
     */
    private String employeeID;

    /**
     * The hours worked by the employee.
     */
    private double hoursWorked;

    /**
     * Constructs a TimeCard object.
     *
     * @param employeeID  the ID of the employee
     * @param hoursWorked the hours worked by the employee
     */
    public TimeCard(String employeeID, double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative");
        }
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return the ID of the employee
     */
    @Override
    public String getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Gets the hours worked by the employee.
     *
     * @return the hours worked by the employee
     */
    @Override
    public double getHoursWorked() {
        return this.hoursWorked;
    }
}
