package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityUrogo;
import xol.lostinfinity.mob.model.boss.ModelUrogo;
public class RenderUrogo extends RenderLiving<EntityUrogo> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/urogo.png");
    public RenderUrogo(RenderManager manager) {
        super(manager, new ModelUrogo(), 0.5f);
    }
    public void func_77041_b(EntityUrogo entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityUrogo entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityUrogo entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
