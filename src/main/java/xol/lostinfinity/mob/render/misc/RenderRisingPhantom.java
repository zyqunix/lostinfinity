package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityRisingPhantom;
import xol.lostinfinity.mob.model.ModelRisingPhantom;
public class RenderRisingPhantom extends RenderLiving<EntityRisingPhantom> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/rising_phantom.png");
    public RenderRisingPhantom(RenderManager manager) {
        super(manager, new ModelRisingPhantom(), 0.0f);
    }
    public ResourceLocation func_110775_a(EntityRisingPhantom entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityRisingPhantom entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
