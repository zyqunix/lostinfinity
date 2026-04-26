package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityDroidBoss;
import xol.lostinfinity.mob.model.ModelDroid;
public class RenderDroidBoss extends RenderLiving<EntityDroidBoss> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/droid_mk1_aggressive.png");
    private float scale;
    public RenderDroidBoss(RenderManager manager) {
        super(manager, new ModelDroid(), 0.5f);
        this.scale = 1.5f;
    }
    public void func_77041_b(EntityDroidBoss entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityDroidBoss entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDroidBoss entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
