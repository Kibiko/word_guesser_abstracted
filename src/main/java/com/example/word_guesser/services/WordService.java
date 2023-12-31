package com.example.word_guesser.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private List<String> words;

    public WordService(){
        this.words = Arrays.asList(
          "hello",
          "goodbye",
          "testing",
          "mystery",
          "tokyo",
          "ikigai",
          "matsumoto",
          "mikudonarudo"
        );
    }

    public String getRandomWord(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.words.size());
        return this.words.get(randomIndex);
    }

}
