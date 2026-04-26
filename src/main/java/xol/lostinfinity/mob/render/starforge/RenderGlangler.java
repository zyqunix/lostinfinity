package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGlangler;
import xol.lostinfinity.mob.model.starforge.ModelGlangler;
public class RenderGlangler extends RenderLiving<EntityGlangler> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/glangler.png");
    public RenderGlangler(RenderManager manager) {
        super(manager, new ModelGlangler(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGlangler entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGlangler entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
