package xol.lostinfinity.util.math;

import java.util.TreeMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/ComplexInterpolator.class */
public class ComplexInterpolator<T, R> extends TreeMap<Float, T> {
    private Interpolation<T, R> interpolateFunc;
    private Parse<T, R> parseFunc;
    private R defaultValue = null;

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/ComplexInterpolator$Interpolation.class */
    @FunctionalInterface
    public interface Interpolation<T, R> {
        R interpolate(Context context, T t, T t2, float f);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/ComplexInterpolator$Parse.class */
    @FunctionalInterface
    public interface Parse<T, R> {
        R parse(T t);
    }

    public ComplexInterpolator<T, R> setInterpolateFunc(Interpolation<T, R> interpolateFunc) {
        this.interpolateFunc = interpolateFunc;
        return this;
    }

    public ComplexInterpolator<T, R> setParseFunc(Parse<T, R> parseFunc) {
        this.parseFunc = parseFunc;
        return this;
    }

    public ComplexInterpolator<T, R> setDefaultValue(R value) {
        this.defaultValue = value;
        return this;
    }

    @Nullable
    public R interpolate(float key) {
        if (isEmpty()) {
            return this.defaultValue;
        }
        if (containsKey(Float.valueOf(key))) {
            return this.parseFunc.parse(get(Float.valueOf(key)));
        }
        float nextKey = getHigherKey(key);
        float lastKey = getLowerKey(key);
        if (nextKey == lastKey) {
            return this.parseFunc.parse(get(Float.valueOf(lastKey)));
        }
        float t = (key - lastKey) / (nextKey - lastKey);
        T next = get(Float.valueOf(nextKey));
        T prev = get(Float.valueOf(lastKey));
        return this.interpolateFunc.interpolate(new Context(lastKey, nextKey), prev, next, t);
    }

    public float getHigherKey(float time) {
        Float high = higherKey(Float.valueOf(time));
        if (high == null) {
            return lastKey().floatValue();
        }
        return high.floatValue();
    }

    public float getLowerKey(float time) {
        Float low = lowerKey(Float.valueOf(time));
        if (low == null) {
            return firstKey().floatValue();
        }
        return low.floatValue();
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/math/ComplexInterpolator$Context.class */
    public static class Context {
        public final float prevKey;
        public final float nextKey;

        public Context(float prevKey, float nextKey) {
            this.prevKey = prevKey;
            this.nextKey = nextKey;
        }
    }
}
