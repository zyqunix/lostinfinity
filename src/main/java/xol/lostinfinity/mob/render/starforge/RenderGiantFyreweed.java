package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGiantFyreweed;
import xol.lostinfinity.mob.model.starforge.ModelGiantFyreweed;
public class RenderGiantFyreweed extends RenderLiving<EntityGiantFyreweed> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/giantfyreweed.png");
    public RenderGiantFyreweed(RenderManager manager) {
        super(manager, new ModelGiantFyreweed(), 0.5f);
    }
    public void func_77041_b(EntityGiantFyreweed fyreweed, float partialTickTime) {
        if (fyreweed.field_70173_aa > 40) {
            GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
        } else {
            float scale = fyreweed.field_70173_aa * 0.075f;
            GlStateManager.func_179152_a(scale, scale, scale);
        }
    }
    public ResourceLocation func_110775_a(EntityGiantFyreweed entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGiantFyreweed entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
