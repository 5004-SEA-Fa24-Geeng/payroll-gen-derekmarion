package student;

/**
 * Class representing a pay stub.
 */
public class PayStub extends CSVStringFormatter implements IPayStub {

    /**
     * The name of the employee.
     */
    private String employeeName;

    /**
     * The net pay of the employee.
     */
    private double netPay;

    /**
     * The taxes paid by the employee.
     */
    private double taxes;

    /**
     * The year-to-date earnings of the employee.
     */
    private double ytdEarnings;

    /**
     * The year-to-date taxes paid by the employee.
     */
    private double ytdTaxesPaid;

    /**
     * Constructs a PayStub object.
     *
     * @param employeeName the name of the employee
     * @param netPay       the net pay of the employee
     * @param taxes        the taxes paid by the employee
     * @param ytdEarnings  the year-to-date earnings of the employee
     * @param ytdTaxesPaid the year-to-date taxes paid by the employee
     */
    public PayStub(String employeeName, double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the net pay of the employee.
     *
     * @return the net pay of the employee
     */
    @Override
    public double getPay() {
        return this.netPay;
    }

    /**
     * Gets the taxes paid by the employee.
     *
     * @return the taxes paid by the employee
     */
    @Override
    public double getTaxesPaid() {
        return this.taxes;
    }

    /**
     * Gets the name of the employee.
     *
     * @return the name of the employee
     */
    public String getEmployeeName() {
        return this.employeeName;
    }

    /**
     * Gets the year-to-date earnings of the employee.
     *
     * @return the year-to-date earnings of the employee
     */
    public double getYtdEarnings() {
        return this.ytdEarnings;
    }

    /**
     * Gets the year-to-date taxes paid by the employee.
     *
     * @return the year-to-date taxes paid by the employee
     */
    public double getYtdTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    /**
     * Converts the pay stub object to a CSV string.
     *
     * @return the CSV string representation of the pay stub
     */
    @Override
    public String toCSV() {
        StringBuilder payStubString = new StringBuilder();
        payStubString.append(this.getEmployeeName()).append(",");
        payStubString.append(formatDouble(this.getPay())).append(",");
        payStubString.append(formatDouble(this.getTaxesPaid())).append(",");
        payStubString.append(formatDouble(this.getYtdEarnings())).append(",");
        payStubString.append(formatDouble(this.getYtdTaxesPaid()));
        return payStubString.toString();
    }
}
