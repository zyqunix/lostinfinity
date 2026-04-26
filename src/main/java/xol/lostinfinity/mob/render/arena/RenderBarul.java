package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityBarul;
import xol.lostinfinity.mob.model.boss.ModelBarul;
public class RenderBarul extends RenderLiving<EntityBarul> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/barul.png");
    public RenderBarul(RenderManager manager) {
        super(manager, new ModelBarul(), 0.5f);
    }
    public void func_77041_b(EntityBarul entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityBarul entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityBarul entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
