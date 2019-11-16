package com.stardusted1.Sendicate.app.core.cargo.condition;

import com.stardusted1.Sendicate.app.core.cargo.Frame;

public class HumidityCondition extends Condition {
	@Override
	public boolean CheckCondition(Frame frame) {
		float humidity = frame.getHumidity();
		if(humidity < max && humidity > min){
			return true;
		}
		return false;
	}
}
