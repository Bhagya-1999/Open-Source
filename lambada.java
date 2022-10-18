package com.fresco;
import java.util.*;

import java.util.stream.Collectors;

public class LambdaFn {

    long countDigit(long n){

      if(n==0)

      return 0;

      return 1+countDigit(n/10);

    }

    boolean check(long n){

      long l=countDigit(n);

      long dup=n;

      long sum=0;

      while(dup>0){

        sum+=Math.pow(dup%10,l);

        dup/=10;

      }

      return(n==sum);

    }

    public List<Long> functionalProgramming(List<String> listOfIntegers)

    {

        //Write your code here

        List<Long> outputList = listOfIntegers.stream()

         .map(s->Long.parseLong(s))

         .filter(c->check(c))

         .collect(Collectors.toList());

        return outputList;
    }
}
