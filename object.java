import java.io.*;
import java.util.*;
class Register {
    
    private static Register register = new Register();
    /*
     * Complete the 'getTotalBill' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts MAP itemDetails as a parameter.
     */
     public Register(){
         
     }
     public static Register getInstance(){
         return register;
     }

    public Double getTotalBill(Map<String,Integer> itemDetails) {

        // Write your code here
        Map<String,Double> map = new HashMap<>();
        map.put("apple",2.0);
        map.put("orange",1.5);
        map.put("mango",1.2);
        map.put("grape",1.0);
        double sum  = 0.0;
        for(Map.Entry<String,Integer> entry: itemDetails.entrySet()){
            Double d = map.get(entry.getKey());
            if( d != null){
                sum += entry.getValue() * d;
            }
        }
    return sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        
        Scanner readInput = new Scanner(System.in);        
        String[] input=readInput.nextLine().split(" ");                
        Map<String,Integer> myItems=new HashMap<String,Integer>();
        for(int i=0;i<input.length;i+=2){
          myItems.put(input[i],Integer.parseInt(input[i+1]));   
        }
        Register regObj = Register.getInstance();        
        System.out.println(regObj.getTotalBill(myItems));
        readInput.close();
        
    }
}