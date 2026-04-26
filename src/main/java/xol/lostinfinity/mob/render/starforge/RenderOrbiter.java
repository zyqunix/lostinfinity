package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityOrbiter;
import xol.lostinfinity.mob.model.starforge.ModelOrbiter;
public class RenderOrbiter extends RenderLiving<EntityOrbiter> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/orbiter.png");
    public RenderOrbiter(RenderManager manager) {
        super(manager, new ModelOrbiter(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityOrbiter entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityOrbiter entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
