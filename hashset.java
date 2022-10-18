package com.fresco;
import java.util.HashSet;

public class Hashset {
	 public static String getOut(int numberOfMatches, String squads, int squad1, int squad2)
	    {
	        String result = "";
	        String[] matchSet = squads.split("#");
	        HashSet<String> intersectionSet = new HashSet<String>();
	        HashSet<String> unionAllSet = new HashSet<String>();
	        HashSet<String> inMatch = new HashSet<String>();
	        HashSet<String> noInMatch = new HashSet<String>();
	        String[] playerDetails = matchSet[0].split(" ");
	        for(String playerName : playerDetails){
	          intersectionSet.add(playerName);
	          unionAllSet.add(playerName);
	        }
	        if(squad1 == 1){
	          noInMatch.addAll(intersectionSet);
	        }
	        if(squad2 == 1){
	          inMatch.addAll(intersectionSet);
	        }
	        for(int i=1;i<matchSet.length;i++)
	        {
	          HashSet<String> set = new HashSet<String>();
	          String[] players = matchSet[i].split(" ");
	          for(String playerName : players)
	          {
	            set.add(playerName);
	          }
	          intersectionSet.retainAll(set);
	          unionAllSet.addAll(set);
	          if( i == squad1 - 1)
	              noInMatch.addAll(set);
	          if(i == squad2-1)
	              inMatch.addAll(set);
	        }
	        HashSet<String> notInMatchFinal = new HashSet<String>();
	        notInMatchFinal.addAll(unionAllSet);
	        notInMatchFinal.removeAll(noInMatch);
	        inMatch.retainAll(notInMatchFinal);

	        return String.join(" ", intersectionSet)+", "+String.join(" ",inMatch);
	      
	  }    
}