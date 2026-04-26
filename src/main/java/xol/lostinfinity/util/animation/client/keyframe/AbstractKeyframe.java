package xol.lostinfinity.util.animation.client.keyframe;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/keyframe/AbstractKeyframe.class */
public abstract class AbstractKeyframe<T> {
    private T value;
    private KeyframeType type;

    public KeyframeType getType() {
        return this.type;
    }

    public void setType(KeyframeType type) {
        this.type = type;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public AbstractKeyframe(T value) {
        this(value, KeyframeType.LINEAR);
    }

    public AbstractKeyframe(T value, KeyframeType type) {
        this.value = value;
        this.type = type;
    }
}
