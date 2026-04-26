package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityLurcher;
import xol.lostinfinity.mob.model.starforge.ModelLurcher;
public class RenderLurcher extends RenderLiving<EntityLurcher> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/lurcher.png");
    public RenderLurcher(RenderManager manager) {
        super(manager, new ModelLurcher(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityLurcher entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityLurcher entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
