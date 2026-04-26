package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityNuxuro;
import xol.lostinfinity.mob.model.boss.ModelNuxuro;
public class RenderNuxuro extends RenderLiving<EntityNuxuro> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/nuxuro.png");
    public RenderNuxuro(RenderManager manager) {
        super(manager, new ModelNuxuro(), 0.5f);
    }
    public void func_77041_b(EntityNuxuro entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityNuxuro entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityNuxuro entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
