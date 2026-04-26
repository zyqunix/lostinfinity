package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityScreacher;
import xol.lostinfinity.mob.model.starforge.ModelScreacher;
public class RenderScreacher extends RenderLiving<EntityScreacher> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/screacher.png");
    public RenderScreacher(RenderManager manager) {
        super(manager, new ModelScreacher(), 0.5f);
    }
    public void func_77041_b(EntityScreacher entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.5f, 3.5f, 3.5f);
    }
    public ResourceLocation func_110775_a(EntityScreacher entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityScreacher entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
