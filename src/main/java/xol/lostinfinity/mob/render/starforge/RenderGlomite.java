package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGlomite;
import xol.lostinfinity.mob.model.starforge.ModelGlomite;
public class RenderGlomite extends RenderLiving<EntityGlomite> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/glomite.png");
    public RenderGlomite(RenderManager manager) {
        super(manager, new ModelGlomite(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGlomite entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGlomite entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
