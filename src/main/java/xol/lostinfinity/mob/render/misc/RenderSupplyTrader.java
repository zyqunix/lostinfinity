package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
import xol.lostinfinity.mob.model.contest.ModelContestNPC;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderSupplyTrader.class */
public class RenderSupplyTrader extends RenderLiving<EntitySupplyTrader> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/supply_trader.png");

    public RenderSupplyTrader(RenderManager manager) {
        super(manager, new ModelContestNPC(), 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntitySupplyTrader entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntitySupplyTrader entity) {
        return TEXTURES;
    }
}
