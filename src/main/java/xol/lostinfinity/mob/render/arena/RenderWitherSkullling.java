package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityWitherSkullling;
import xol.lostinfinity.mob.model.boss.ModelWitherSkullling;
public class RenderWitherSkullling extends RenderLiving<EntityWitherSkullling> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/wither_skullling.png");
    public RenderWitherSkullling(RenderManager manager) {
        super(manager, new ModelWitherSkullling(), 0.5f);
    }
    public void func_77041_b(EntityWitherSkullling entity, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public ResourceLocation func_110775_a(EntityWitherSkullling entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityWitherSkullling entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
