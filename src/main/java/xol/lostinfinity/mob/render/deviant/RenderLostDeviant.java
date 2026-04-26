package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityLostDeviant;
import xol.lostinfinity.mob.model.ModelLostDeviant;
public class RenderLostDeviant extends RenderLiving<EntityLostDeviant> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/lostdeviant.png");
    public RenderLostDeviant(RenderManager manager) {
        super(manager, new ModelLostDeviant(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityLostDeviant entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityLostDeviant entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
