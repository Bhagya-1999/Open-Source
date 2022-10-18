package com.fresco;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Passanger
{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
    float fare;
    public Passanger(int id, float fare)
    {
        this.id=id;
        this.fare=fare;
    }
}


public class BusProb {
    public String output(int capacity, int stops, List<String> listOfInputStrings, String query) {
        String outstring = "";
        ArrayList<Passanger> passangers = new ArrayList<Passanger>();
        List<String> passList = new ArrayList<String>();
        if (query.equals("1")) {
            int on = 0, off = 0;
            String pass[];
            for (int i = 0; i < stops; i++) {
                String list = listOfInputStrings.get(i);
                pass = list.split(" ");
                for (int j = 0; j < pass.length; j++) {
                    if (pass[j].contains("+")) on++;
                    else if (pass[j].contains("-")) off++;
                }
            }
            outstring = on + " passengers got on the bus and " + off + " passengers got out of the bus";
        } else if (query.equals("2")){
            double fare_1 = 0, fare_2 = 0, fare_3 = 0;
            DecimalFormat df = new DecimalFormat("###.#");
            fare_1 = capacity + (capacity * 0.6);
            fare_2 = capacity + (capacity * 0.3);
            fare_3 = capacity;
            int fare_1_pass = 0,fare_2_pass = 0,fare_3_pass = 0;
            int total_pass = 0, in = 0, out = 0;
            String pass[];
            for(int i=0;i<stops;i++) {
                String list = listOfInputStrings.get(i);
                pass = list.split(" ");
                in = 0;
                out = 0;
                for (int j = 0; j < pass.length; j++) {
                    if (pass[j].contains("+"))
                        in++;
                    else if (pass[j].contains("-")) out++;
                }
                total_pass = total_pass + in - out;
                if (total_pass <= Math.ceil(((double) capacity / 4))) {
                    fare_1_pass += in;
                } else if (total_pass > Math.ceil(((double) capacity / 4)) && total_pass <= Math.ceil(((double) capacity / 2))) {
                    fare_2_pass += in;
                } else if (total_pass > Math.ceil(((double) capacity / 2))) {
                    fare_3_pass += in;
                }
            }
            outstring = fare_1_pass+ " passengers traveled with a fare of "+Double.valueOf(df.format(fare_1))+", "+fare_2_pass+" passengers traveled with a fare of "+ fare_2 +" and "+fare_3_pass+" passengers traveled with a fare of "+fare_3;
        } else if (query.split(",")[0].equals("3")){
            int q_pass_id = Integer.parseInt(query.split(",")[1].trim());
            System.out.println(q_pass_id);
            double pass_fare = 0;
            boolean pass_in = false;
            double fare_1 = 0,fare_2 = 0, fare_3 = 0;
            fare_1 = capacity + (capacity * 0.6);
            fare_2 = capacity + (capacity * 0.3);
            fare_3 = capacity;
            System.out.println(fare_1+" "+fare_2+" "+fare_3);
            int total_pass = 0, in = 0, out = 0;
            String pass[];
            int pass_id,sum = 0;
            for(int i=0;i<stops;i++) {
                String list = listOfInputStrings.get(i);
                pass = list.split(" ");
                in = 0;
                out = 0;
                for (int j = 0; j < pass.length; j++) {
                    if (pass[j].contains("+" + q_pass_id))
                        pass_in = true;
                    else if (pass[j].contains("-"+q_pass_id)) pass_in = false;
                    if (pass[j].contains("+"))
                        in++;
                    else if (pass[j].contains("-")) out++;
                }
                total_pass = total_pass + in - out;
                System.out.println("STOP :" + (i + 1) + " " + total_pass);
                if (total_pass <= Math.ceil(((double) capacity / 4))) {
                    if (pass_in) {
                        pass_fare += fare_1;
                        pass_in = false;
                        System.out.println("fare_1");
                    }
                } else if (total_pass > Math.ceil(((double) capacity / 4)) && total_pass <= Math.ceil(((double) capacity / 2))) {
                    if (pass_in) {
                        pass_fare += fare_2;
                        pass_in = false;
                        System.out.println("fare_2");
                    }
                }
                else if (total_pass > Math.ceil(((double) capacity / 2))) {
                    if (pass_in) {
                        pass_fare += fare_3;
                        pass_in = false;
                        System.out.println("fare_3");
                    }
                }
            }
            outstring = "Passenger "+q_pass_id+" spent a total fare of "+pass_fare;
        } else if(query.split(",")[0].equals("4")){
            ArrayList<Count> count = new ArrayList<Count>();
            int q_pass_id = Integer.parseInt(query.split(",")[1].trim());
            passangers.clear();
            String pass[];
            int pass_id,sum = 0;
            for(int i=0;i<stops;i++){
                String list =listOfInputStrings.get(i);
                pass = list.split(" ");
                for(int j=0;j<pass.length;j++){
                    if(pass[j].contains("+")){
                        count.add(new Count(Integer.parseInt(pass[j].substring(1))));
                    }
                }
            }
            for(int i=0;i<count.size();i++){
                if(count.get(i).getId() == q_pass_id){
                    sum++;
                }
            }
            outstring = "Passenger "+q_pass_id+ " has got on the bus for "+sum+ " times";
        }
        else  if (query.split(",")[0].equals("5")){
            int q_pass_id = Integer.parseInt(query.split(",")[1].trim());
            System.out.println(q_pass_id);
            String pass[];
            int pass_id,sum = 0;
            for(int i=0;i<stops;i++){
                String list = listOfInputStrings.get(i);
                pass = list.split(" ");
                for (int j=0;j<pass.length;j++){
                    if(pass[j].contains(String.valueOf(q_pass_id))){
                        sum++;
                    }
                }
            }
            System.out.println(sum);
            if(sum%2 == 0){
                outstring = "Passenger "+ q_pass_id+" was not inside the bus at the end of the trip";
            }
            else {
                outstring = "Passenger "+q_pass_id+" was inside the bus at the end of the trip";
            }
        }
        return outstring;
    }
}


package com.fresco;
public class Count {
    int id;

    public Count() {
    }

    public Count(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}