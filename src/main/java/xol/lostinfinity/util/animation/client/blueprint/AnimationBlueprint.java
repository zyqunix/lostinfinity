package xol.lostinfinity.util.animation.client.blueprint;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
public class AnimationBlueprint {
    private final Map<String, Animation> animations = new LinkedHashMap();
    public Map<String, Animation> getAnimations() {
        return this.animations;
    }
    public void addAnimation(String id, Animation animation) {
        this.animations.put(id, animation);
    }
    public void removeAnimation(String id) {
        this.animations.remove(id);
    }
    @Nullable
    public Animation getAnimation(String id) {
        return this.animations.get(id);
    }
    public Collection<Animation> getAll() {
        return this.animations.values();
    }
}
