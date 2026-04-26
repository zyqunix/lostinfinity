package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityDarrio;
import xol.lostinfinity.mob.model.boss.ModelDarrio;
public class RenderDarrio extends RenderLiving<EntityDarrio> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/darrio.png");
    public RenderDarrio(RenderManager manager) {
        super(manager, new ModelDarrio(), 0.5f);
    }
    public void func_77041_b(EntityDarrio entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityDarrio entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDarrio entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
