package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityPhaser;
import xol.lostinfinity.mob.model.starforge.ModelPhaser;
public class RenderPhaser extends RenderLiving<EntityPhaser> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/phaser.png");
    public RenderPhaser(RenderManager manager) {
        super(manager, new ModelPhaser(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityPhaser entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityPhaser entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
