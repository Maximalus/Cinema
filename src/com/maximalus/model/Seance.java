package com.maximalus.model;

public class Seance {
	private Movie movie;
	private Time timeOfStart;
	private Time timeOfFinish;

	public Seance(Movie movie, Time timeOfStart) {
		super();
		this.movie = movie;
		this.timeOfStart = timeOfStart;
		if(movie.getDuration().getMin()+timeOfStart.getMin()>59) {
			this.timeOfFinish=new Time((movie.getDuration().getHour()+timeOfStart.getHour()+1), (movie.getDuration().getMin()+timeOfStart.getMin()));
		}else {
			this.timeOfFinish=new Time((movie.getDuration().getHour()+timeOfStart.getHour()), (movie.getDuration().getMin()+timeOfStart.getMin()));
		}
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getTimeOfStart() {
		return timeOfStart;
	}

	public void setTimeOfStart(Time timeOfStart) {
		this.timeOfStart = timeOfStart;
	}

	public Time getTimeOfFinish() {
		return timeOfFinish;
	}

	public void setTimeOfFinish(Time timeOfFinish) {
		this.timeOfFinish = timeOfFinish;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((timeOfFinish == null) ? 0 : timeOfFinish.hashCode());
		result = prime * result + ((timeOfStart == null) ? 0 : timeOfStart.hashCode());
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
		Seance other = (Seance) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (timeOfFinish == null) {
			if (other.timeOfFinish != null)
				return false;
		} else if (!timeOfFinish.equals(other.timeOfFinish))
			return false;
		if (timeOfStart == null) {
			if (other.timeOfStart != null)
				return false;
		} else if (!timeOfStart.equals(other.timeOfStart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seance [movie=" + movie + ", timeOfStart=" + timeOfStart + ", timeOfFinish=" + timeOfFinish + "]";
	}
}
