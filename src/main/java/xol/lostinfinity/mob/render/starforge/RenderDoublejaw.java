package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityDoublejaw;
import xol.lostinfinity.mob.model.starforge.ModelDoublejaw;
public class RenderDoublejaw extends RenderLiving<EntityDoublejaw> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/doublejaw.png");
    public RenderDoublejaw(RenderManager manager) {
        super(manager, new ModelDoublejaw(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityDoublejaw entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDoublejaw entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
