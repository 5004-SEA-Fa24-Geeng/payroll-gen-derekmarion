/*
 * Students, build off this class. We are providing one sample test case as file reading is new to
 * you.
 * 
 * NOTE: you may end up changing this completely depending on how you setup your project.
 * 
 * we are just using .main() as we know that is an entry point that we specified.
 * 
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import student.PayrollGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPayrollGenerator {

    @TempDir
    static Path tempDir;


    @Test
    public void testFinalPayStub() throws IOException {
        // copy employees.csv into tempDir
        Path employees = tempDir.resolve("employees.csv");
        Files.copy(Paths.get("resources/employees.csv"), employees);

        // get the path of the paystubs.csv
        Path payStubs = tempDir.resolve("paystubs.csv");



        String[] args = {"-e", employees.toString(), "-t", "resources/time_cards.csv", // allowed,
                                                                                       // this isn't
                                                                                       // modified -
                                                                                       // so safe
                "-o", payStubs.toString()};

        // run main method
        PayrollGenerator.main(args);



        String expectedPayStubs = Files
                .readString(Paths.get("resources/original/pay_stubs_solution_to_original.csv"));

        String actualPayStubs = Files.readString(payStubs);

        assertEquals(expectedPayStubs, actualPayStubs);


        // you could also read lines and compared the lists


    }


}
