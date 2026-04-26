package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityClusterCannon;
import xol.lostinfinity.mob.model.ModelCannon;
public class RenderClusterCannon extends RenderLiving<EntityClusterCannon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/cluster_cannon.png");
    public RenderClusterCannon(RenderManager manager) {
        super(manager, new ModelCannon(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityClusterCannon entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityClusterCannon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
