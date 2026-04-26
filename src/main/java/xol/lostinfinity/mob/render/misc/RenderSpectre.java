package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySpectre;
import xol.lostinfinity.mob.model.ModelSpectre;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderSpectre.class */
public class RenderSpectre extends RenderLiving<EntitySpectre> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/spectre.png");
    public static final ResourceLocation TEXTURES_PRIME = new ResourceLocation("lostinfinity:textures/entity/spectre_prime.png");

    public RenderSpectre(RenderManager manager) {
        super(manager, new ModelSpectre(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntitySpectre entity) {
        if (entity.isPrime()) {
            return TEXTURES_PRIME;
        }
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntitySpectre entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
