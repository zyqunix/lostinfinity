package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityExplosect;
import xol.lostinfinity.mob.model.starforge.ModelExplosect;
public class RenderExplosect extends RenderLiving<EntityExplosect> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/explosect.png");
    public RenderExplosect(RenderManager manager) {
        super(manager, new ModelExplosect(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityExplosect entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityExplosect entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
