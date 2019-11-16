package com.stardusted1.Sendicate.app.core.cargo.condition;

import com.stardusted1.Sendicate.app.core.cargo.Frame;

public class TemperatureCondition extends Condition {
	@Override
	public boolean CheckCondition(Frame frame) {
		float temperature = frame.getTemperature();
		if (temperature < max && temperature > min) {
			return true;
		}
		return false;
	}
}
