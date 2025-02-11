# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 
   1. Comma separated value 

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?
   1. At runtime, you don't know the type of employees that will be generated, so the only type-safe declaration is List<IEmployee>. In my specific implementation, I have an abstract class Employee that implements the IEmployee interface, so mine reads List<Employee>, which is still type-safe.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?
   1. This would constitute a has-a (composition) relationship.

4. Can you provide an example of a has-a relationship in your code (if one exists)?
   1. The only example of a composition relationship in my code is that PayrollGenerator has all other classes involved in the program (Paystub, Employee, TimeCard). I chose not to create composition relationships between these various objects (for example, I might have had and Employee attribute that contained a generated Paystub object, but this was not strictly necessary).

5. Can you provide an example of an is-a relationship in your code (if one exists)?
   1. HourlyEmployee and SalaryEmployee classes both extend the Employee abstract class and have an is-a (inheritance) relationship with it.


6. What is the difference between an interface and an abstract class?
   1. Interfaces enforce method signatures in inheriting classes but do not provide implementation details. A class can implement more than one interface.
   2. Abstract classes can optionally provide implementation details for subordinate classes to inherit. A class can only extend one abstract class.


7. What is the advantage of using an interface over an abstract class?
   1. An interface can provide more flexibility as it only enforces a method signature but not its implementation details. Additionally, a class can implement multiple interfaces, but only extend one abstract class.


8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 
   1. int is a primitive type, but you cannot declare Lists in Java without wrapper classes containing primitive types.
   2. The proper syntax would be `List<Integer> numbers = new ArrayList<Integer>();`


9. Which class/method is described as the "driver" for your application? 
   1.  PayrollGenerator is the application driver.



10. How do you create a temporary folder for JUnit Testing? 
    1.  The @TempDir annotation allows you to create and manipulate a temporary directory for testing that gets torn down after the test is complete.


## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

One article from Paycom [^1] defines pay equality and pay equity as separate but related concepts. Pay equality is the enforcement of equal pay for the same work without discrimination based on gender, age, or other personal characteristics. Pay equity is a broader category related to ensuring appropriate compensation across different job roles based on value added or contributions to the organization's mission. Because pay equality is closely linked with demographic data, a payroll system would need to accurately track employee demographics to ensure compliance. For example, each employee would need to have an associated sex/age/gender etc. to make the system auditable against applicable laws and regulations regarding pay discrimination. 

Based on these definitions, it seems much of the challenge in ensuring pay equity would also involve establishing a transparent and sensible pay scale based on value added to the business. The payroll system would have to track each employee's associated level in the payscale and when/if any level changes occur to provide maximum auditability. For example, if an employee received a promotion or changed job roles, the system would have to register that change and potentially automatically increase pre-tax compensation to ensure that discrimination does not occur, in addition to making that employee's pay more auditable.

Another article from Salary.com [^2] has inverse definitions for pay equality and pay equity. Regardless of the exact wording, it seems that the primary challenge is establishing fair and equitable compensation levels. While the payroll system can help enforce these by tracking demographic data of the employees, the system is only as good as the compensation rules that guide it. For example, if there was some inequity in the compensation scheme, the payroll system couldn't inherently address that other than by leaving an audit trail. I think that the only way the payroll system might proactively support equity is to automatically adjust compensation based on an equitable ruleset, thus removing human judgment (and by extension, bias) from compensation decisions by ensuring that compensation changes are applied automatically based on employee demographics.

[^1]: [Paycom - Pay Equity](https://www.paycom.com/resources/blog/pay-equity/)
[^2]: [Salary.com - Pay Equity vs Pay Equality: What is the Difference?](https://www.salary.com/blog/pay-equity-vs-pay-equality-what-is-the-difference/)