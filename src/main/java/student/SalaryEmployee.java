package student;

public class SalaryEmployee extends Employee {

    private static double SALARIED_MONTHLY_PAYMENTS = 24.0;

    public SalaryEmployee(String name, String ID, double payRate, double pretaxDeductions,
            double YTDEarnings, double YTDTaxesPaid) {
        super(EmployeeType.SALARY, name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return this.getPayRate() / SALARIED_MONTHLY_PAYMENTS;
    }
}
