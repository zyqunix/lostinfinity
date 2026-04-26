package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityMultiverseGhost;
import xol.lostinfinity.mob.layer.LayerHeldItemOffset;
import xol.lostinfinity.mob.model.ModelMultiverseGhost;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderMultiverseGhost.class */
public class RenderMultiverseGhost extends RenderLiving<EntityMultiverseGhost> {
    public RenderMultiverseGhost(RenderManager manager) {
        super(manager, new ModelMultiverseGhost(), 0.5f);
        func_177094_a(new LayerHeldItemOffset(this) { // from class: xol.lostinfinity.mob.render.misc.RenderMultiverseGhost.1
            @Override // xol.lostinfinity.mob.layer.LayerHeldItemOffset
            protected void offsetItem(EntityLivingBase entityLivingBase, ItemStack itemStack, ItemCameraTransforms.TransformType transformType, EnumHandSide handSide) {
                EntityMultiverseGhost ghost = (EntityMultiverseGhost) entityLivingBase;
                ghost.getPose().offsetItem();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityMultiverseGhost entity) {
        return entity.getSkinForMyCopy();
    }
}
