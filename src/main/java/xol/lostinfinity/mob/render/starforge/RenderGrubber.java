package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGrubber;
import xol.lostinfinity.mob.model.starforge.ModelGrubber;
public class RenderGrubber extends RenderLiving<EntityGrubber> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/grubber.png");
    public RenderGrubber(RenderManager manager) {
        super(manager, new ModelGrubber(), 0.5f);
    }
    public void func_77041_b(EntityGrubber entity, float partialTickTime) {
        GlStateManager.func_179152_a(0.5f, 0.5f, 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGrubber entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGrubber entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
