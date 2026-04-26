package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityThunderBomb;
import xol.lostinfinity.mob.model.ModelSpecialBomb;
public class RenderThunderBomb extends RenderLiving<EntityThunderBomb> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/thunder_bomb.png");
    public RenderThunderBomb(RenderManager manager) {
        super(manager, new ModelSpecialBomb(), 0.5f);
    }
    public void func_77041_b(EntityThunderBomb entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.field_70173_aa >= 40) {
            GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
        } else {
            float scl = 0.0375f * entitylivingbaseIn.field_70173_aa;
            GlStateManager.func_179152_a(scl, scl, scl);
        }
    }
    public ResourceLocation func_110775_a(EntityThunderBomb entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityThunderBomb entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
