package com.maximalus.dao;

import com.maximalus.model.Movie;
import com.maximalus.model.Schedule;
import com.maximalus.model.Time;

import java.util.List;
import java.util.Scanner;

public class MovieDao {
    private Scanner scanner = new Scanner(System.in);

    public void addMovie(List<Movie> movieList) {
        System.out.println("Введіть назву фільму");
        String nameOfMovie = scanner.next();
        System.out.println("Введіть тривалість фільму у годинах");
        int hourDuration = scanner.nextInt();
        System.out.println("Введіть тривалість фільму у хвилинах");
        int minDuration = scanner.nextInt();
        movieList.add(new Movie(nameOfMovie, new Time(hourDuration, minDuration)));
        System.out.println("Фільм " + nameOfMovie + " доданий до бази данних");
    }

    public void showAllMovies(List<Movie> movieList) {
        movieList.forEach(movie -> System.out.println("Назва фільму - " + movie.getNameOfMovie()
                + " тривалість фільму - " + movie.getDuration().getHour() + " годин " +
                movie.getDuration().getMinute() + " хвилин"));
    }

    public void removeMovie(List<Movie> movieList, List<Schedule> scheduleList){
        System.out.println("Введіть назву фільму");
        String nameOfMovie = scanner.next();
        movieList.stream().filter(movie -> movie.getNameOfMovie().equalsIgnoreCase(nameOfMovie))
                .findFirst()
                .ifPresentOrElse(movie -> {
                    movieList.remove(movie);
                    removeMovieFromSchedule(scheduleList, nameOfMovie);
                    System.out.println("Фільм " + nameOfMovie + " був видалений з бази данних фільмів та розкладів");
                },
                        () -> System.out.println("Фільм " + nameOfMovie + " у базі данних не знайдений"));
    }

    private void removeMovieFromSchedule(List<Schedule> scheduleList, String nameOfMovie){
        scheduleList.stream()
                .filter(schedule -> schedule.getSeance().getMovie().getNameOfMovie().equalsIgnoreCase(nameOfMovie))
                .forEach(scheduleList::remove);
    }
}
