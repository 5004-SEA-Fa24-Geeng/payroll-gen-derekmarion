# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
---
title: Initial Design
---
classDiagram
    direction LR
    class Builder {
        -Builder()
        +static Employee buildEmployeeFromCSV(String csv)
        +static TimeCard buildTimeCardFromCSV(String csv)
    }
    Builder --> Employee : generates
    Builder --> TimeCard : generates

    class FileUtil {
        +static final String EMPLOYEE_HEADER
        +static final String PAY_STUB_HEADER
        -FileUtil()
        +static List<String> readFileToList(String file)
        +static void writeFile(String outfile, List<String> lines)
        +static void writeFile(String outfile, List<String> lines, boolean backup) 
    }

    class IEmployee {
        <<interface>>
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    IEmployee <|.. Employee
    class Employee {
        -String name
        -String ID
        -double payRate
        -String employeeType
        -double payRate
        -double YTDEarnings
        -double YTDTaxesPaid
        -double pretaxDeductions
        -double hoursWorked
        -TimeCard timeCard
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +PayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    Employee --> PayStub : generates
    Employee --> TimeCard: has

    class IPayStub {
        <<interface>>
        +double getPay()
        +double getTaxesPaid()
        +String toCSV
    }
    IPayStub <|.. PayStub
    class PayStub {
        -double pay
        -double taxesPaid
        +double getPay()
        +double getTaxesPaid()
        +String toCSV
    }

    class ITimeCard {
        <<interface>>
        +getEmployeeID()
        +getHoursWorked()
    }
    ITimeCard <|.. TimeCard
    class TimeCard {
        -String EmployeeID
        -int hoursWorked
        +String getEmployeeID()
        +int getHoursWorked()
    }

    class PayrollGenerator {
        -static final String DEFAULT_EMPLOYEE_FILE
        -static final String DEFAULT_PAYROLL_FILE
        -PayrollGenerator()
        +static void main(String[] args)
    }
    PayrollGenerator ..> FileUtil : uses
```



## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test all Builder class methods
2. Test all Employee class methods
3. Test all PayStub class methods
4. Test all TimeCard class methods 



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.


```mermaid
---
title: Post-Implementation Design
---
classDiagram
    direction LR
    class Builder {
        -Builder()
        +static Employee buildEmployeeFromCSV(String csv)
        +static TimeCard buildTimeCardFromCSV(String csv)
    }
    Builder --> Employee : generates
    Builder --> TimeCard : generates

    class FileUtil {
        +static final String EMPLOYEE_HEADER
        +static final String PAY_STUB_HEADER
        -FileUtil()
        +static List<String> readFileToList(String file)
        +static void writeFile(String outfile, List<String> lines)
        +static void writeFile(String outfile, List<String> lines, boolean backup) 
    }

    class IEmployee {
        <<interface>>
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +IPayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    IEmployee <|.. Employee
    class Employee {
        -String name
        -String ID
        -double payRate
        -EmployeeType employeeType
        -double YTDEarnings
        -double YTDTaxesPaid
        -double pretaxDeductions
        -double hoursWorked
        +String getName()
        +String getID()
        +double getPayRate()
        +String getEmployeeType()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +PayStub runPayroll(double hoursWorked)
        +String toCSV()
    }
    Employee --> PayStub : generates
    Employee ..> EmployeeType : uses

    class EmployeeType {
        <<enumeration>>
        HOURLY
        SALARY
    }
    
    class HourlyEmployee {
        +HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        +double calculateGrossPay(double hoursWorked)
    }
    Employee <|-- HourlyEmployee

    class SalaryEmployee {
        +SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        +double calculateGrossPay(double hoursWorked)
    }
    Employee <|-- SalaryEmployee
  
    class IPayStub {
        <<interface>>
        +double getPay()
        +double getTaxesPaid()
        +String toCSV
    }
    IPayStub <|.. PayStub
    class PayStub {
        -double pay
        -double taxesPaid
        +double getPay()
        +double getTaxesPaid()
        +String toCSV
    }

    class ITimeCard {
        <<interface>>
        +getEmployeeID()
        +getHoursWorked()
    }
    ITimeCard <|.. TimeCard
    class TimeCard {
        -String EmployeeID
        -int hoursWorked
        +String getEmployeeID()
        +int getHoursWorked()
    }

    class PayrollGenerator {
        -static final String DEFAULT_EMPLOYEE_FILE
        -static final String DEFAULT_PAYROLL_FILE
        -PayrollGenerator()
        +static void main(String[] args)
    }
    PayrollGenerator ..> FileUtil : uses
    PayrollGenerator ..> Builder : uses

    class CSVStringBuilder {
        <<abstract>>
        _+String formatDouble(double value)_
    }
    Employee <|-- CSVStringBuilder
```


## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
