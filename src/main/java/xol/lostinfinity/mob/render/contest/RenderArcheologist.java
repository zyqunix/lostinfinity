package xol.lostinfinity.mob.render.contest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.trader.EntityArcheologist;
import xol.lostinfinity.mob.model.contest.ModelArcheologist;
public class RenderArcheologist extends RenderLiving<EntityArcheologist> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/archeologist.png");
    public RenderArcheologist(RenderManager manager) {
        super(manager, new ModelArcheologist(), 1.0f);
    }
    public void func_77043_a(EntityArcheologist entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
    public ResourceLocation func_110775_a(EntityArcheologist entity) {
        return TEXTURES;
    }
}
