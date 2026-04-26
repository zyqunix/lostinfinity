package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityShimmer;
import xol.lostinfinity.mob.model.starforge.ModelShimmer;
public class RenderShimmer extends RenderLiving<EntityShimmer> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/shimmer.png");
    public RenderShimmer(RenderManager manager) {
        super(manager, new ModelShimmer(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityShimmer entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityShimmer entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
