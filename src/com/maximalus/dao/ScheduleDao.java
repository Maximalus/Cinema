package com.maximalus.dao;

import com.maximalus.model.*;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScheduleDao {
    private Scanner scanner = new Scanner(System.in);
    private Schedule schedule = new Schedule();

    private AtomicBoolean checkCinema = new AtomicBoolean(false);
    private AtomicBoolean checkMovie = new AtomicBoolean(false);
    private AtomicBoolean checkDay = new AtomicBoolean(false);

    public void addSeance(List<Cinema> cinemaList, List<Movie> movieList, List<Schedule> scheduleList) {
        System.out.println("Введіть назву кінотеатру");
        String nameOfCinema = scanner.next();
        System.out.println("Введіть назву фільма");
        String nameOfMovie = scanner.next();
        System.out.println("Введіть день показу фільму англійською мовою");
        String day = scanner.next().toUpperCase();
        System.out.println("Введіть час показу фільму у годинах");
        int hourStart = scanner.nextInt();
        System.out.println("Введіть час показу фільму у хвилинах");
        int minuteStart = scanner.nextInt();

        setCinemaForSeance(cinemaList, nameOfCinema);
        setMovieForSeance(movieList, nameOfMovie, hourStart, minuteStart);
        setDayForSeance(day);
        setScheduleForSeance(scheduleList);
    }

    private void setCinemaForSeance(List<Cinema> cinemaList, String nameOfCinema){
        if(!checkCinema.get()) {
            cinemaList.stream().filter(cinema -> cinema.getNameOfCinema().equalsIgnoreCase(nameOfCinema))
                    .findFirst()
                    .ifPresentOrElse(cinema -> {
                                checkCinema.set(true);
                                schedule.setCinema(cinema);
                            },
                            () -> System.out.println("Кінотеатр " + nameOfCinema + " не існує в базі данних кінотеатрів"));
        }
    }

    private void setMovieForSeance(List<Movie> movieList, String nameOfMovie, int hourStart, int minuteStart){
        if(checkCinema.get()) {
            movieList.stream().filter(movie -> movie.getNameOfMovie().equalsIgnoreCase(nameOfMovie))
                    .findFirst()
                    .ifPresentOrElse(movie -> {
                                checkMovie.set(true);
                                schedule.setSeance(new Seance(movie, new Time(hourStart, minuteStart)));
                            },
                            () -> System.out.println("Фільм " + nameOfMovie + " не існує в базі даних фільмів"));
        }
    }

    private void setDayForSeance(String day) {
        if(checkCinema.get() && checkMovie.get()) {
            for (Days days : Days.values()) {
                if (days.toString().equals(day)) {
                    schedule.setDay(Days.valueOf(day));
                    checkDay.set(true);
                }
            }
        }else{
            System.out.println("День " + day + " не існує");
        }
    }

    private void setScheduleForSeance(List<Schedule> scheduleList) {
        if(checkCinema.get() && checkMovie.get() &&checkDay.get()) {
            if(schedule.getSeance().getTimeOfStart().getHour()>=schedule.getCinema().getOpenTime().getHour()) {
                if(schedule.getSeance().getTimeOfFinish().getHour()<schedule.getCinema().getCloseTime().getHour()) {
                    scheduleList.add(schedule);
                    System.out.println("Фільм " + schedule.getSeance().getMovie().getNameOfMovie() + " буде демонструватися "
                            + " у кінотеатрі "+ schedule.getCinema().getNameOfCinema() + " o " + schedule.getSeance().getTimeOfStart().getHour()
                            + " годині " +schedule.getSeance().getTimeOfStart().getMinute() + " хвилин. Сеанс завершиться "
                            + "о " + schedule.getSeance().getTimeOfFinish().getHour() + " годині "
                            + schedule.getSeance().getTimeOfFinish().getMinute() +" хвилин ");
                }else {
                    System.out.println("Сеанс не може відбуватися після закриття кінотеатру. Введіть коректні дані у консоль.");
                }
            }else {
                System.out.println("Сеанс не може відбуватися до початку роботи кінотеатру. Введіть коректні дані у консоль.");
            }
        }
    }

    public void removeSeanceInExactDay(List<Schedule> scheduleList) {
        System.out.println("Введіть назву кінотеатру");
        String nameOfCinema = scanner.next();
        System.out.println("Введіть день");
        String day = scanner.next().toUpperCase();
        System.out.println("Введіть назву фільму");
        String nameOfMovie = scanner.next();

        //todo complete this method

    }

    public void removeMovieFromAllSchedules(List<Schedule> scheduleList) {
        System.out.println("Введіть назву фільму");
        String nameOfMovie = scanner.nextLine();
        scheduleList.stream().filter(schedule -> schedule.getSeance().getMovie().getNameOfMovie().equalsIgnoreCase(nameOfMovie))
                .findFirst()
                .ifPresentOrElse(schedule -> {
                            scheduleList.remove(schedule);
                            System.out.println("Фільм " + nameOfMovie + " був видалений з розкладів усіх кінотеатрів");
                        },
                        () -> System.out.println("Фільм з назвою " + nameOfMovie + " у базі даних не існує"));
    }

    public void removeMovieFromExactCinema(List<Schedule> scheduleList) {
        System.out.println("Введіть назву фільму");
        String nameOfMovie = scanner.nextLine();
        System.out.println("Введіть назву кінотеатру");
        String nameOfCinema = scanner.nextLine();

        scheduleList.stream().filter(schedule -> schedule.getCinema().getNameOfCinema().equalsIgnoreCase(nameOfCinema))
                .filter(schedule -> schedule.getSeance().getMovie().getNameOfMovie().equalsIgnoreCase(nameOfMovie))
                .findFirst()
                .ifPresentOrElse(
                        schedule -> {
                            scheduleList.remove(schedule);
                            System.out.println("Фільм був видалений зі списків сеансів кінотеатру "
                                    + schedule.getCinema().getNameOfCinema());
                        }, () -> System.out.println("Фільм з назвою " + nameOfMovie
                                + " у розкладі кінотеатру " + nameOfCinema + " не присутній")
                );
    }

    public void showAllSeances(List<Schedule> scheduleList) {
        scheduleList.forEach(schedule ->
                System.out.println("Назва фільму - " + schedule.getCinema().getNameOfCinema() +
                        ". Час початку сеансу - " + schedule.getSeance().getTimeOfStart().getHour() + " годин " +
                        schedule.getSeance().getTimeOfStart().getMinute() + " хвилин " +
                        " та час завершення " + schedule.getSeance().getTimeOfFinish().getHour() + " годин " +
                        schedule.getSeance().getTimeOfFinish().getMinute() + " хвилин у наступний день - " + schedule.getDay().name())
        );
    }

}
