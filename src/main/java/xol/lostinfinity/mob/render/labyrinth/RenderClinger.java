package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityClinger;
import xol.lostinfinity.mob.model.labyrinth.ModelClinger;
public class RenderClinger extends RenderLiving<EntityClinger> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/clinger.png");
    public RenderClinger(RenderManager manager) {
        super(manager, new ModelClinger(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityClinger entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityClinger entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
