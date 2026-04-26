package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityMirrorZombie;
import xol.lostinfinity.mob.model.ModelMirrorZombie;
public class RenderMirrorZombie extends RenderLiving<EntityMirrorZombie> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/mirrorzombie.png");
    public RenderMirrorZombie(RenderManager manager) {
        super(manager, new ModelMirrorZombie(), 1.0f);
    }
    public void func_77041_b(EntityMirrorZombie entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.field_70173_aa < 80) {
            GlStateManager.func_179152_a(1.0f + (0.025f * entitylivingbaseIn.field_70173_aa), 1.0f + (0.025f * entitylivingbaseIn.field_70173_aa), 1.0f + (0.025f * entitylivingbaseIn.field_70173_aa));
        } else {
            GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
        }
    }
    public ResourceLocation func_110775_a(EntityMirrorZombie entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityMirrorZombie entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
