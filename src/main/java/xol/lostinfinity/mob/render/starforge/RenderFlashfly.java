package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityFlashfly;
import xol.lostinfinity.mob.model.starforge.ModelFlashfly;
public class RenderFlashfly extends RenderLiving<EntityFlashfly> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/flashfly.png");
    public static final ResourceLocation TEXTURES_LIT = new ResourceLocation("lostinfinity:textures/entity/starforge/flashfly_lit.png");
    public RenderFlashfly(RenderManager manager) {
        super(manager, new ModelFlashfly(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityFlashfly entity) {
        if (entity.field_70173_aa % 40 <= 2) {
            return TEXTURES_LIT;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntityFlashfly entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
