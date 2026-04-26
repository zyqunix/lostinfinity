package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityTempTentacle;
import xol.lostinfinity.mob.model.boss.ModelCryonus;
public class RenderTempTentacle extends RenderLiving<EntityTempTentacle> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/cryonus.png");
    public RenderTempTentacle(RenderManager manager) {
        super(manager, new ModelCryonus(), 0.5f);
    }
    public void func_77041_b(EntityTempTentacle entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityTempTentacle entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTempTentacle entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
