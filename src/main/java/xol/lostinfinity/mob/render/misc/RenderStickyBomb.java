package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityStickyBomb;
import xol.lostinfinity.mob.model.ModelSpecialBomb;
public class RenderStickyBomb extends RenderLiving<EntityStickyBomb> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/sticky_bomb.png");
    public RenderStickyBomb(RenderManager manager) {
        super(manager, new ModelSpecialBomb(), 0.5f);
    }
    public boolean func_177071_a(EntityStickyBomb livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }
    public ResourceLocation func_110775_a(EntityStickyBomb entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityStickyBomb entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
