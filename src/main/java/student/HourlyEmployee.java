package student;

public class HourlyEmployee extends Employee {

    private static double OVERTIME_RATE = 1.5;

    public HourlyEmployee(String name, String ID, double payRate, double pretaxDeductions,
            double YTDEarnings, double YTDTaxesPaid) {
        super(EmployeeType.HOURLY, name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        double regularPay = Math.min(hoursWorked, 40) * this.getPayRate();
        double overtimePay = Math.max(0, hoursWorked - 40) * this.getPayRate() * OVERTIME_RATE;
        return regularPay + overtimePay;
    }
}
