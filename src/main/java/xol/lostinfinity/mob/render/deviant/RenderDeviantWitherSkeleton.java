package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantWitherSkeleton;
import xol.lostinfinity.mob.model.deviant.ModelDeviantWitherSkeleton;
public class RenderDeviantWitherSkeleton extends RenderLiving<EntityDeviantWitherSkeleton> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantwitherskeleton.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantwitherskeleton_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantWitherskeleton_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantwitherskeleton_3.png");
    public RenderDeviantWitherSkeleton(RenderManager manager) {
        super(manager, new ModelDeviantWitherSkeleton(), 0.5f);
    }
    public void func_77041_b(EntityDeviantWitherSkeleton entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public ResourceLocation func_110775_a(EntityDeviantWitherSkeleton entity) {
        switch (entity.getMutation()) {
            case 1:
                return TEXTURES_SUPER_MUTATION_1;
            case 2:
                return TEXTURES_SUPER_MUTATION_2;
            case 3:
                return TEXTURES_SUPER_MUTATION_3;
            default:
                return TEXTURES_NORMAL;
        }
    }
    public void func_77043_a(EntityDeviantWitherSkeleton entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
