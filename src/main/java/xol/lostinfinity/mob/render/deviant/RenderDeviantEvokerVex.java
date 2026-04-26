package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEvokerVex;
import xol.lostinfinity.mob.model.deviant.ModelDeviantVex;
public class RenderDeviantEvokerVex extends RenderLiving<EntityDeviantEvokerVex> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantvex.png");
    public RenderDeviantEvokerVex(RenderManager manager) {
        super(manager, new ModelDeviantVex(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityDeviantEvokerVex entity) {
        return TEXTURES_NORMAL;
    }
    public void func_77043_a(EntityDeviantEvokerVex entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
