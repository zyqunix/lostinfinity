package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityLabWarrior;
import xol.lostinfinity.mob.model.labyrinth.ModelLabWarrior;
public class RenderLabWarrior extends RenderLiving<EntityLabWarrior> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/labwarrior.png");
    public RenderLabWarrior(RenderManager manager) {
        super(manager, new ModelLabWarrior(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityLabWarrior entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityLabWarrior entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
