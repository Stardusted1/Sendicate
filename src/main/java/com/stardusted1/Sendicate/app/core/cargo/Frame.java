package com.stardusted1.Sendicate.app.core.cargo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Frame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected Date time;
	protected float temperature;
	protected float humidity;
	protected float illumination;
	protected float acceleration;
	protected long transmitterId;

	public Frame() {
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getIllumination() {
		return illumination;
	}

	public void setIllumination(float illumination) {
		this.illumination = illumination;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public long getTransmitterId() {
		return transmitterId;
	}

	public void setTransmitterId(long transmitterId) {
		this.transmitterId = transmitterId;
	}

	public long getId() {
		return id;
	}


}
