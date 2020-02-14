package com.maximalus.console;

import com.maximalus.model.Cinema;
import com.maximalus.model.Movie;
import com.maximalus.model.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	Scanner scanner = new Scanner(System.in);
	Schedule schedule = new Schedule();
	Cinema cinema = new Cinema();
	Movie movie = new Movie();

	List<Movie>movieList = new ArrayList();
	List<Schedule>scheduleList = new ArrayList();
	List<Cinema>cinemaList = new ArrayList();

	public void mainText() {
		System.out.println("Введіть номер необхідної команди");
		System.out.println("1. Додати кінотеатр");
		System.out.println("2. Додати фільм");
		System.out.println("3. Додати сеанс");
		System.out.println("4. Видалити кінотеатр з  розкладу");
		System.out.println("5. Видалити фільм з розкладу в усі дні з конкретного кінотеатру");
		System.out.println("6. Видалити фільм з розкладу в усі дні з усіх кінотеатрів");
		System.out.println("7. Видалити сеанс певного фільму у певний день");
		System.out.println("8. Видалити фільм з бази данних та зі списку сеансів усіх кінотеатрів");
		System.out.println("9. Показати список всіх кінотеатрів");
		System.out.println("10. Показати список всіх фільмів");
		System.out.println("11. Показати список всіх сеансів");
		System.out.println("0. Вийти з програми");
		choiseAction(scanner.nextInt());
	}

	public void choiseAction(int choise) {
		switch(choise) {
			case 1:
				System.out.println("-------------------");
				cinema.addCinema(cinemaList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 2:
				System.out.println("-------------------");
				movie.addMovie(movieList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 3:
				System.out.println("-------------------");
				schedule.addSeance(scheduleList, movieList, cinemaList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 4:
				System.out.println("-------------------");
				cinema.removeCinema(cinemaList, scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 5:
				System.out.println("-------------------");
				System.out.println("Фільм був видалений з усіх кінотеатрів");
				schedule.removeMovieFromExactCinema(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 6:
				System.out.println("-------------------");
				schedule.removeMovieFromAllSchedule(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 7:
				System.out.println("-------------------");
				schedule.removeSeanceInExactDay(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 8:
				System.out.println("-------------------");
				movie.removeMovieFromBaseAndAllListes(movieList, scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 9:
				System.out.println("-------------------");
				cinema.showAllCinemas(cinemaList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 10:
				System.out.println("-------------------");
				movie.showListOfAllMovies(movieList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 11:
				System.out.println("-------------------");
				schedule.showAllSeances(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 0:
				System.out.println("-------------------");
				System.out.println("Програма завершила свою роботу");
				break;
		}
		if(choise<0||choise>11) {
			System.out.println("Введено номер неіснуючої команди. Введіть команду від 1 до 8");
		}
	}
}