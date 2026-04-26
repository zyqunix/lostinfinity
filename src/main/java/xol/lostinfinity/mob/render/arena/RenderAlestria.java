package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityAlestria;
import xol.lostinfinity.mob.model.boss.ModelAlestria;
public class RenderAlestria extends RenderLiving<EntityAlestria> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/alestria.png");
    public RenderAlestria(RenderManager manager) {
        super(manager, new ModelAlestria(), 0.5f);
    }
    public void func_77041_b(EntityAlestria entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityAlestria entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityAlestria entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
