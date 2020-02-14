package com.maximalus.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Movie {
	private String nameOfMovie;
	private Time duration;
	Scanner scanner = new Scanner(System.in);

	public Movie() {
		super();
	}

	public Movie(String nameOfMovie, Time duration) {
		super();
		this.nameOfMovie = nameOfMovie;
		this.duration = duration;
	}

	public String getNameOfMovie() {
		return nameOfMovie;
	}

	public void setNameOfMovie(String nameOfMovie) {
		this.nameOfMovie = nameOfMovie;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((nameOfMovie == null) ? 0 : nameOfMovie.hashCode());
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
		Movie other = (Movie) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (nameOfMovie == null) {
			if (other.nameOfMovie != null)
				return false;
		} else if (!nameOfMovie.equals(other.nameOfMovie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [nameOfMovie=" + nameOfMovie + ", duration=" + duration + "]";
	}

	public void addMovie(List<Movie>list) {
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.next();
		System.out.println("Введіть тривалість фільму у годинах");
		int hourDuration = scanner.nextInt();
		System.out.println("Введіть тривалість фільму у хвилинах");
		int minDuration = scanner.nextInt();
		list.add(new Movie(nameOfMovie, new Time(hourDuration, minDuration)));
		System.out.println("Фільм " + nameOfMovie + " доданий до бази данних");
	}

	public void showListOfAllMovies(List<Movie>list) {
		Iterator<Movie> iterator = list.iterator();
		while(iterator.hasNext()) {
			Movie next = iterator.next();
			if(next!=null) {
				System.out.println("Назва фільму - " + next.getNameOfMovie() + " тривалість фільму - " + next.getDuration().getHour() + " годин " + next.getDuration().getMin() + " хвилин");
			}
		}
	}

	public void removeMovieFromBaseAndAllListes(List<Movie>list, List<Schedule>list2) {
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.next();
		Iterator<Movie> iterator = list.iterator();
		while(iterator.hasNext()) {
			Movie next = iterator.next();
			if(next.getNameOfMovie().equals(nameOfMovie)) {
				iterator.remove();
				System.out.println("Фільм "+ nameOfMovie +" видалений з бази данних фільмів");
			}
		}

		Iterator<Schedule> iterator2 = list2.iterator();
		while(iterator2.hasNext()) {
			Schedule next = iterator2.next();
			if(next.getSeance().getMovie().getNameOfMovie().equals(nameOfMovie)) {
				iterator2.remove();
				System.out.println("Фільм " + nameOfMovie +" видалений з бази данних сеансів");
			}
		}
	}
}