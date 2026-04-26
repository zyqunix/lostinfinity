package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityMortarCannon;
import xol.lostinfinity.mob.model.ModelCannon;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderMortarCannon.class */
public class RenderMortarCannon extends RenderLiving<EntityMortarCannon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/mortar_cannon.png");

    public RenderMortarCannon(RenderManager manager) {
        super(manager, new ModelCannon(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityMortarCannon entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityMortarCannon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
