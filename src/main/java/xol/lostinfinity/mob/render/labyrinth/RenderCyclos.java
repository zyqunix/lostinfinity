package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityCyclos;
import xol.lostinfinity.mob.model.labyrinth.ModelCyclos;
public class RenderCyclos extends RenderLiving<EntityCyclos> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/cyclos.png");
    public RenderCyclos(RenderManager manager) {
        super(manager, new ModelCyclos(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityCyclos entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityCyclos entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
