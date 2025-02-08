package student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

/**
 * Main driver for the PayrollGenerator program.
 * 
 * Students, you are free to modify this file as needed, but you need to leave
 * in the parts where we
 * can pass in the employee and payroll files as arguments.
 * 
 * Grading wise, we will both be using unit tests, and running your program with
 * different employee
 * files. We also will create a separate output file for each.
 * 
 * 
 * To run the program, you can use the following command:
 * 
 * java student.PayrollGenerator -e employees_mine.csv -t time_cards.csv -o
 * pay_stubs_mine.csv or
 * java student.PayrollGenerator The above defaults listed below.
 * 
 * We also suggest meeting with a TA and learning how to add command line
 * arguments
 * in your IDE, as it will make testing and debugging easier.
 **/
public final class PayrollGenerator {
    /** default file name for employees. */
    private static final String DEFAULT_EMPLOYEE_FILE = "resources/employees.csv";
    /** default file name for pay stub output. */
    private static final String DEFAULT_PAYROLL_FILE = "resources/pay_stubs.csv";
    /** default time card file name. */
    private static final String DEFAULT_TIME_CARD_FILE = "resources/time_cards.csv";
    /** output for updated employee file */
    private static final String EMPLOYEE_OUT_FILE = "resources/employees_out.csv";

    /**
     * private constructor to prevent instantiation.
     */
    private PayrollGenerator() {

    }

    /**
     * Main driver for the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Arguments arguments = Arguments.process(args); // leave this, and make sure you use it on
                                                       // reading/writing files!

        // you are free to modify this code, or use it as a basis for your code
        // depends on how you want to implement the program

        List<String> employeeLines = FileUtil.readFileToList(arguments.getEmployeeFile());
        List<String> timeCards = FileUtil.readFileToList(arguments.getTimeCards());

        List<IEmployee> employees = employeeLines.stream().map(Builder::buildEmployeeFromCSV)
                .collect(Collectors.toList());

        List<ITimeCard> timeCardList = timeCards.stream().map(Builder::buildTimeCardFromCSV)
                .collect(Collectors.toList());

        List<IPayStub> payStubs = new LinkedList<>();

        // now we suggest looping through the timeCardList and for each timecard, find
        // the matching employee and generate a new paystub object. Then add that
        // paystub
        // to the payStubs list. - remember, you can use the employee ID to match the
        // employee
        // to the time card. Also remember if the value is negative, you just skip that
        // payStub
        // as it is invalid, but if is 0, you still generate a paystub, but the amount
        // is 0.

        for (ITimeCard timeCard : timeCardList) {
            // Find employee that corresponds to timecard
            IEmployee matchingEmployee = employees.stream()
                    .filter(employee -> employee.getID().equals(timeCard.getEmployeeID()))
                    .findFirst()
                    .orElse(null);

            if (matchingEmployee == null) {
                System.out.println("No matching employee found for TimeCard: " + timeCard.getEmployeeID());
                continue;
            }

            // Generate a new pay stub object
            IPayStub payStub = matchingEmployee.runPayroll(timeCard.getHoursWorked());

            // Add paystub if valid
            if (payStub != null) {
                System.out.println("Generated PayStub: " + payStub.toCSV());
                payStubs.add(payStub);
            } else {
                System.out.println("Invalid PayStub for Employee: " + matchingEmployee.getID());
            }
        }

        // now save out employees to a new file
        employeeLines = employees.stream().map(IEmployee::toCSV).collect(Collectors.toList());
        employeeLines.add(0, FileUtil.EMPLOYEE_HEADER);
        FileUtil.writeFile(EMPLOYEE_OUT_FILE, employeeLines);

        // now save out the pay stubs
        List<String> payStubLines = payStubs.stream().filter(x -> x != null).map(IPayStub::toCSV)
                .collect(Collectors.toList());
        payStubLines.add(0, FileUtil.PAY_STUB_HEADER);
        FileUtil.writeFile(arguments.getPayrollFile(), payStubLines);

    }

    /**
     * This is an internal class. Please leave it as is/do not modify! This design
     * is common for
     * processing arguments if you want to make sure it is unique to the driver.
     */
    private static final class Arguments {
        /** sets the employeeFile argument. */
        private String employeeFile = DEFAULT_EMPLOYEE_FILE;

        /** sets the payrollFile argument. */
        private String payrollFile = DEFAULT_PAYROLL_FILE;

        /** sets the timeCards argument. */
        private String timeCards = DEFAULT_TIME_CARD_FILE;

        /**
         * Constructor for Arguments. Setup as private, so builder has to be used.
         * 
         * @see #process(String[])
         */
        private Arguments() {

        }

        /**
         * Gets the employee file.
         * 
         * @return the name of the employee file
         */
        public String getEmployeeFile() {
            return employeeFile;
        }

        /**
         * Gets the payroll file.
         * 
         * @return the name of the payroll file
         */
        public String getPayrollFile() {
            return payrollFile;
        }

        /**
         * Gets the time card file.
         * 
         * @return the name of the time card file
         */
        public String getTimeCards() {
            return timeCards;
        }

        /**
         * Prints the help message.
         */
        public void printHelp() {
            System.out.println(
                    "Usage: java student.PayrollGenerator [-e employee_file] [-t time_cards_file] [-o payroll_file]");
            System.out.println("Options:");
            System.out.println(
                    "  -e employee_file  Input file containing employee information. Default is employees.csv");
            System.out.println(
                    "  -t time_cards_file  Input file containing time card information. Default is time_cards.csv");
            System.out.println(
                    "  -o payroll_file   Output file containing payroll information. Default is pay_stubs.csv");
            System.out.println("  -h                Print this help message");
        }

        /**
         * Processes the arguments.
         * 
         * @param args the arguments
         * @return an Argument object with file names added
         */
        public static Arguments process(String[] args) {
            Arguments arguments = new Arguments();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-e")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.employeeFile = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -i option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-t")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.timeCards = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -t option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-o")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.payrollFile = args[i + 1];
                    } else {
                        System.out.println("Missing argument for -o option");
                        arguments.printHelp();
                        System.exit(1);
                    }
                } else if (args[i].equals("-h")) {
                    arguments.printHelp();
                    System.exit(0);
                } else if (args[i].startsWith("-")) {
                    System.out.println("Unknown option: " + args[i]);
                    arguments.printHelp();
                    System.exit(1);
                }
            }
            return arguments;
        }
    }

}
