package xol.lostinfinity.util.math;
public class Interpolator<T> extends ComplexInterpolator<T, T> {
    public Interpolator() {
        setParseFunc(value -> {
            return value;
        });
    }
}
