package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySkybooster;
import xol.lostinfinity.mob.model.ModelSkybooster;
public class RenderSkybooster extends RenderLiving<EntitySkybooster> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/skybooster.png");
    public RenderSkybooster(RenderManager manager) {
        super(manager, new ModelSkybooster(), 0.5f);
    }
    public void func_77041_b(EntitySkybooster entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale());
    }
    public ResourceLocation func_110775_a(EntitySkybooster entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySkybooster entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
