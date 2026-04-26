package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityTentaclon;
import xol.lostinfinity.mob.model.starforge.ModelTentaclon;
public class RenderTentaclon extends RenderLiving<EntityTentaclon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/tentaclon.png");
    public RenderTentaclon(RenderManager manager) {
        super(manager, new ModelTentaclon(), 0.5f);
    }
    public void func_77041_b(EntityTentaclon entity, float partialTickTime) {
        int ticks = entity.field_70173_aa;
        if (entity.field_70173_aa < 40) {
            GlStateManager.func_179152_a(ticks * 0.05f, ticks * 0.05f, ticks * 0.05f);
        } else {
            GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
        }
    }
    public ResourceLocation func_110775_a(EntityTentaclon entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTentaclon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
