package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityArash;
import xol.lostinfinity.mob.model.boss.ModelArash;
public class RenderArash extends RenderLiving<EntityArash> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/arash.png");
    public RenderArash(RenderManager manager) {
        super(manager, new ModelArash(), 0.5f);
    }
    public void func_77041_b(EntityArash entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityArash entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityArash entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
