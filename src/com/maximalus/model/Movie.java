package com.maximalus.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Movie {
	private String nameOfMovie;
	private Time duration;
	private Scanner scanner = new Scanner(System.in);

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
			return other.nameOfMovie == null;
		} else return nameOfMovie.equals(other.nameOfMovie);
	}

	@Override
	public String toString() {
		return "Movie [nameOfMovie=" + nameOfMovie + ", duration=" + duration + "]";
	}

	public void removeMovie(List<Movie>movieList, List<Schedule>scheduleList) {
		System.out.println("Введіть назву фільму");
		String nameOfMovie = scanner.next();

		movieList.stream().filter(movie -> movie.getNameOfMovie().equalsIgnoreCase(nameOfMovie))
				.findFirst()
				.ifPresentOrElse(movie -> {
					movieList.remove(movie);
					System.out.println("Фільм "+ nameOfMovie +" видалений з бази данних фільмів");
				},
						() -> System.out.println("Фільм " + nameOfMovie + " в базі данних не існує"));

		scheduleList.stream().filter(schedule -> schedule.getSeance().getMovie().getNameOfMovie().equalsIgnoreCase(nameOfMovie))
				.findFirst()
				.ifPresentOrElse(schedule -> {
					scheduleList.remove(schedule);
					System.out.println("Фільм " + nameOfMovie +" видалений з бази данних сеансів");
				},
						() -> System.out.println("Фільм " + nameOfMovie + " в базі данних не існує"));
	}
}