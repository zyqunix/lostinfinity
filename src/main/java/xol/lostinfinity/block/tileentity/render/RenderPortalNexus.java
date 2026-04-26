package xol.lostinfinity.block.tileentity.render;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.block.tileentity.TileEntityPortalNexus;
import xol.lostinfinity.init.BlockInit;
public class RenderPortalNexus extends TileEntitySpecialRenderer<TileEntityPortalNexus> {
    public static final ResourceLocation TEXTURE_SUMMONING_PORTAL = new ResourceLocation("lostinfinity:textures/particles/summoning_portal.png");
    public void func_192841_a(TileEntityPortalNexus te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        IBlockState state = te.func_145831_w().func_180495_p(te.func_174877_v());
        Block block = state.func_177230_c();
        if (block.equals(BlockInit.portalNode) && state != BlockInit.portalNode.func_176203_a(1)) {
            return;
        }
        func_147499_a(TEXTURE_SUMMONING_PORTAL);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.3f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        double growth = te.getGrowth();
        float rotation = te.getRotation();
        double xPos = x + 0.5d;
        double yPos = y + 0.10000000149011612d;
        double zPos = z + 0.5d;
        Vec3d dirRight = new Vec3d(1.0d, 0.0d, 0.0d).func_178785_b(rotation);
        Vec3d dirForward = new Vec3d(0.0d, 0.0d, 1.0d).func_178785_b(rotation);
        bufferbuilder.func_181662_b(((-0.5d) * growth * dirRight.field_72450_a) + (0.5d * growth * dirForward.field_72450_a) + xPos, 1.0d + yPos, ((-0.5d) * growth * dirRight.field_72449_c) + (0.5d * growth * dirForward.field_72449_c) + zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(((((-0.5d) * growth) * dirRight.field_72450_a) - ((0.5d * growth) * dirForward.field_72450_a)) + xPos, 1.0d + yPos, ((((-0.5d) * growth) * dirRight.field_72449_c) - ((0.5d * growth) * dirForward.field_72449_c)) + zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((((0.5d * growth) * dirRight.field_72450_a) - ((0.5d * growth) * dirForward.field_72450_a)) + xPos, 1.0d + yPos, (((0.5d * growth) * dirRight.field_72449_c) - ((0.5d * growth) * dirForward.field_72449_c)) + zPos).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((0.5d * growth * dirRight.field_72450_a) + (0.5d * growth * dirForward.field_72450_a) + xPos, 1.0d + yPos, (0.5d * growth * dirRight.field_72449_c) + (0.5d * growth * dirForward.field_72449_c) + zPos).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }
}
