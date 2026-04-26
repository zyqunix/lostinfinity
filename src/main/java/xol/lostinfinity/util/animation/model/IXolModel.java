package xol.lostinfinity.util.animation.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.util.animation.client.AnimationDeserializer;
import xol.lostinfinity.util.animation.client.blueprint.AnimationBlueprint;
import xol.lostinfinity.util.animation.entity.IXolAnimated;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/model/IXolModel.class */
public interface IXolModel {
    public static final Set<IXolModel> models = new HashSet();
    public static final Gson gson = new GsonBuilder().registerTypeAdapter(AnimationBlueprint.class, new AnimationDeserializer()).create();

    ResourceLocation getAnimationJson();

    void setBlueprint(AnimationBlueprint animationBlueprint);

    AnimationBlueprint getBlueprint();

    Map<String, ModelRenderer> getIndex();

    static void initializeModel(IXolModel model) {
        models.add(model);
        model.indexModel();
        model.refresh();
    }

    default void indexModel() {
        ModelRenderer renderer;
        try {
            Map<String, ModelRenderer> index = getIndex();
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (ModelRenderer.class.isAssignableFrom(field.getType()) && (renderer = (ModelRenderer) field.get(this)) != null) {
                    renderer.lockDefaultRotation();
                    index.put(field.getName(), renderer);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    default void refresh() {
        try {
            InputStream input = Minecraft.func_71410_x().func_110442_L().func_110536_a(getAnimationJson()).func_110527_b();
            InputStreamReader reader = new InputStreamReader(input);
            setBlueprint((AnimationBlueprint) gson.fromJson(reader, AnimationBlueprint.class));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    default void animate(Entity entity) {
        if (entity instanceof IXolAnimated) {
            IXolAnimated animated = (IXolAnimated) entity;
            animated.setAnimationBlueprint(getBlueprint());
            animated.getAnimationHandler().update(this, Minecraft.func_71410_x().func_147113_T() ? 0.0f : Minecraft.func_71410_x().func_193989_ak());
        }
    }
}
