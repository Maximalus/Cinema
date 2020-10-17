package com.maximalus.model;

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
			return other.openTime == null;
		} else return openTime.equals(other.openTime);
	}

	@Override
	public String toString() {
		return "Cinema [nameOfCinema=" + nameOfCinema + ", openTime=" + openTime + ", closeTime=" + closeTime + "]";
	}
}

