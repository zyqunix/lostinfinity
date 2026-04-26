package xol.lostinfinity.mob.render.nebula;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGiant;
import xol.lostinfinity.mob.model.nebula.ModelNebulaGiant;
public class RenderNebulaGiant extends RenderLiving<EntityNebulaGiant> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/nebula_giant.png");
    public static final ResourceLocation TEXTURES_ALTERNATE = new ResourceLocation("lostinfinity:textures/entity/nebula_giant_alternate.png");
    public RenderNebulaGiant(RenderManager manager) {
        super(manager, new ModelNebulaGiant(), 0.0f);
    }
    public void func_77041_b(EntityNebulaGiant entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityNebulaGiant entity) {
        if (entity.field_70173_aa % 4 < 2) {
            return TEXTURES_ALTERNATE;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntityNebulaGiant entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
