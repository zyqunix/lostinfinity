package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityBomberBomb;
import xol.lostinfinity.mob.model.ModelSpecialBomb;
public class RenderBomberBomb extends RenderLiving<EntityBomberBomb> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/bombers_bomb.png");
    public RenderBomberBomb(RenderManager manager) {
        super(manager, new ModelSpecialBomb(), 0.5f);
    }
    public void func_77041_b(EntityBomberBomb entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityBomberBomb entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityBomberBomb entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
