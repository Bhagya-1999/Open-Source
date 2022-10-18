import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class CipherDecipher{
    
    private String swapCase(String str){
         char[] ch = str.toCharArray();
         for(int i=0;i<str.length();i++){
             if(ch[i]<= 90 && ch[i] >= 65)
                ch[i] += 32;
            else if(ch[i] <=122 && ch[i] >= 97)
                ch[i] -= 32;
         }
         return String.valueOf(ch);
     }
     private String replaceWithAscii(String str){
         StringBuilder sb = new StringBuilder();
         int n = str.length();
         for(int i=0;i<n;i++){
             if(i%2==1)
                sb.append((int)str.charAt(i));
             else 
                sb.append(str.charAt(i));
         }
            return sb.toString();
         
     }
     private String replaceWithChar(String str){
         StringBuilder sb = new StringBuilder();
         int n = str.length();
         for(int i=0;i<n-1;i++){
             char c = str.charAt(i);
             if(Character.isDigit(c)){
                 int ch = c - '0';
                 while(i<n-2 && Character.isDigit(str.charAt(++i))){
                     ch *= 10;
                     ch += str.charAt(i) - '0';
                 }
                 c = (char)ch;
                 sb.append(c);
                 if(!Character.isDigit(str.charAt(i)))
                 sb.append(str.charAt(i));
                 continue;
             }
             sb.append(c);
         }
         return sb.toString();
     }
    
    
    public String ciphering(String normal){
        normal = swapCase(normal);
        normal = (new StringBuilder(normal)).reverse().toString();
        normal = normal.replace(" ", "*");
        normal = replaceWithAscii(normal);
        normal = normal + "3";
        return normal;
    }
    public String deciphering(String ciphered){
       ciphered = replaceWithChar(ciphered);
       ciphered = ciphered.replaceAll("\\*"," ");
       ciphered = (new StringBuilder(ciphered)).reverse().toString();
       ciphered = swapCase(ciphered);
       return ciphered;
    }
}

public class Solution {
    
    public static void main(String[] args){
        Scanner readInput = new Scanner(System.in);
        String normal=readInput.nextLine();
        String ciphered=readInput.nextLine();
        
        CipherDecipher cipherOrDecipher = new CipherDecipher();
        System.out.println(cipherOrDecipher.ciphering(normal));
        System.out.println(cipherOrDecipher.deciphering(ciphered));
        
        

    }
    
}