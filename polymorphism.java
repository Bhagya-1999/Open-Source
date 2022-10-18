import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Parent{
    public int startElement;
    public int endElement;
    public String filter(){
        return null;
    }
}
    class ChildOne extends Parent {
        private Boolean isPrime(int n){
            if(n==1) return false;
            for(int i=2;i<= Math.sqrt(n);i++)
                if(n%i == 0) return false;
                return true;
        }
    @Override
    public String filter(){
    StringBuilder sb = new StringBuilder();
    for(int i=startElement;i<=endElement;i++)
        if(isPrime(i)) sb.append(i+" ");
        return sb.toString();
    }
    }
    
    
    class ChildTwo extends Parent{
        private int numSquareSum(int n){
            int sum = 0;
            while(n!=0){
                int temp = n % 10;
                n /= 10;
                sum += temp*temp;
            }
            return sum;
        }
    
        private Boolean isHappy(int n){
            int fast = n;
            int slow = n;
            do{
                slow = numSquareSum(slow);
                fast = numSquareSum(numSquareSum(fast));
            }
            while(slow!=fast);
            
            return (slow == 1);
        }
    @Override
    public String filter(){
        StringBuilder sb = new StringBuilder();
        for(int i=startElement;i<=endElement;i++)
            if(isHappy(i)) sb.append(i+" ");
        return sb.toString();
    }
}


public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());
        ChildOne ch1 = new ChildOne();
        ChildTwo ch2 = new ChildTwo();
        ch1.startElement =  start;
        ch1.endElement = end;
        ch2.startElement = start;
        ch2.endElement = end;
        System.out.println(ch1.filter());
        System.out.print(ch2.filter());
    }
}
