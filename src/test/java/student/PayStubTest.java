package test.java.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.student.PayStub;

public class PayStubTest {

    private PayStub payStub;

    @BeforeEach
    public void setUp() {
        payStub = new PayStub(20000, 5000);
    }

    @Test
    public void testGetPay() {
        assertEquals(20000, payStub.getPay());
    }

    @Test
    public void testGetTaxesPaid() {
        assertEquals(5000, payStub.getTaxesPaid());
    }

    @Test
    public void testToCSV() {
        assertEquals("20000,5000", payStub.toCSV());
    }
}
