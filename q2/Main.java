package q2;

import q2.BigInt;

import java.util.Scanner;
import java.lang.*;
public class Main {
    public static void main(String[] args) {
        String input;
        BigInt first = null, second = null;
        boolean okey = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter the first long number: ");
        while (!okey) {
            input = scan.nextLine();
            okey = true;
            try {
                first = new BigInt(input);
            } catch (IllegalArgumentException e)
            {
                System.out.println("this argument does not meet the requirements, please enter again: \n" + e.getMessage());
            }
        }
        okey = false;
        System.out.println("please enter the second long number: ");
        while (!okey) {
            input = scan.nextLine();
            try {
                second = new BigInt(input);
                okey = true;
            } catch (IllegalArgumentException e) {
                System.out.println("this argument does not meet the requirements, please enter again: \n" + e.getMessage());
            }
        }
        presentMenu();
        String operator = scan.nextLine();
        char op = operator.charAt(0);
        switch (op)
        {
            case '+':
                System.out.println(first.toString() + " + " + second.toString() + " = " + first.plus(second).toString());
                break;
            case '-':
                System.out.println(first.toString() + " - " + second.toString() + " = " + first.minus(second).toString());
                break;
            case '*':
                System.out.println(first.toString() + " * " + second.toString() + " = " + first.multiply(second).toString());
                break;
            case '/':
                BigInt res = null;
                try {
                    res = first.divide(second);
                } catch (ArithmeticException e){
                    System.out.println("division by zero is not allowed ->" + e.getMessage());
                }
                System.out.println(first.toString() + " / " + second.toString() + " = " + first.divide(second).toString());
                break;
            case '=':
                int result = first.compareTo(second);
                if(result < 0)
                    System.out.print(first.toString() + " < " + second.toString());
                else if(result > 0)
                    System.out.print(first.toString() + " > " + second.toString());
                else
                    System.out.print(first.toString() + " = " + second.toString());
                break;
        }
    }

    private static void presentMenu(){
        System.out.println("this program supports the following operations - addition(press +)," +
                " Subtraction(press -), multiplication(press *) and division(press /).\n" +
                "you may also use comparison (press =)");

    }
}