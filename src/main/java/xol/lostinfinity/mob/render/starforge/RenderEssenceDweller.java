package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceDweller;
import xol.lostinfinity.mob.model.starforge.ModelEssenceDweller;
public class RenderEssenceDweller extends RenderLiving<EntityEssenceDweller> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/essencedweller.png");
    public RenderEssenceDweller(RenderManager manager) {
        super(manager, new ModelEssenceDweller(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityEssenceDweller entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityEssenceDweller entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
