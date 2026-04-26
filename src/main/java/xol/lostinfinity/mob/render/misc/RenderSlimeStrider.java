package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.misc.EntitySlimeStrider;
import xol.lostinfinity.mob.model.ModelSlimeStrider;
public class RenderSlimeStrider extends RenderLiving<EntitySlimeStrider> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/slimestrider.png");
    public RenderSlimeStrider(RenderManager manager) {
        super(manager, new ModelSlimeStrider(), 0.5f);
    }
    public void func_77041_b(EntitySlimeStrider entitylivingbaseIn, float partialTickTime) {
        float scl = 1.8f + (0.5f * MathHelper.func_76126_a(entitylivingbaseIn.field_70173_aa * 0.05f));
        GlStateManager.func_179152_a(scl, scl, scl);
    }
    public ResourceLocation func_110775_a(EntitySlimeStrider entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySlimeStrider entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
