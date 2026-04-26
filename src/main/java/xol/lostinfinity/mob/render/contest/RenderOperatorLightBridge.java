package xol.lostinfinity.mob.render.contest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorLightBridge;
import xol.lostinfinity.mob.model.contest.ModelContestNPC;
public class RenderOperatorLightBridge extends RenderLiving<EntityOperatorLightBridge> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/contest_npc_operator.png");
    public RenderOperatorLightBridge(RenderManager manager) {
        super(manager, new ModelContestNPC(), 1.0f);
    }
    public void func_77043_a(EntityOperatorLightBridge entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
    public ResourceLocation func_110775_a(EntityOperatorLightBridge entity) {
        return TEXTURES;
    }
}
