package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityAcidback;
import xol.lostinfinity.mob.model.starforge.ModelAcidback;
public class RenderAcidback extends RenderLiving<EntityAcidback> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/acidback.png");
    public RenderAcidback(RenderManager manager) {
        super(manager, new ModelAcidback(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityAcidback entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityAcidback entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
