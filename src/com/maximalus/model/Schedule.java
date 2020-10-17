package com.maximalus.model;

import java.util.Scanner;

public class Schedule {
	private Cinema cinema;
	private Seance seance;
	private Days day;
	private Scanner scanner = new Scanner(System.in);

	public Schedule() {
	}

	public Schedule(Cinema cinema, Seance seance, Days day) {
		this.cinema = cinema;
		this.seance = seance;
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
			return other.seance == null;
		} else return seance.equals(other.seance);
	}

	@Override
	public String toString() {
		return "Schedule [cinema=" + cinema + ", seance=" + seance + ", day=" + day + "]";
	}


}
