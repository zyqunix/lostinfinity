package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityStarfiend;
import xol.lostinfinity.mob.model.galaxy.ModelStarfiend;
public class RenderStarfiend extends RenderLiving<EntityStarfiend> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starfiend.png");
    public RenderStarfiend(RenderManager manager) {
        super(manager, new ModelStarfiend(), 1.0f);
    }
    public void func_77041_b(EntityStarfiend entitylivingbaseIn, float partialTickTime) {
        float scale = entitylivingbaseIn.getPhysicalScale();
        GlStateManager.func_179152_a(scale, scale, scale);
    }
    public void func_77043_a(EntityStarfiend entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
    public ResourceLocation func_110775_a(EntityStarfiend entity) {
        return TEXTURES;
    }
}
