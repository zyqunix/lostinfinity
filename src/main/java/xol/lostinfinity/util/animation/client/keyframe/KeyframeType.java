package xol.lostinfinity.util.animation.client.keyframe;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/keyframe/KeyframeType.class */
public enum KeyframeType {
    LINEAR,
    SMOOTH,
    STEP;

    public static KeyframeType parse(String val) {
        if ("catmullrom".equals(val)) {
            return SMOOTH;
        }
        if ("step".equals(val)) {
            return STEP;
        }
        return LINEAR;
    }
}
