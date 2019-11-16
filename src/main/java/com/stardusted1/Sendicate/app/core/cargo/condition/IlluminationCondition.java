package com.stardusted1.Sendicate.app.core.cargo.condition;

import com.stardusted1.Sendicate.app.core.cargo.Frame;

public class IlluminationCondition extends Condition {
	@Override
	public boolean CheckCondition(Frame frame) {
		float illumination = frame.getIllumination();
		if (illumination < max && illumination > min) {
			return true;
		}
		return false;
	}
}