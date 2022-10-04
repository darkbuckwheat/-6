package Common.classes;

import Common.exceptions.IncorrectFieldValueException;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private long x; //Значение поля должно быть больше -846
    private double y;

    public Coordinates(long x, double y) throws IncorrectFieldValueException{
        setX(x);
        setY(y);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) throws IncorrectFieldValueException {
        if (x < -845) throw new IncorrectFieldValueException("x", String.valueOf(x), "greater than -846");
        this.x = x;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
