package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntitySightwalker;
import xol.lostinfinity.mob.model.starforge.ModelSightwalker;
public class RenderSightwalker extends RenderLiving<EntitySightwalker> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/sightwalker.png");
    public RenderSightwalker(RenderManager manager) {
        super(manager, new ModelSightwalker(), 0.5f);
    }
    public void func_77041_b(EntitySightwalker entity, float partialTickTime) {
        float scl = entity.getMyScale();
        GlStateManager.func_179152_a(scl, scl, scl);
    }
    public ResourceLocation func_110775_a(EntitySightwalker entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySightwalker entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
