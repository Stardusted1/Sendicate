package com.stardusted1.Sendicate.app.core.cargo.condition;

import com.stardusted1.Sendicate.app.core.cargo.Frame;

import java.io.Serializable;

public abstract class Condition implements Serializable {
    protected int id;
    protected float max;
    protected float min;
    protected float preferred;

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getPreferred() {
        return preferred;
    }

    public void setPreferred(float preferred) {
        this.preferred = preferred;
    }

    public int getId(){
        return this.id;
    }

    public abstract boolean CheckCondition(Frame frame);

}
