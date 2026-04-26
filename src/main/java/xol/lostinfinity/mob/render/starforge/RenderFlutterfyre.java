package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterfyre;
import xol.lostinfinity.mob.model.starforge.ModelFlutterfyre;
public class RenderFlutterfyre extends RenderLiving<EntityFlutterfyre> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/flutterfyre.png");
    public RenderFlutterfyre(RenderManager manager) {
        super(manager, new ModelFlutterfyre(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityFlutterfyre entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityFlutterfyre entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
