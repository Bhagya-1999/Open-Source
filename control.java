import java.io.*;
import java.math.*;
import java.util.*;


class Result {

    /*
     * Complete the 'calculateGrade' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY students_marks as parameter.
     */

    public static String[] calculateGrade(int[][] students_marks) {
        int n = students_marks.length;
        String[] result = new String[n];
        for(int i=0;i<n;i++){
            float avg = 0;
            for (int j=0;j<5;j++)
                avg += students_marks[i][j];
                avg = avg/5;
                if(avg >= 90) result[i] = "A+";
                else if(avg >= 80) result[i] = "A";
                else if(avg >= 70) result[i] = "B";
                else if(avg >= 60) result[i] = "C";
                else if(avg >= 50) result[i] = "D";
                else result[i] = "F";
            }
            return result;
        }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int students_marksRows = Integer.parseInt(sc.next().trim());
		int[][] students_marks = new int[students_marksRows][students_marksColumns];
		for(int i = 0; i < students_marksRows; i++)
        {
			for(int j = 0; j < students_marksColumns; j++)
			{
				students_marks[i][j] = Integer.parseInt(sc.next().trim());				
			}
        }

        String[] result = Result.calculateGrade(students_marks);
		
		for(int i = 0; i < result.length; i++)
        {
			System.out.println(result[i]);
            bufferedWriter.write(result[i]+"\n");
        }       
        bufferedWriter.close();
    }
}