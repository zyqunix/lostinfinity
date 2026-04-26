package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityTitanopod;
import xol.lostinfinity.mob.model.starforge.ModelTitanopod;
public class RenderTitanopod extends RenderLiving<EntityTitanopod> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/titanopod.png");
    public RenderTitanopod(RenderManager manager) {
        super(manager, new ModelTitanopod(), 0.5f);
    }
    public void func_77041_b(EntityTitanopod entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityTitanopod entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTitanopod entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
