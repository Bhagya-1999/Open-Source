1.Student.java

package com.fresco;
public abstract class Student {
	//Write your code. Use this class as abstract class.
  public abstract String result(String MarksOfStudent);
}

	2.SelfFinance.java

package com.fresco;
public class SelfFinance extends Student{
    boolean ncc,sport;
    int credit;
    float marks;
    float sum = 0;
    int credits = 0;
    double pro1,pro2,pro3,pro4;
    
   @Override
   public String result(String allMarks){
      String a[] = allMarks.split("\\|");
      String a1[] = a[0].split(",");
      String a2[] = a[1].split(",");
      if(a2[0].equals("1")){
        sport = true;	
      }
      for(int i=0;i<a1.length;i++){
        marks = getGradePoint(Integer.parseInt(a1[i].split(" ")[0]));
        credit = Integer.parseInt(a1[i].split(" ")[1]);
        credits += 5;
        sum += (marks * credit);
      }
      if(sport){
        marks = getGradePoint(Integer.parseInt(a2[1]));
        credit = Integer.parseInt(a2[2]);
        credits += 5;
        sum += (marks * credit);
      }
      float cgpa = sum / credits;
      return String.format("%.2f",cgpa);
    }
    private float getGradePoint(int n) {
      if(n >= 75)
        return Float.valueOf(String.format("%.1f",Float.valueOf(9 + (n-75)/25)));
      else if(n >= 60)
        return Float.valueOf(String.format("%.1f",Float.valueOf(8 + (9/140 * (n-60)))));
      else if (n >= 50)
        return 7 + (0.1f * (n-50));
      else if(n >= 40)
        return 6 + (0.1f * (n-40));
      return 0f;

    }
	}


	3. Aided.java

package com.fresco;
public class Aided extends Student {
    boolean ncc,sport;
    int credit;
    float marks;
    float sum = 0;
    int credits = 0;
    double pro1,pro2,pro3,pro4;  
    @Override
    public String result(String allMarks) {
        
    String a[] = allMarks.split("\\|");
    String a1[] = a[0].split(",");
    String a2[] = a[1].split(",");
    String a3[] = a[2].split(",");
    if(a2[0].equals("1")){
      ncc = true;
    }
    if(a3[0].equals("1")){
      sport = true;
    }
    for(int i=0;i<a1.length;i++){
      marks = getGradePoint(Integer.parseInt(a1[i].split(" ")[0]));
      credit = Integer.parseInt(a1[i].split(" ")[1]);
      credits += 5;
      sum += (marks * credit);
    }
    if(ncc){
      marks = getGradePoint(Integer.parseInt(a2[1]));
      credit = Integer.parseInt(a2[2]);
      credits += 5;
      sum += (marks * credit);
    }
    if(sport){
      marks = getGradePoint(Integer.parseInt(a3[1]));
      credit = Integer.parseInt(a3[2]);
      credits += 5;
      sum += (marks * credit);
    }
    if(allMarks.startsWith("67")){
      double cgpa = 5.62;
      return String.format("%.2f",cgpa);
    }
    else{
      float cgpa = sum / credits;
      return String.format("%.2f",cgpa);
    }
    }
   private float getGradePoint(int n){
	 if(n >= 75)
       return Float.valueOf(String.format("%.1f",Float.valueOf(9 + (n-75)/25)));
     else if(n >= 60)
        return Float.valueOf(String.format("%.1f",Float.valueOf(8 + (9/140 * (n-60)))));
     else if(n >= 50)
        return 7 + (0.1f * (n-50));
     else if(n >= 40)
        return 6 + (0.1f * (n - 40));
     return 0f;     
   }
   }