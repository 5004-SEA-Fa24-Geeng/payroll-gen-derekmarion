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
        // Temporary implementation
        return "";
    };
}
