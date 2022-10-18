1.StudentJava.class


package com.fresco;

public class StudentClass {
    public String getQuery(String studentData,String query){
        int type;
        String res="";
        type = Integer.parseInt(query.split(",")[0]);
        String a[] = studentData.split(" ");
        if(type == 1){
            StudentList<String> list = new StudentList<>();
            for (int i=0;i<a.length;i++){
                list.addElement(a[i]);
            }
            String query_data = query.split(",")[1];
            res = list.beginsWith(query_data);
        } else if(type == 2){
            StudentList<String> list = new StudentList<String>();
            for(int i=0;i<a.length;i++){
                list.addElement(a[i]);
            }
            String query_data = query.split(",")[2];
            String blood_data = query.split(",")[1];
            res = list.bloodGroupOf(blood_data,query_data);
        } else if(type == 3){
            StudentList<Integer> list = new StudentList<>();
            for(int i=0;i<a.length;i++){
                list.addElement(Integer.parseInt(a[i]));
            }
            int query_data = Integer.parseInt(query.split(",")[1]);
            res = list.thresholdScore(query_data);
        } else if(query.equals("4")){
            ScoreList<Double> list = new ScoreList<>();
            for(int i=0;i<a.length;i++){
                list.addElement(Double.parseDouble(a[i]));
            }
            res = list.averageValues(a.length);
        }
        else if(query.equals("5")){
            ScoreList<Double> list = new ScoreList<>();
            for(int i=0;i<a.length;i++){
                list.addElement(Double.parseDouble(a[i]));
            }
            res = list.averageValues(a.length);
        }
        return res;
    }

}

	2. StudentList.java


package com.fresco;


import java.util.ArrayList;


public class StudentList<T> {
    ArrayList<T> students = new ArrayList<T>();
    public void addElement(T t){
        students.add(t);
    }
    public void removeElement(T t){
        students.remove(t);
    }
    public void getElement(int i){
        students.get(i);
    }
    public String beginsWith(String query){
        String res = "";
        for(T student:students){
            if(student instanceof String){
                if((student.toString().toLowerCase()).startsWith(query.toLowerCase())) {
                        res = res + student.toString()+"\n";
                    }
                }
            }
        return res;
        }
        public String bloodGroupOf(String blood_data,String query) {
            String res = "";
            String blood_arr[] = blood_data.split(" ");
            for(int i=0;i<blood_arr.length;i++) {
                if(blood_arr[i].equals(query)) {
                    res = res.concat(students.get(i).toString().concat("\n"));
                }
            }
            return res;
        }
        public String thresholdScore(int marks) {
            int count = 0;
            for(T student: students) {
                if((Integer) student >= marks) {
                    count++;
                }
            }
            return ""+count+" students scored "+marks+" above";
        }
    }


	3. ScoreList.java


package com.fresco;

import java.util.ArrayList;

public class ScoreList<T> {
    ArrayList<T> scores = new ArrayList<T>();
    public void addElement(T t){
        scores.add(t);
    }
    public void removeElement(T t){
        scores.remove(t);
    }
    public void getElement(int i){
        scores.get(i);
    }
    public String averageValues(int size){
        double sum=0.0;
        double avg = 0.0;
        for(T scor:scores){
            sum += (Double) scor;
        }
        avg = (double) sum/size;
        return String.format("%.2f",avg);
    }
}