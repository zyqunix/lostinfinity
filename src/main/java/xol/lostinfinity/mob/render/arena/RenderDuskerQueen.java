package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityDuskerQueen;
import xol.lostinfinity.mob.model.starforge.ModelDuskerQueen;
public class RenderDuskerQueen extends RenderLiving<EntityDuskerQueen> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/duskerqueen.png");
    public RenderDuskerQueen(RenderManager manager) {
        super(manager, new ModelDuskerQueen(), 0.5f);
    }
    public void func_77041_b(EntityDuskerQueen entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }
    public ResourceLocation func_110775_a(EntityDuskerQueen entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDuskerQueen entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
