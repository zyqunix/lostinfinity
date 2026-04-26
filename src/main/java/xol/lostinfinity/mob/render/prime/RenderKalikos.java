package xol.lostinfinity.mob.render.prime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.prime.EntityKalikos;
import xol.lostinfinity.mob.model.boss.ModelKalikos;
public class RenderKalikos extends RenderLiving<EntityKalikos> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/kalikos.png");
    public RenderKalikos(RenderManager manager) {
        super(manager, new ModelKalikos(), 0.5f);
    }
    public void func_77041_b(EntityKalikos entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public ResourceLocation func_110775_a(EntityKalikos entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityKalikos entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
