package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityDrippler;
import xol.lostinfinity.mob.model.starforge.ModelDrippler;
public class RenderDrippler extends RenderLiving<EntityDrippler> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/drippler.png");
    public RenderDrippler(RenderManager manager) {
        super(manager, new ModelDrippler(), 0.5f);
    }
    public void func_77041_b(EntityDrippler entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.25f, 1.25f, 1.25f);
    }
    public ResourceLocation func_110775_a(EntityDrippler entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDrippler entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
