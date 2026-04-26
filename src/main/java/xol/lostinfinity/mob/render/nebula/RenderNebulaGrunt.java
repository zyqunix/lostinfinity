package xol.lostinfinity.mob.render.nebula;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGrunt;
import xol.lostinfinity.mob.model.nebula.ModelNebulaGrunt;
public class RenderNebulaGrunt extends RenderLiving<EntityNebulaGrunt> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/nebula_grunt.png");
    public RenderNebulaGrunt(RenderManager manager) {
        super(manager, new ModelNebulaGrunt(), 0.0f);
    }
    public ResourceLocation func_110775_a(EntityNebulaGrunt entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityNebulaGrunt entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
