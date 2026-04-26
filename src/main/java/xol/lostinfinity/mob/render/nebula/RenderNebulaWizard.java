package xol.lostinfinity.mob.render.nebula;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaWizard;
import xol.lostinfinity.mob.model.nebula.ModelNebulaWizard;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/nebula/RenderNebulaWizard.class */
public class RenderNebulaWizard extends RenderLiving<EntityNebulaWizard> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/nebula_wizard.png");

    public RenderNebulaWizard(RenderManager manager) {
        super(manager, new ModelNebulaWizard(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityNebulaWizard entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityNebulaWizard entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
