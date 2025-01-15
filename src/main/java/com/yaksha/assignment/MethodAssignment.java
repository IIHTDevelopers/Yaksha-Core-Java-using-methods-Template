package com.yaksha.assignment;

public class MethodAssignment {

    public static void main(String[] args) {
        
        // Task 1: Calling a Method with No Arguments
        System.out.println("Task 1: Calling a Method with No Arguments");
        printMessage();

        // Task 2: Calling a Method with Arguments
        System.out.println("\nTask 2: Calling a Method with Arguments");
        int result = addNumbers(5, 10);
        System.out.println("Sum of 5 and 10: " + result);

        // Task 3: Calling a Method that Returns a Value
        System.out.println("\nTask 3: Calling a Method that Returns a Value");
        int max = findMax(15, 20);
        System.out.println("Maximum Value: " + max);

        // Task 4: Calling a Method with Variable Arguments
        System.out.println("\nTask 4: Calling a Method with Variable Arguments");
        int sum = calculateSum(1, 2, 3, 4, 5);
        System.out.println("Sum of numbers: " + sum);

        // Task 5: Calling a Method from Another Method
        System.out.println("\nTask 5: Calling a Method from Another Method");
        printMessageAndSum(3, 4);
    }

    // Task 1: A method that prints a message
    public static void printMessage() {
        System.out.println("Hello, welcome to method examples!");
    }

    // Task 2: A method that accepts two integers and returns their sum
    public static int addNumbers(int a, int b) {
        return a + b;
    }

    // Task 3: A method that finds the maximum of two numbers
    public static int findMax(int x, int y) {
        return (x > y) ? x : y;
    }

    // Task 4: A method that takes variable arguments and calculates the sum
    public static int calculateSum(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Task 5: A method that calls another method
    public static void printMessageAndSum(int a, int b) {
        printMessage();
        int sum = addNumbers(a, b);
        System.out.println("The sum of " + a + " and " + b + " is: " + sum);
    }
}
