package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySandAttack;
import xol.lostinfinity.mob.model.ModelSandAttack;
public class RenderSandAttack extends RenderLiving<EntitySandAttack> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/sand_attack.png");
    public RenderSandAttack(RenderManager manager) {
        super(manager, new ModelSandAttack(), 0.5f);
    }
    public void func_77041_b(EntitySandAttack sa, float partialTickTime) {
        GlStateManager.func_179152_a(sa.getCubeSize(), sa.getCubeSize(), sa.getCubeSize());
    }
    public ResourceLocation func_110775_a(EntitySandAttack entity) {
        return TEXTURES;
    }
}
