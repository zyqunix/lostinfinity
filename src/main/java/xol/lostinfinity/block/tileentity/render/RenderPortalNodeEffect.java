package xol.lostinfinity.block.tileentity.render;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.block.activator.BlockPortalNode;
import xol.lostinfinity.block.tileentity.TileEntityPortalNexus;
import xol.lostinfinity.block.tileentity.TileEntityPortalNode;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/render/RenderPortalNodeEffect.class */
public class RenderPortalNodeEffect extends TileEntitySpecialRenderer<TileEntityPortalNode> {
    public static final ResourceLocation TEXTURE_PORTAL_BEAM = new ResourceLocation("lostinfinity:textures/particles/portal_beam.png");

    /* JADX INFO: renamed from: render, reason: merged with bridge method [inline-methods] */
    public void func_192841_a(TileEntityPortalNode te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        BlockPos nexusPos;
        TileEntityPortalNexus nexus;
        IBlockState state = te.func_145831_w().func_180495_p(te.func_174877_v());
        Block block = state.func_177230_c();
        if ((block.equals(BlockInit.portalNode) && state != ((BlockPortalNode) BlockInit.portalNode).func_176203_a(1)) || (nexusPos = te.getNexusPos()) == null || (nexus = (TileEntityPortalNexus) te.func_145831_w().func_175625_s(nexusPos)) == null) {
            return;
        }
        Vec3d nexusVec = new Vec3d(((double) te.getNexusPos().func_177958_n()) + 0.5d, te.getNexusPos().func_177956_o(), ((double) te.getNexusPos().func_177952_p()) + 0.5d);
        Vec3d pos = new Vec3d(te.func_174877_v().func_177958_n(), te.func_174877_v().func_177956_o(), te.func_174877_v().func_177952_p());
        func_147499_a(TEXTURE_PORTAL_BEAM);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.3f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        double dist = pos.func_72438_d(nexusVec);
        Vec3d dir = nexusVec.func_178788_d(pos).func_72432_b();
        double growth = nexus.getGrowth();
        for (int i = 0; i < ((int) Math.floor((dist - (growth / 2.0d)) / ((double) 1.0f))); i++) {
            double xPos = x + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
            double yPos = y + (dir.field_72448_b * ((double) i) * ((double) 1.0f));
            double zPos = z + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
            bufferbuilder.func_181662_b(xPos, yPos + ((double) 2.0f), zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + ((double) 2.0f) + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }
}
