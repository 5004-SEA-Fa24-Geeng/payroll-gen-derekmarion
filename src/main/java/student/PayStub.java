package main.java.student;

import student.IPayStub;

public class PayStub implements IPayStub {

    private double pay;
    private double taxesPaid;

    public PayStub(double pay, double taxesPaid) {
        this.pay = pay;
        this.taxesPaid = taxesPaid;
    }

    @Override
    public double getPay() {
        return this.pay;
    };

    @Override
    public double getTaxesPaid() {
        return this.taxesPaid;
    };

    @Override
    public String toCSV() {
        StringBuilder payStubString = new StringBuilder();
        payStubString.append(String.format("%.2f", this.getPay())).append(",");
        payStubString.append(String.format("%.2f", this.getTaxesPaid()));
        return payStubString.toString();
    };
}
