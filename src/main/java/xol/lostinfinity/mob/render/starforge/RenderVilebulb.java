package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityVilebulb;
import xol.lostinfinity.mob.model.starforge.ModelVilebulb;
public class RenderVilebulb extends RenderLiving<EntityVilebulb> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/vilebulb.png");
    public static final ResourceLocation TEXTURES_EXPLODING = new ResourceLocation("lostinfinity:textures/entity/starforge/vilebulb_exploding.png");
    public RenderVilebulb(RenderManager manager) {
        super(manager, new ModelVilebulb(), 0.5f);
    }
    public void func_77041_b(EntityVilebulb entity, float partialTickTime) {
        GlStateManager.func_179152_a(0.5f, 0.5f, 0.5f);
    }
    public ResourceLocation func_110775_a(EntityVilebulb entity) {
        if (entity.isVolatile()) {
            if (entity.field_70173_aa % 4 < 2) {
                return TEXTURES;
            }
            return TEXTURES_EXPLODING;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntityVilebulb entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
