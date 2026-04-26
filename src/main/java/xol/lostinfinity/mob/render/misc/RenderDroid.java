package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityDroid;
import xol.lostinfinity.mob.model.ModelDroid;
public class RenderDroid extends RenderLiving<EntityDroid> {
    public static final ResourceLocation TEXTURES_MK1_AGGRO = new ResourceLocation("lostinfinity:textures/entity/droid_mk1_aggressive.png");
    public static final ResourceLocation TEXTURES_MK1_REACT = new ResourceLocation("lostinfinity:textures/entity/droid_mk1_reactive.png");
    public static final ResourceLocation TEXTURES_MK2_AGGRO = new ResourceLocation("lostinfinity:textures/entity/droid_mk2_aggressive.png");
    public static final ResourceLocation TEXTURES_MK2_REACT = new ResourceLocation("lostinfinity:textures/entity/droid_mk2_reactive.png");
    public static final ResourceLocation TEXTURES_MK3_AGGRO = new ResourceLocation("lostinfinity:textures/entity/droid_mk3_aggressive.png");
    public static final ResourceLocation TEXTURES_MK3_REACT = new ResourceLocation("lostinfinity:textures/entity/droid_mk3_reactive.png");
    public RenderDroid(RenderManager manager) {
        super(manager, new ModelDroid(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityDroid entity) {
        switch (entity.getGrade()) {
            case 0:
                if (entity.isAggressive()) {
                    return TEXTURES_MK1_AGGRO;
                }
                return TEXTURES_MK1_REACT;
            case 1:
                if (entity.isAggressive()) {
                    return TEXTURES_MK2_AGGRO;
                }
                return TEXTURES_MK2_REACT;
            case 2:
                if (entity.isAggressive()) {
                    return TEXTURES_MK3_AGGRO;
                }
                return TEXTURES_MK3_REACT;
            default:
                if (entity.isAggressive()) {
                    return TEXTURES_MK1_AGGRO;
                }
                return TEXTURES_MK1_REACT;
        }
    }
    public void func_77043_a(EntityDroid entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
