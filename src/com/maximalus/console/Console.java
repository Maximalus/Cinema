package com.maximalus.console;

import com.maximalus.dao.CinemaDao;
import com.maximalus.dao.MovieDao;
import com.maximalus.dao.ScheduleDao;
import com.maximalus.model.Cinema;
import com.maximalus.model.Movie;
import com.maximalus.model.Schedule;
import com.maximalus.model.Seance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	private Scanner scanner = new Scanner(System.in);
	private ScheduleDao scheduleDao = new ScheduleDao();
	private CinemaDao cinemaDao = new CinemaDao();
	private MovieDao movieDao = new MovieDao();

	private List<Movie>movieList = new ArrayList();
	private List<Schedule>scheduleList = new ArrayList();
	private List<Cinema>cinemaList = new ArrayList();
	private List<Seance> seanceList = new ArrayList<>();

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
				cinemaDao.addCinema(cinemaList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 2:
				System.out.println("-------------------");
				movieDao.addMovie(movieList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 3:
				System.out.println("-------------------");
				scheduleDao.addSeance(cinemaList, movieList, scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 4:
				System.out.println("-------------------");
				cinemaDao.removeCinema(cinemaList, scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 5:
				System.out.println("-------------------");
				scheduleDao.removeMovieFromExactCinema(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 6:
				System.out.println("-------------------");
				scheduleDao.removeMovieFromAllSchedules(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 7:
				System.out.println("-------------------");
				scheduleDao.removeSeanceInExactDay(scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 8:
				System.out.println("-------------------");
				movieDao.removeMovie(movieList, scheduleList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 9:
				System.out.println("-------------------");
				cinemaDao.showAllCinemas(cinemaList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 10:
				System.out.println("-------------------");
				movieDao.showAllMovies(movieList);
				System.out.println("-------------------");
				System.out.println();
				mainText();
				break;
			case 11:
				System.out.println("-------------------");
				scheduleDao.showAllSeances(scheduleList);
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