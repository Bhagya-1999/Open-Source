package com.fresco;

import java.util.*;
import java.util.Map;
import java.util.TreeMap;

public class TreemapHandson {
    public TreeMap<Integer,String> createPlayerPositionMap(String cricketDataset)
    {
        TreeMap<Integer,String> playerPositionMap = new TreeMap<Integer,String>();
        String[] playerSet = cricketDataset.split("\\|");
        for(String player: playerSet){
            String[] playerDetails = player.split(",");
            playerPositionMap.put(Integer.parseInt(playerDetails[0].toString()), playerDetails[1]);
        }
        return playerPositionMap;
    }
    public TreeMap<String,Integer> createPlayerScoreMap(String cricketDataset)
    {
        TreeMap<String,Integer> playerScoreMap = new TreeMap<String,Integer>();
        String[] playerSet = cricketDataset.split("\\|");
        for(String player: playerSet){
            String[] playerDetails = player.split(",");
            playerScoreMap.put(playerDetails[1],Integer.parseInt(playerDetails[2].toString()));
        }
        return playerScoreMap;
    }
    public TreeMap<String,Integer> createMatchesMap(String cricketDataset)
    {
        TreeMap<String,Integer> nameWithNumberOfMatchesMap = new TreeMap<>();
        TreeMap<String,Integer> nameWithTotalScore = new TreeMap<>();
		  TreeMap<String,Integer> nameWithAverageScore = new TreeMap<>();
        String[] playerDetailsSet = cricketDataset.split("\\n");
        String joinedPlayerSet = String.join("|",playerDetailsSet);
        String[] playerSet = joinedPlayerSet.split("\\|");
        for(String player: playerSet){
            String[] playerDetails = player.split(",");
            if(Integer.parseInt(playerDetails[0]) == 1){
                if(nameWithNumberOfMatchesMap.size() == 0 && nameWithTotalScore.size() == 0){
                    nameWithNumberOfMatchesMap.put(playerDetails[1],1);
                    nameWithTotalScore.put(playerDetails[1],Integer.parseInt(playerDetails[2]));
                }
                else {
                    if(nameWithNumberOfMatchesMap.get(playerDetails[1])!=null)
                        nameWithNumberOfMatchesMap.put(playerDetails[1],nameWithNumberOfMatchesMap.get(playerDetails[1])+1);
                    else
                        nameWithNumberOfMatchesMap.put(playerDetails[1],1);
                    if(nameWithTotalScore.get(playerDetails[1])!=null){
                        nameWithTotalScore.put(playerDetails[1],nameWithTotalScore.get(playerDetails[1])+Integer.parseInt(playerDetails[2]));
                    } else {
                        nameWithTotalScore.put(playerDetails[1],Integer.parseInt(playerDetails[2]));
                    }
                }
            }
        }
        for(Map.Entry<String,Integer> positionEntry: nameWithTotalScore.entrySet()){
            Integer averageScore = positionEntry.getValue() / nameWithNumberOfMatchesMap.get(positionEntry.getKey());
            nameWithAverageScore.put(positionEntry.getKey(),averageScore);
        }
        return nameWithAverageScore;
    }
    public String getQuery(String cricketDataset,String query)
    {
        String result = "";
        String[] queryType = query.split(" ");
        if(queryType.length == 3){
            Integer firstPosition = Integer.parseInt(queryType[1]);
            Integer endPosition = Integer.parseInt(queryType[2]);
            TreeMap<Integer,String> playerPositionMap = this.createPlayerPositionMap(cricketDataset);
            for(Map.Entry<Integer,String> entry: playerPositionMap.entrySet()){
                if(entry.getKey() >= firstPosition && entry.getKey() <= endPosition)
                    result = result + entry.getKey().toString()+ " "+entry.getValue()+"\n";
                }
            }
            else if(queryType.length == 2){
                TreeMap<Integer,String> resultMap = new TreeMap<>();
                Integer thresholdScore = Integer.parseInt(queryType[1]);
                TreeMap<String,Integer> playerScoreMap = this.createPlayerScoreMap(cricketDataset);
                TreeMap<Integer,String> playerPositionMap = this.createPlayerPositionMap(cricketDataset);
                Integer position = 0;
                for(Map.Entry<String,Integer> entry: playerScoreMap.entrySet()) {
                    if(entry.getValue() > thresholdScore) {
                        for(Map.Entry<Integer,String> positionEntry: playerPositionMap.entrySet()) {
                            if(positionEntry.getValue().equals(entry.getKey())) {
                                position = positionEntry.getKey();
                            }
                        }
                        resultMap.put(position, entry.getKey());
                    }
                }
                for (Map.Entry<Integer,String> resultEntry: resultMap.entrySet()) {
                    result = result + resultEntry.getKey().toString() + " "+resultEntry.getValue() + "\n";
                }
            }
            else if(queryType.length == 1) {
                TreeMap<String,Integer> nameWithAverageScore = this.createMatchesMap(cricketDataset);
                Map.Entry<String,Integer> maxEntry = null;
                for(Map.Entry<String,Integer> entry: nameWithAverageScore.entrySet()) {
                    if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                        maxEntry = entry;
                    }
                }
                result = "The Efficient Opener is "+ maxEntry.getKey();
            }
        return result;

    }
}