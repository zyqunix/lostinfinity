package xol.lostinfinity.util.animation.client.blueprint;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/blueprint/LoopMode.class */
public enum LoopMode {
    NONE,
    LOOP,
    HOLD;

    public static LoopMode parse(String val) {
        if ("hold_on_last_frame".equals(val)) {
            return HOLD;
        }
        if ("true".equals(val)) {
            return LOOP;
        }
        return NONE;
    }
}
