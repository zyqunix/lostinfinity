package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityStormBomb;
import xol.lostinfinity.mob.model.ModelSpecialBomb;
public class RenderStormBomb extends RenderLiving<EntityStormBomb> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/storm_bomb.png");
    public RenderStormBomb(RenderManager manager) {
        super(manager, new ModelSpecialBomb(), 0.25f);
    }
    public void func_77041_b(EntityStormBomb entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.field_70173_aa >= 40) {
            GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
        } else {
            float scl = 0.0375f * entitylivingbaseIn.field_70173_aa;
            GlStateManager.func_179152_a(scl, scl, scl);
        }
    }
    public ResourceLocation func_110775_a(EntityStormBomb entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityStormBomb entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
