package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGnawer;
import xol.lostinfinity.mob.model.starforge.ModelGnawer;
public class RenderGnawer extends RenderLiving<EntityGnawer> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/gnawer.png");
    public RenderGnawer(RenderManager manager) {
        super(manager, new ModelGnawer(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGnawer entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGnawer entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
