package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityOzor;
import xol.lostinfinity.mob.model.boss.ModelOzor;
public class RenderOzor extends RenderLiving<EntityOzor> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/ozor.png");
    public RenderOzor(RenderManager manager) {
        super(manager, new ModelOzor(), 0.5f);
    }
    public void func_77041_b(EntityOzor entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(5.0f, 5.0f, 5.0f);
    }
    public ResourceLocation func_110775_a(EntityOzor entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityOzor entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
