package student;

public class PayStub implements IPayStub {

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

    public double getNetPay() {
        return this.netPay;
    }

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
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                this.employeeName,
                this.getPay(),
                this.getTaxesPaid(),
                this.YTDEarnings,
                this.YTDTaxesPaid);
    }
}
