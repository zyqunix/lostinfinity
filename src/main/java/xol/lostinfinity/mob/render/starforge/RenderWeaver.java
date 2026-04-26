package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityWeaver;
import xol.lostinfinity.mob.model.starforge.ModelWeaver;
public class RenderWeaver extends RenderLiving<EntityWeaver> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/weaver.png");
    public RenderWeaver(RenderManager manager) {
        super(manager, new ModelWeaver(), 0.5f);
    }
    public void func_77041_b(EntityWeaver entity, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityWeaver entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityWeaver entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
