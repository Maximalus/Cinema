package com.maximalus.dao;

import com.maximalus.model.Cinema;
import com.maximalus.model.Schedule;
import com.maximalus.model.Time;

import java.util.List;
import java.util.Scanner;

public class CinemaDao {
    private Scanner scanner = new Scanner(System.in);

    public void addCinema(List<Cinema> cinemaList){
        System.out.println("Введіть назву кінотеатру");
        String nameOfCinema = scanner.next();
        System.out.println("Введіть час відкриття кінотеатру у годинах");
        int hourOpen = scanner.nextInt();
        System.out.println("Введіть час відкриття кінотеатру у хвилинах");
        int minuteOpen = scanner.nextInt();
        System.out.println("Введіть час закриття кінотеатру у годинах");
        int hourClose = scanner.nextInt();
        System.out.println("Введіть час закриття кінотеатру у хвилинах");
        int minuteClose = scanner.nextInt();
        cinemaList.add(new Cinema(nameOfCinema, new Time(hourOpen, minuteOpen), new Time(hourClose, minuteClose)));
        System.out.println("Кінотеатр " + nameOfCinema + " доданий до бази данних. " + "Час відкриття " + hourOpen + " год."
                + minuteOpen + " хв." + " Час закриття " + hourClose + " год." + minuteClose + " хв.");
    }

    public void removeCinema(List<Cinema> cinemaList, List<Schedule> scheduleList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву кінотеатру");
        String nameOfCinema = scanner.next();
        boolean checkCinama = false;

        cinemaList
                .stream().filter(cinema -> cinema.getNameOfCinema().equalsIgnoreCase(nameOfCinema))
                .findFirst()
                .ifPresentOrElse(cinema -> {
                    cinemaList.remove(cinema);
                    deleteCinemaFromSchedule(scheduleList, nameOfCinema);
                    System.out.println("Кінотеатр " + nameOfCinema + " був видалений з бази даних кінотеатрів та розкладів");
                        },
                        () -> System.out.println("Кінотеатр з такою назвою " + nameOfCinema +  " в базі данних кінотеатрів не існує"));
    }

    private void deleteCinemaFromSchedule(List<Schedule> scheduleList, String nameOfCinema){
        scheduleList.stream().filter(schedule -> schedule.getCinema().getNameOfCinema().equalsIgnoreCase(nameOfCinema))
                .forEach(scheduleList::remove);
    }

    public void showAllCinemas(List<Cinema> cinemaList){
        cinemaList.forEach(cinema -> System.out.println("Кінотеатр " + cinema.getNameOfCinema() + " існує в базі данних"));
    }
}
