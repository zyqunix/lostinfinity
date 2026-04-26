package xol.lostinfinity.mob.render.prime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.prime.EntityZenon;
import xol.lostinfinity.mob.model.boss.ModelZenon;
public class RenderZenon extends RenderLiving<EntityZenon> {
    public static final ResourceLocation NORMAL = new ResourceLocation("lostinfinity:textures/entity/zenon.png");
    public static final ResourceLocation REFLECT = new ResourceLocation("lostinfinity:textures/entity/zenon_immune.png");
    public RenderZenon(RenderManager manager) {
        super(manager, new ModelZenon(), 0.5f);
    }
    public void func_77041_b(EntityZenon entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityZenon entity) {
        if (!entity.getReflect()) {
            return NORMAL;
        }
        return REFLECT;
    }
    public void func_77043_a(EntityZenon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
