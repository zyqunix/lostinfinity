package xol.lostinfinity.util.animation.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.util.animation.client.blueprint.Animation;
import xol.lostinfinity.util.animation.client.blueprint.AnimationBlueprint;
import xol.lostinfinity.util.animation.client.blueprint.LoopMode;
import xol.lostinfinity.util.animation.client.blueprint.Timeline;
import xol.lostinfinity.util.animation.client.keyframe.KeyframeType;
import xol.lostinfinity.util.animation.client.keyframe.PositionKeyframe;
import xol.lostinfinity.util.animation.client.keyframe.RotationKeyframe;
import xol.lostinfinity.util.animation.client.keyframe.ScaleKeyframe;
import xol.lostinfinity.util.math.ComplexInterpolator;
import xol.lostinfinity.util.math.EulerAngle;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/AnimationDeserializer.class */
public class AnimationDeserializer implements JsonDeserializer<AnimationBlueprint> {

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/AnimationDeserializer$Keyframe.class */
    @FunctionalInterface
    public interface Keyframe<T, R> {
        T get(R r, KeyframeType keyframeType);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/client/AnimationDeserializer$Value.class */
    @FunctionalInterface
    public interface Value<T> {
        T get(float f, float f2, float f3);
    }

    /* JADX INFO: renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public AnimationBlueprint m772deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        AnimationBlueprint blueprint = new AnimationBlueprint();
        JsonObject root = json.getAsJsonObject();
        if (root.has("animations")) {
            JsonObject animations = root.getAsJsonObject("animations");
            for (Map.Entry<String, JsonElement> entry : animations.entrySet()) {
                String id = entry.getKey();
                JsonObject data = entry.getValue().getAsJsonObject();
                LoopMode loopMode = data.has("loop") ? LoopMode.parse(data.get("loop").getAsString()) : LoopMode.NONE;
                boolean isOverride = data.has("override_previous_animation") && data.get("override_previous_animation").getAsBoolean();
                float duration = data.get("animation_length").getAsFloat() * 20.0f;
                Animation animation = new Animation(id, loopMode, isOverride, duration);
                if (data.has("bones")) {
                    JsonObject bones = data.getAsJsonObject("bones");
                    for (Map.Entry<String, JsonElement> bone : bones.entrySet()) {
                        String boneId = bone.getKey();
                        Timeline timeline = parseTimeline(bone.getValue().getAsJsonObject());
                        animation.timelines.put(boneId, timeline);
                    }
                }
                blueprint.addAnimation(id, animation);
            }
        }
        return blueprint;
    }

    private Timeline parseTimeline(JsonObject object) {
        Timeline timeline = new Timeline();
        if (object.has("position")) {
            insertKeyframes(object.get("position"), timeline.position, this::getPosition);
        }
        if (object.has("rotation")) {
            insertKeyframes(object.get("rotation"), timeline.rotation, this::getRotation);
        }
        if (object.has("scale")) {
            insertKeyframes(object.get("scale"), timeline.scale, this::getScale);
        }
        return timeline;
    }

    private <T> void insertKeyframes(JsonElement element, ComplexInterpolator<T, ?> timeline, Function<JsonElement, T> function) {
        if (element.isJsonArray()) {
            timeline.put(Float.valueOf(0.0f), function.apply(element));
            return;
        }
        JsonObject obj = element.getAsJsonObject();
        for (Map.Entry<String, JsonElement> bone : obj.entrySet()) {
            float time = Float.parseFloat(bone.getKey()) * 20.0f;
            timeline.put(Float.valueOf(time), function.apply(bone.getValue()));
        }
    }

    private PositionKeyframe getPosition(JsonElement element) {
        return (PositionKeyframe) getKeyframe(element, (v1, v2, v3) -> {
            return new Vec3d(v1, v2, v3);
        }, PositionKeyframe::new);
    }

    private RotationKeyframe getRotation(JsonElement element) {
        return (RotationKeyframe) getKeyframe(element, EulerAngle::new, RotationKeyframe::new);
    }

    private ScaleKeyframe getScale(JsonElement element) {
        return (ScaleKeyframe) getKeyframe(element, (v1, v2, v3) -> {
            return new Vec3d(v1, v2, v3);
        }, ScaleKeyframe::new);
    }

    private <T, R> T getKeyframe(JsonElement element, Value<R> value, Keyframe<T, R> keyframe) {
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            R floats = value.get(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
            return keyframe.get(floats, KeyframeType.LINEAR);
        }
        JsonObject object = element.getAsJsonObject();
        JsonArray key = object.getAsJsonArray("post");
        KeyframeType type = getType(object);
        R floats2 = value.get(key.get(0).getAsFloat(), key.get(1).getAsFloat(), key.get(2).getAsFloat());
        return keyframe.get(floats2, type);
    }

    private KeyframeType getType(JsonObject object) {
        return object.has("lerp_mode") ? KeyframeType.parse(object.get("lerp_mode").getAsString()) : KeyframeType.LINEAR;
    }
}
