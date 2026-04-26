package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityRavager;
import xol.lostinfinity.mob.model.starforge.ModelRavager;
public class RenderRavager extends RenderLiving<EntityRavager> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/ravager.png");
    public RenderRavager(RenderManager manager) {
        super(manager, new ModelRavager(), 0.5f);
    }
    public void func_77041_b(EntityRavager entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public ResourceLocation func_110775_a(EntityRavager entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityRavager entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
