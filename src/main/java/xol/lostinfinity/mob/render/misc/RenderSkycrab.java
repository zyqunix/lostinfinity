package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySkycrab;
import xol.lostinfinity.mob.model.ModelSkycrab;
public class RenderSkycrab extends RenderLiving<EntitySkycrab> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/skycrab.png");
    public RenderSkycrab(RenderManager manager) {
        super(manager, new ModelSkycrab(), 0.5f);
    }
    public void func_77041_b(EntitySkycrab entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.25f, 4.25f, 4.25f);
    }
    public ResourceLocation func_110775_a(EntitySkycrab entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySkycrab entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
