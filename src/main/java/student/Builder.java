package student;

/**
 * This is a static class (essentially functions) that will help you build
 * objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders
 * are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {

    private Builder() {
    }

    /**
     * Builds an employee object from a CSV string.
     * 
     * You may end up checking the type of employee (hourly or salary) by looking at
     * the first
     * element of the CSV string. Then building an object specific to that type.
     * 
     * @param csv the CSV string
     * @return the employee object
     */
    public static Employee buildEmployeeFromCSV(String csv) {

        String[] values = csv.split(",");
        EmployeeType employeeType = EmployeeType.valueOf(values[0]);
        String name = values[1];
        String ID = values[2];
        double payRate = Double.parseDouble(values[3]);
        double pretaxDeductions = Double.parseDouble(values[4]);
        double YTDEarnings = Double.parseDouble(values[5]);
        double YTDTaxesPaid = Double.parseDouble(values[6]);

        return new Employee(employeeType, name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);

    }

    /**
     * Converts a TimeCard from a CSV String.
     * 
     * @param csv csv string
     * @return a TimeCard object
     */
    public static TimeCard buildTimeCardFromCSV(String csv) {

        String[] values = csv.split(",");
        String employeeID = values[0];
        double hoursWorked = Double.parseDouble(values[1]);

        return new TimeCard(employeeID, hoursWorked);
    }
}
