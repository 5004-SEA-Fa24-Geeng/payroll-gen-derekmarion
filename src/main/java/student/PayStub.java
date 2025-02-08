package student;

public class PayStub extends CSVStringFormatter implements IPayStub {

    private String employeeName;
    private double netPay;
    private double taxes;
    private double YTDEarnings;
    private double YTDTaxesPaid;

    public PayStub(String employeeName, double netPay, double taxes, double YTDEarnings, double YTDTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxes = taxes;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
    }

    @Override
    public double getPay() {
        return this.netPay;
    };

    @Override
    public double getTaxesPaid() {
        return this.taxes;
    };

    public String getEmployeeName() {
        return this.employeeName;
    }

    public double getYTDEarnings() {
        return this.YTDEarnings;
    }

    public double getYTDTaxesPaid() {
        return this.YTDTaxesPaid;
    }

    @Override
    public String toCSV() {
        StringBuilder payStubString = new StringBuilder();
        payStubString.append(this.getEmployeeName()).append(",");
        payStubString.append(formatDouble(this.getPay())).append(",");
        payStubString.append(formatDouble(this.getTaxesPaid())).append(",");
        payStubString.append(formatDouble(this.getYTDEarnings())).append(",");
        payStubString.append(formatDouble(this.getYTDTaxesPaid()));
        System.out.println("PayStub CSV: " + payStubString.toString());
        return payStubString.toString();
    }
}
