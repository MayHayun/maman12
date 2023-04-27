package q2;

import java.lang.*;
import java.util.ArrayList;
public class BigInt
{
    //a list to present all the digits of the number
    private ArrayList<Integer> number = new ArrayList<>();
    //var to represent the sign of the number
    private int charge;

//a constructor to init an instance of long number
    public BigInt(String s) {
        int j;
        if(!validate(s))
            throw new IllegalArgumentException();
        if(s.charAt(0) == '-')
            charge = -1;
        else
            charge = 1;
        for (int i = s.length() - 1; i > 0; i--) {
            j = s.charAt(i) - '0';
            number.add(j);
        }
    }
    //getters
    public ArrayList <Integer> getNumber(){
        return number;
    }
    public int getCharge(){
        return charge;
    }
    //a method to multiply two given long numbers
    public BigInt multiply(BigInt b){
        BigInt res = new BigInt("+0");
        BigInt temp = null;
        BigInt second = new BigInt();
        second.number = b.getNumber();
        int count = 0; //represent the digit location
        for(int i = 0; i < this.number.size(); i++) //multiply each digit by param
        {
            temp = second.multByDigit(this.number.get(i));
            temp.ShiftRight(count);
            res = res.plus(temp);
            count += 1;
        }
        if(b.getCharge() != charge)
            res.charge = -1;
        return res;
    }
    //a private constructor to create an empty instance of long number - used only in class
    private BigInt(){
        this.number = new ArrayList<Integer>();
        charge = 1;
    }
    //a method to multiply long given number by a single digit number
    private BigInt multByDigit(int dig){
        BigInt res = new BigInt("+0");
        res.charge = this.charge;
        while(dig > 0){
            res = res.plus(this);
            dig--;
        }
        return res;
    }
    //a method to multiply a number by 10 *count times
    private void ShiftRight(int count) {
        int j, interval, temp = this.number.size()-1; //interval is the amount of elements to shift
        for(int i = 0; i < count; i++)
            this.number.add(0);
        interval = this.number.size() - temp-1;
        for(j = this.number.size()-1; j >= interval ; j--) {
            this.number.set(j, this.number.get(temp));
            temp--;
        }
        for(; j >= 0; j--) //put 0 in all the shifted locations to mimic the multiplication by 10 im power of count
            this.number.set(j, 0);
    }
    //a method to add to long numbers
    public BigInt plus(BigInt b){
        int sum, remainder = 0;
        BigInt res = new BigInt();
        if(this.charge == b.getCharge()) {
            if(number.size() != b.getNumber().size()) {
                this.getToSameLength(b);
            }
            for (int i = 0; i < b.getNumber().size(); i++) {
                sum = number.get(i)+ b.getNumber().get(i)+ remainder;
                if(sum > 9) {
                    remainder = 1; //remember 1
                    sum -= 10;
                }
                else
                    remainder = 0;
                res.number.add(sum);
            }
            if(remainder == 1)
                res.number.add(1);
            if(charge == 1)
                res.charge = 1;
            else
                res.charge = -1;
        } else {
            if(charge == 1)
                res = this.minus(changeCharge(b.getNumber(), 1));
            else{
                res = changeCharge(this.number, 1).minus(b);
                res.charge = -1;
            }
        }
        return res;
    }
    // a method to compare between two long numbers - returns 0 if equals, 1 if the given object is bigger than b and -1 otherwise
    public int compareTo(BigInt b){
        if(this.equals(b))
            return 0;
        if(this.charge == 1 && b.getCharge() == 1){
            if(this.number.size() > b.getNumber().size())
                return 1;
            else if (this.number.size() == b.getNumber().size()){
                for(int i = number.size()-1; i >=0; i--)
                    if(this.number.get(i) > b.number.get(i))
                        return 1; //check which is bigger
                    else if (this.number.get(i) < b.number.get(i))
                        return -1;
                return 1;
            }
            return -1;
        }
        else if(charge == -1){
            if(b.charge == -1) {
                if (this.number.size() < b.getNumber().size())
                    return 1;
                else if (this.number.size() == b.getNumber().size())
                    for(int i = number.size()-1; i >=0; i--) //same method for other direction
                        if(this.number.get(i) > b.number.get(i))
                            return -1;
                        else if (this.number.get(i) < b.number.get(i))
                            return 1;
                return 1;
            }else
                return 1;
        }
        return 1;
    }
    //a method to change the sign of a number for calculation purposes
    private BigInt changeCharge(ArrayList<Integer> i, int c){
        BigInt b = new BigInt();
        b.number = i;
        b.charge = c;
        return b;
    }
    //a method to subtract a long number from given long number
    public BigInt minus(BigInt b) {
      int sub;
      BigInt res = new BigInt();
      if (b.getCharge() == -1)
          return this.plus(changeCharge(b.number, 1));
      if (charge == -1) {
          res = changeCharge(this.number, 1).plus(b);
          res.charge = -1;
      } else {
          if (this.compareTo(b)<=0) {
              res = b.minus(this);
              res.charge = (-1) * res.getCharge();
          } else {
              this.getToSameLength(b);
              for (int i = 0; i < b.getNumber().size(); i++) {
                  if (number.get(i) < 0) {
                      number.add(i + 1, number.get(i + 1) - 1);
                      number.add(i, number.get(i) + 10);
                  }
                  sub = number.get(i) - b.getNumber().get(i);
                  if (sub < 0) {
                      number.add(i + 1, number.get(i + 1) - 1);
                      sub = sub + 10; //lone 10
                  }
                  res.number.add(sub);
              }
          }
      }
      return res;
  }
  //a method to divide given long number by a long number
    public BigInt divide(BigInt b){
        BigInt res = new BigInt("+0");
        BigInt quotient = new BigInt("+0");
        BigInt first = new BigInt();
        BigInt second = new BigInt();
        first.number = this.number;
        second.number = b.number;
        BigInt one = new BigInt("+1");
        if(b.equals(res)) // check if dividing by 0 - if true - throw error
            throw new ArithmeticException("division by zero is not allowed\n");
        while(first.compareTo(res) == 1){
            res = res.plus(second);
            quotient = quotient.plus(one);
        }
        if(b.getCharge() != charge)
            quotient.charge = -1;
        return quotient;
    }
    //a method to make two long numbers contain the same amount of elements for calculation purposes
    private void getToSameLength(BigInt b){
        int maxLength = Math.max(this.number.size(), b.getNumber().size());
        BigInt res = new BigInt();
        if(b.getNumber().size() == maxLength)
            res = this;
        else
            res = b;
        while(res.getNumber().size() < maxLength)
        {
            res.getNumber().add(0);
        }
    }
    //a method to make the given number to a string representation
    public String toString() {
        String res = "";
        boolean notNeeded = true;
        if(charge == -1)
            res = res.concat("-");
        for(int i = this.number.size()-1; i >=0 ; i--)
        {
            if(!(this.number.get(i) == 0 && notNeeded)) {
                res = res.concat(String.valueOf(number.get(i)));
                notNeeded = false;
            }
        }
        return res;
    }
    //a method to check if a given input is a valid input
    private boolean validate(String s) {
        if (s == null || s.isEmpty())
            return false;
        if(s.charAt(0) != '-' && s.charAt(0) != '+')
            return false;
        for (int i = 1; i < s.length(); i++) {
            if (!isNum(s.charAt(i)))
                return false;
        }
        return true;
    }
    //a method to check if two long numbers are equal
    public boolean equals(Object o){
        BigInt b = null;
        if(o instanceof BigInt){ // make sure its the right object
            b = (BigInt) o;
            if(charge != b.getCharge()) //compare charges
                return false;
            if(this.number.size() != b.getNumber().size())
                return false;
            for(int i = 0; i < this.number.size(); i++){ //compare digits
                if(number.get(i) != b.getNumber().get(i))
                    return false;
            }
            return true;
        }
        return false;
    }
    //a method to check if a char is a digit
    private static boolean isNum(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }
}
