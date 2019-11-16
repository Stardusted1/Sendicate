package com.stardusted1.Sendicate.app.core.cargo.condition;

import com.stardusted1.Sendicate.app.core.cargo.Frame;

public class AccelerationCondition extends Condition{
	@Override
	public boolean CheckCondition(Frame frame) {
		float acceleration = frame.getAcceleration();
		if(acceleration < max && acceleration > min){
			return true;
		}
		return false;
	}
}
