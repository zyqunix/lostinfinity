package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGlochipper;
import xol.lostinfinity.mob.model.starforge.ModelGlochipper;
public class RenderGlochipper extends RenderLiving<EntityGlochipper> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/glochipper.png");
    public RenderGlochipper(RenderManager manager) {
        super(manager, new ModelGlochipper(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGlochipper entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGlochipper entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
