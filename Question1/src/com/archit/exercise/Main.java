package com.archit.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String csvFile = "E:\\project Spring\\Employee\\out\\production\\Employee\\com\\archit\\exercise\\Employees.csv";
        String line;
        String csvSplitBy = ",";

        // Map to store employees grouped by department
        Map<String, List<Employee>> departmentMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            // Read data from CSV file
            while ((line = br.readLine()) != null) {
                String[] employeeData = line.split(csvSplitBy);

                if (employeeData.length < 5) {
                    System.err.println("Invalid data format for employee: " + line);
                    continue; // Skip this line and proceed to the next one
                }

                String firstName = employeeData[0];
                String lastName = employeeData[1];
                String department = employeeData[2];
                String email = employeeData[3];
                double salary = 0.0; // Default value for salary if it's empty
                String salaryString = employeeData[4].trim();
                if (!salaryString.isEmpty()) {
                    if (salaryString.matches("[0-9]+(\\.[0-9]+)?")) { // Check if salaryString contains only digits or decimal point
                        salary = Double.parseDouble(salaryString);
                    } else {
                        System.err.println("Invalid salary format for employee: " + line);
                    }
                }

                // Create Employee object
                Employee employee = new Employee(firstName, lastName, department, email, salary);

                // Add employee to department map
                departmentMap.computeIfAbsent(department, k -> new ArrayList<>()).add(employee);
            }

            // Sort employees within each department by salary
            departmentMap.forEach((department, employees) -> {
                Collections.sort(employees, Comparator.comparingDouble(Employee::getSalary));
            });

            // Sort departments lexicographically
            List<String> sortedDepartments = new ArrayList<>(departmentMap.keySet());
            Collections.sort(sortedDepartments);

            // Print sorted employees
            for (String department : sortedDepartments) {
                System.out.println("Department: " + department);
                for (Employee employee : departmentMap.get(department)) {
                    System.out.println(employee);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private double salary;

    public Employee(String firstName, String lastName, String department, String email, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
