package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityVectosect;
import xol.lostinfinity.mob.model.labyrinth.ModelVectosect;
public class RenderVectosect extends RenderLiving<EntityVectosect> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/vectosect.png");
    public RenderVectosect(RenderManager manager) {
        super(manager, new ModelVectosect(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityVectosect entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityVectosect entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
