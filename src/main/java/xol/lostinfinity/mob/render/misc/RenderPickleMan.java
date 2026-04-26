package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.mob.model.ModelPickleMan;
public class RenderPickleMan extends RenderLiving<EntityPickleMan> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/pickleman.png");
    public RenderPickleMan(RenderManager manager) {
        super(manager, new ModelPickleMan(), 0.5f);
    }
    public void func_77041_b(EntityPickleMan entity, float partialTickTime) {
        int ticks = entity.field_70173_aa;
        if (entity.field_70173_aa < 20) {
            GlStateManager.func_179152_a(ticks * 0.05f, ticks * 0.05f, ticks * 0.05f);
        } else {
            float pickleScale = entity.getMyStepScale();
            GlStateManager.func_179152_a(pickleScale, pickleScale, pickleScale);
        }
    }
    public ResourceLocation func_110775_a(EntityPickleMan entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityPickleMan entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
