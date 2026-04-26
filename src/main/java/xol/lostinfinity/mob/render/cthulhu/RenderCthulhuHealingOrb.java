package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuHealingOrb;
import xol.lostinfinity.mob.model.cthulhu.ModelCthulhuHealingOrb;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuHealingOrb.class */
public class RenderCthulhuHealingOrb extends RenderLiving<EntityCthulhuHealingOrb> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/entity/cthulhu/healing_orb.png");

    public RenderCthulhuHealingOrb(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCthulhuHealingOrb(), 0.0f);
    }

    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuHealingOrb entity) {
        return TEXTURE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityCthulhuHealingOrb entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(5.0f, 5.0f, 5.0f);
    }
}
