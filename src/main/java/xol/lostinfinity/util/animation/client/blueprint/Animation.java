package xol.lostinfinity.util.animation.client.blueprint;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/blueprint/Animation.class */
public class Animation {
    public final String id;
    public final LoopMode loopMode;
    public final boolean isOverride;
    public final float duration;
    public final Map<String, Timeline> timelines = new HashMap();

    public Animation(String id, LoopMode loopMode, boolean isOverride, float duration) {
        this.id = id;
        this.loopMode = loopMode;
        this.isOverride = isOverride;
        this.duration = duration;
    }
}
