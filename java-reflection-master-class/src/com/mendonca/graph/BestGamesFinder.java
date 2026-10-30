package com.mendonca.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.mendonca.graph.Annotations.DependsOn;
import com.mendonca.graph.Annotations.FinalResult;
import com.mendonca.graph.Annotations.Operation;

public class BestGamesFinder {

    private Database database = new Database();

    @Operation("All-Games")
    public Set<String> getAllGames() {
    	System.out.println("1");
        return database.readAllGames();
    }

    @Operation("Game-To-Price")
    public Map<String, Float> getGameToPrice(@DependsOn("All-Games") Set<String> allGames) {
    	System.out.println("2");
        return database.readGameToPrice(allGames);
    }

    @Operation("Game-To-Rating")
    public Map<String, Float> getGameToRating(@DependsOn("All-Games") Set<String> allGames) {
    	System.out.println("3");
        return database.readGameToRatings(allGames);
    }

    
    @Operation("Score-To-Game")
    public SortedMap<Double, String> scoreGames(@DependsOn("Game-To-Price") Map<String, Float> gameToPrice,
                                                @DependsOn("Game-To-Rating") Map<String, Float> gameToRating) {
    	System.out.println("4");
        SortedMap<Double, String> gameToScore = new TreeMap<>(Collections.reverseOrder());
        for (String gameName : gameToPrice.keySet()) {
            double score = (double) gameToRating.get(gameName) / gameToPrice.get(gameName);
            gameToScore.put(score, gameName);
        }

        return gameToScore;
    }


    @FinalResult
    public List<String> getTopGames(@DependsOn("Score-To-Game") SortedMap<Double, String> gameToScore) {
        System.out.println("Final");
    	return new ArrayList<>(gameToScore.values());
    }
	
	
}
