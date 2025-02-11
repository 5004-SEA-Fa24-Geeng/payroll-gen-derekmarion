package student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeCardTest {

    @Test
    public void testGetEmployeeID() {
        TimeCard timeCard = new TimeCard("E123", 40.0);
        assertEquals("E123", timeCard.getEmployeeID());
    }

    @Test
    public void testGetHoursWorked() {
        TimeCard timeCard = new TimeCard("E123", 40.0);
        assertEquals(40.0, timeCard.getHoursWorked());
    }

    @Test
    public void testTimeCardWithNegativeHours() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard("E123", -40.0);
        });
    }

}