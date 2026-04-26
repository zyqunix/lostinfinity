package xol.lostinfinity.util.math;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/Interpolator.class */
public class Interpolator<T> extends ComplexInterpolator<T, T> {
    public Interpolator() {
        setParseFunc(value -> {
            return value;
        });
    }
}
