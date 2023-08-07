package com.example.word_guesser.repositories;

import com.example.word_guesser.models.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameList {
//  keep track of games
//  be able to add a new game to track
//  get a game by ID

    private List<Game> games;

    public GameList(){
        this.games = new ArrayList<>();
    }

    public void addGame(Game game){
        this.games.add(game);
    }

    public Game getGameById(int id){
        return this.games.get(id - 1);
    }
}
