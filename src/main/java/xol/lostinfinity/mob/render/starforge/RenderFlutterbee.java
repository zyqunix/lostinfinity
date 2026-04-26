package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterbee;
import xol.lostinfinity.mob.model.starforge.ModelFlutterbee;
public class RenderFlutterbee extends RenderLiving<EntityFlutterbee> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/flutterbee.png");
    public RenderFlutterbee(RenderManager manager) {
        super(manager, new ModelFlutterbee(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityFlutterbee entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityFlutterbee entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
