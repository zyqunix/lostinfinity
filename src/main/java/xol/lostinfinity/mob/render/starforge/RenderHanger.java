package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityHanger;
import xol.lostinfinity.mob.model.starforge.ModelHanger;
public class RenderHanger extends RenderLiving<EntityHanger> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/hanger.png");
    public RenderHanger(RenderManager manager) {
        super(manager, new ModelHanger(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityHanger entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityHanger entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
