import student.PayStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PayStubTest {

    private PayStub payStub;

    @BeforeEach
    public void setUp() {
        payStub = new PayStub("John Doe", 20000, 5000, 100000, 25000);
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
    public void testGetNetPay() {
        assertEquals(20000, payStub.getNetPay());
    }

    @Test
    public void testGetEmployeeName() {
        assertEquals("John Doe", payStub.getEmployeeName());
    }

    @Test
    public void testGetYTDEarnings() {
        assertEquals(100000, payStub.getYTDEarnings());
    }

    @Test
    public void testGetYTDTaxesPaid() {
        assertEquals(25000, payStub.getYTDTaxesPaid());
    }

    @Test
    public void testToCSV() {
        assertEquals("John Doe,20000.00,5000.00,100000.00,25000.00", payStub.toCSV());
    }
}
