package main.java.student;

import student.IPayStub;

public class PayStub implements IPayStub {

    private double pay;
    private double taxesPaid;

    PayStub(double pay, double taxesPaid) {
        this.pay = pay;
        this.taxesPaid = taxesPaid;
    }

    @Override
    public double getPay() {
        // Temporary implementation
        return 0.0;
    };

    @Override
    public double getTaxesPaid() {
        // Temporary implementation
        return 0.0;
    };

    @Override
    public String toCSV() {
        // Temporary implementation
        return "";
    };
}
