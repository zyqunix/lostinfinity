package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTurret;
import xol.lostinfinity.mob.model.boss.ModelRestorationCrystal;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuTurret.class */
public class RenderCthulhuTurret extends RenderLiving<EntityCthulhuTurret> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/turret.png");

    public RenderCthulhuTurret(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRestorationCrystal(), 2.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuTurret entity) {
        return TEXTURE;
    }
}
