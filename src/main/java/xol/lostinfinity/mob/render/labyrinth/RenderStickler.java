package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityStickler;
import xol.lostinfinity.mob.model.labyrinth.ModelStickler;
public class RenderStickler extends RenderLiving<EntityStickler> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/stickler.png");
    public RenderStickler(RenderManager manager) {
        super(manager, new ModelStickler(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityStickler entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityStickler entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
