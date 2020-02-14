package com.maximalus.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Schedule {
	private Cinema cinema;
	private Seance seance;
	private Days day;
	Scanner scanner = new Scanner(System.in);

	public Schedule() {
		super();
	}

	public Schedule(Cinema cinema, Seance seance, Days day) {
		super();
		this.cinema = cinema;
		this.seance = seance;
		this.day = day;
	}

	public Schedule(Cinema cinema) {
		super();
		this.cinema = cinema;
	}

	public Schedule(Seance seance) {
		super();
		this.seance = seance;
	}

	public Schedule(Days day) {
		super();
		this.day = day;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Days getDay() {
		return day;
	}

	public void setDay(Days day) {
		this.day = day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinema == null) ? 0 : cinema.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((seance == null) ? 0 : seance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (cinema == null) {
			if (other.cinema != null)
				return false;
		} else if (!cinema.equals(other.cinema))
			return false;
		if (day != other.day)
			return false;
		if (seance == null) {
			if (other.seance != null)
				return false;
		} else if (!seance.equals(other.seance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [cinema=" + cinema + ", seance=" + seance + ", day=" + day + "]";
	}

	public void addSeance(List<Schedule>list, List<Movie>list2, List<Cinema>list3) {
		Schedule schedule = new Schedule();
		Days[]daysMassive = Days.values();

		System.out.println("Введіть назву кінотеатру");
		String nameOfCinema = scanner.next();
		System.out.println("Введіть назву фільма");
		String nameOfMovie = scanner.next();
		System.out.println("Введіть день показу фільму англійською мовою");
		String day = scanner.next().toUpperCase();
		System.out.println("Введіть час показу фільму у годинах");
		int hourStart = scanner.nextInt();
		System.out.println("Введіть час показу фільму у хвилинах");
		int minStart = scanner.nextInt();

		boolean checkCinema = false;
		boolean checkMovie = false;
		boolean checkDay = false;

		if(checkCinema!=true) {
			Iterator<Cinema> iterator = list3.iterator();
			while(iterator.hasNext()) {
				Cinema next = iterator.next();
				if(next.getNameOfCinema().equals(nameOfCinema)) {
					checkCinema = true;
					Cinema cinema = new Cinema();
					cinema.setNameOfCinema(next.getNameOfCinema());
					cinema.setOpenTime(new Time(next.getOpenTime().getHour(), next.getOpenTime().getMin()));
					cinema.setCloseTime(new Time(next.getCloseTime().getHour(), next.getCloseTime().getMin()));
					schedule.setCinema(cinema);
				}
			}
		}

		if(checkCinema) {
			Iterator<Movie> iterator = list2.iterator();
			while(iterator.hasNext()) {
				Movie next = iterator.next();
				if(next.getNameOfMovie().equals(nameOfMovie)) {
					checkMovie=true;
					schedule.setSeance(new Seance(next, new Time(hourStart, minStart)));
				}
			}
		}

		if(checkCinema&&checkMovie) {
			for (int i = 0; i < daysMassive.length; i++) {
				if(daysMassive[i].toString().equals(day)) {
					schedule.setDay(Days.valueOf(day));
					checkDay = true;
				}
			}
		}

		if(checkCinema&&checkMovie&&checkDay) {
			if(schedule.getSeance().getTimeOfStart().getHour()>=schedule.getCinema().getOpenTime().getHour()) {
				if(schedule.getSeance().getTimeOfFinish().getHour()<schedule.getCinema().getCloseTime().getHour()) {
					list.add(schedule);
					System.out.println("Фільм " + schedule.getSeance().getMovie().getNameOfMovie() + " буже демонструватися "
							+ " у кінотеатрі "+ schedule.getCinema().getNameOfCinema() + " o " + schedule.getSeance().getTimeOfStart().getHour()
							+ " годині " +schedule.getSeance().getTimeOfStart().getMin() + " хвилин. Сеанс завершиться "
							+ "о " + schedule.getSeance().getTimeOfFinish().getHour() + " годині "
							+ schedule.getSeance().getTimeOfFinish().getMin() +" хвилин ");
				}else {
					System.out.println("Сеанс не може відбуватися після закриття кінотеатру. Введіть коректні дані до програми.");
				}
			}else {
				System.out.println("Сеанс не може відбуватися до початку роботи кінотеатру. Введіть коректні дані до програми.");
			}
		}

		if(checkCinema!=true) {
			System.out.println("Введеної назви кінотеатру не існує у базі данних. Введіть коректні дані до програми.");
		}
		if(checkMovie!=true) {
			System.out.println("Введеної назви фільму не існує у базі данних. Введіть коректні дані до програми.");
		}
		if(checkDay!=true) {
			System.out.println("Введеної назви дня не існує. Введіть коректні дані до програми.");
		}
	}

	public void removeSeanceInExactDay(List<Schedule>list) {
		System.out.println("Введіть назву кінотеатру");
		String nameOfCinema = scanner.next();
		System.out.println("Введіть день");
		String day = scanner.next().toUpperCase();
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.next();

		Iterator<Schedule> iterator = list.iterator();
		while(iterator.hasNext()) {
			Schedule next = iterator.next();
			if(next.getCinema().getNameOfCinema().equals(nameOfCinema)) {
				if(next.getDay().toString().equals(day)) {
					if(next.getSeance().getMovie().getNameOfMovie().equals(nameOfMovie)) {
						iterator.remove();
						System.out.println("Фільм " + next.getSeance().getMovie().getNameOfMovie()+ " був видалений з усіх сеансів у " + next.getDay().name() + " в кінотеатрі " + next.getCinema().getNameOfCinema());
					}
				}
			}
		}
	}

	public void removeMovieFromAllSchedule(List<Schedule>list) {
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.nextLine();
		Iterator<Schedule> iterator = list.iterator();
		while(iterator.hasNext()) {
			Schedule next = iterator.next();
			if(next.getSeance().getMovie().getNameOfMovie().equals(nameOfMovie)) {
				iterator.remove();
				System.out.println("Фільм був видалений з усіх кінотеатрів");
			}
		}
	}

	public void removeMovieFromExactCinema(List<Schedule>list) {
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.nextLine();
		System.out.println("Введіть назву кінотеатру");
		String nameOfCinema = scanner.nextLine();
		Iterator<Schedule> iterator = list.iterator();
		while(iterator.hasNext()) {
			Schedule next = iterator.next();
			if(next.getCinema().getNameOfCinema().equals(nameOfCinema)) {
				if(next.getSeance().getMovie().getNameOfMovie().equals(nameOfMovie)) {
					iterator.remove();
					System.out.println("Фільм був видалений зі списків сеансів кінотеатру " + next.getCinema().getNameOfCinema());
				}
			}
		}
	}

	public void showAllSeances(List<Schedule>list) {
		Iterator<Schedule> iterator = list.iterator();
		while(iterator.hasNext()) {
			Schedule next = iterator.next();
			if(next!=null) {
				System.out.println("Назва фільму - " + next.getCinema().getNameOfCinema() +
						". Час початку сеансу - " + next.getSeance().getTimeOfStart().getHour() + " годин " +
						next.getSeance().getTimeOfStart().getMin() + " хвилин " +
						" та час завершення " + next.getSeance().getTimeOfFinish().getHour() + " годин " +
						next.getSeance().getTimeOfFinish().getMin() + " хвилин у наступний день - " + next.getDay().name());
			}
		}
	}
}
