package com.codecool.games.service;

import com.codecool.games.model.Genre;
import com.codecool.games.model.Questionnaire;
import com.codecool.games.model.ScreenFun;
import com.codecool.games.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private Random random;

    public ScreenFun getRandomGame() {
        return gameRepository.getScreenFunsById(random.nextInt((int) gameRepository.count()) + 1);
    }

    public List<ScreenFun> getAllGames() {
        return gameRepository.findAll();
    }

    public ScreenFun getFilteredGame(Questionnaire questionnaire) {
        List<Genre> genres = setGenre(questionnaire);

        int age = questionnaire.getAge();

        double from = questionnaire.getMasochist() == 1 ? 0 : 50;
        double to = questionnaire.getMasochist() == 1 ? 51 : 101;
        List<ScreenFun> results = gameRepository.getAllByRatingIsBetweenAndGenreIn(from, to, genres);

        return results.get(random.nextInt(results.size()));
    }


    private List<Genre> setGenre(Questionnaire questionnaire) {
        switch (questionnaire.getMood()) {
            case CRY:
                return Arrays.asList(Genre.DRAMA, Genre.ROMANCE);
            case LAUGH:
                return Arrays.asList(Genre.COMEDY, Genre.ANIMATED);
            case THINK:
                return Arrays.asList(Genre.ACTION, Genre.DRAMA);
            case BE_SCARED:
                return Arrays.asList(Genre.HORROR);
            case LEARN:
                return Arrays.asList(Genre.DOCUMENTARY, Genre.ANIMATED);
            case BE_THRILLED:
                return Arrays.asList(Genre.ACTION, Genre.ANIMATED, Genre.SCI_FI, Genre.HORROR);
            default:
                return Arrays.asList(Genre.values());
        }
    }
}
