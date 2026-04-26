package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityBlisterweed;
import xol.lostinfinity.mob.model.starforge.ModelBlisterweed;
public class RenderBlisterweed extends RenderLiving<EntityBlisterweed> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/blisterweed.png");
    public RenderBlisterweed(RenderManager manager) {
        super(manager, new ModelBlisterweed(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityBlisterweed entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityBlisterweed entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
