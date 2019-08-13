package com.codecool.games.repository;

import com.codecool.games.model.Genre;
import com.codecool.games.model.ScreenFun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<ScreenFun, Long> {

    ScreenFun getScreenFunsById(long id);

    List<ScreenFun> getAllByRatingIsBetweenAndGenreIn(double from, double to, List<Genre> genres);

    List<ScreenFun> getScreenFunsByGenre(Genre genre);
}
