package xol.lostinfinity.block.tileentity.render;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.crafting.BlockNicroniumInfuser;
import xol.lostinfinity.block.tileentity.TileEntityNicroniumInfuser;
import xol.lostinfinity.init.BlockInit;
public class RenderNicroniumInfuserEffect extends TileEntitySpecialRenderer<TileEntityNicroniumInfuser> {
    public static final ResourceLocation TEXTURE_NICRONIUM_RING = new ResourceLocation("lostinfinity:textures/particles/nicronium_ring.png");
    public void func_192841_a(TileEntityNicroniumInfuser te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        IBlockState state = te.func_145831_w().func_180495_p(te.func_174877_v());
        Block block = state.func_177230_c();
        if (block.equals(BlockInit.nicroniumInfuser) && state != ((BlockNicroniumInfuser) BlockInit.nicroniumInfuser).func_176203_a(1)) {
            return;
        }
        func_147499_a(TEXTURE_NICRONIUM_RING);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.3f);
        float growth = 0.1f + ((te.func_145831_w().func_82737_E() % 40) * 0.3f);
        float a = 1.0f - ((te.func_145831_w().func_82737_E() % 40) * 0.022224f);
        if (a <= 0.0f) {
            a = 0.0f;
        }
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, a);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        double xPos = x + 0.5d;
        double yPos = y + 0.10000000149011612d;
        double zPos = z + 0.5d;
        bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }
}
