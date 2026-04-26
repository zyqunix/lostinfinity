package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGrappler;
import xol.lostinfinity.mob.model.starforge.ModelGrappler;
public class RenderGrappler extends RenderLiving<EntityGrappler> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/grappler.png");
    public RenderGrappler(RenderManager manager) {
        super(manager, new ModelGrappler(), 0.5f);
    }
    public void func_77041_b(EntityGrappler entity, float partialTickTime) {
        GlStateManager.func_179152_a(1.8f, 1.8f, 1.8f);
    }
    public ResourceLocation func_110775_a(EntityGrappler entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGrappler entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
