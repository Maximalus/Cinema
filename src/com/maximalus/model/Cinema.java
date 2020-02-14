package com.maximalus.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Cinema {
	private String nameOfCinema;
	private Time openTime;
	private Time closeTime;
	Scanner scanner = new Scanner(System.in);

	public Cinema() {

	}

	public Cinema(String nameOfCinema, Time openTime, Time closeTime) {
		super();
		this.nameOfCinema = nameOfCinema;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public String getNameOfCinema() {
		return nameOfCinema;
	}

	public void setNameOfCinema(String nameOfCinema) {
		this.nameOfCinema = nameOfCinema;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closeTime == null) ? 0 : closeTime.hashCode());
		result = prime * result + ((nameOfCinema == null) ? 0 : nameOfCinema.hashCode());
		result = prime * result + ((openTime == null) ? 0 : openTime.hashCode());
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
		Cinema other = (Cinema) obj;
		if (closeTime == null) {
			if (other.closeTime != null)
				return false;
		} else if (!closeTime.equals(other.closeTime))
			return false;
		if (nameOfCinema == null) {
			if (other.nameOfCinema != null)
				return false;
		} else if (!nameOfCinema.equals(other.nameOfCinema))
			return false;
		if (openTime == null) {
			if (other.openTime != null)
				return false;
		} else if (!openTime.equals(other.openTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cinema [nameOfCinema=" + nameOfCinema + ", openTime=" + openTime + ", closeTime=" + closeTime + "]";
	}

	public void addCinema(List<Cinema>list) {
		System.out.println("Введіть назву кінотеатру");
		String nameOfCinema = scanner.next();
		System.out.println("Введіть час відкриття кінотеатру у годинах");
		int hourOpen = scanner.nextInt();
		System.out.println("Введіть час відкриття кінотеатру у хвилинах");
		int minOpen = scanner.nextInt();
		System.out.println("Введіть час закриття кінотеатру у годинах");
		int hourClose = scanner.nextInt();
		System.out.println("Введіть час закриття кінотеатру у хвилинах");
		int minClose = scanner.nextInt();
		list.add(new Cinema(nameOfCinema, new Time(hourOpen, minOpen), new Time(hourClose, minClose)));
		System.out.println("Кінотеатр " + nameOfCinema + " доданий до бази данних. " + "Час відкриття " + hourOpen + " год." + minOpen + " хв."
				+ " Час закриття " + hourClose + " год." + minClose + " хв.");
	}

	public void removeCinema(List<Cinema>list, List<Schedule>list2) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть назву кінотеатру");
		String nameOfCinema = scanner.next();

		Iterator<Cinema> iterator = list.iterator();
		while(iterator.hasNext()) {
			Cinema next = iterator.next();
			if(next.getNameOfCinema().equals(nameOfCinema)) {
				iterator.remove();
				System.out.println("Кінотеатр " + nameOfCinema + " був видалений з бази данних кінотеатрів");
			}
		}

		Iterator<Schedule> iterator2 = list2.iterator();
		while(iterator2.hasNext()) {
			Schedule next = iterator2.next();
			if(next.getCinema().getNameOfCinema().equals(nameOfCinema)) {
				iterator2.remove();
				System.out.println("Кінотеатр " + nameOfCinema + " був видалений з бази данних сеансів");
			}
		}
	}

	public void showAllCinemas(List<Cinema>list) {
		Iterator<Cinema> iterator = list.iterator();
		while(iterator.hasNext()) {
			Cinema next = iterator.next();
			if(next!=null) {
				System.out.println("Кінотеатр " + next.getNameOfCinema() + " існує в базі данних");
			}
		}
	}
}

