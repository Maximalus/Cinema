package com.maximalus.model;

public class Time {
	private int hour;
	private int minute;

	public Time(int hour, int minute) {
		if(hour<=23) {
			this.hour = hour;
		}else {
			System.out.println("Помилка вводу");
		}
		if(minute<=59) {
			this.minute=minute;
		}else {
			System.out.println("Помилка вводу");
		}

	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + minute;
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
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		return minute == other.minute;
	}

	@Override
	public String toString() {
		return "Time [hour=" + hour + ", minute=" + minute + "]";
	}
}
