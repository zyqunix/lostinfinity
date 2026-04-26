package xol.lostinfinity.gui.guis;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityCompressionTable;
import xol.lostinfinity.gui.containers.ContainerCompressionTable;
import xol.lostinfinity.util.Reference;
public class GuiCompressionTable extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/compression_table.png");
    private final ContainerCompressionTable table;
    private final World world;
    private final BlockPos pos;
    private final InventoryPlayer playerInventory;
    private final IInventory inv;
    public GuiCompressionTable(InventoryPlayer invPlayer, World worldIn, BlockPos pos, Block block, IInventory inv) {
        super(new ContainerCompressionTable(invPlayer, worldIn, pos, block, inv));
        this.pos = pos;
        this.world = worldIn;
        this.table = (ContainerCompressionTable) this.field_147002_h;
        this.playerInventory = invPlayer;
        this.inv = inv;
    }
    public void func_73866_w_() {
        super.func_73866_w_();
    }
    public void func_146281_b() {
        super.func_146281_b();
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
        this.field_146289_q.func_78276_b("Compression Table", 60, 6, 4210752);
        List<String> hoverText = new ArrayList<>();
        if (isInRect(this.field_147003_i + 76, this.field_147009_r + 35, 23, 16, mouseX, mouseY)) {
            hoverText.add("Compression Progress:");
            int percent = (int) (((TileEntityCompressionTable) this.world.func_175625_s(this.pos)).getProgressAsFraction() * 100.0d);
            hoverText.add(percent + "%");
        }
        if (!hoverText.isEmpty()) {
            drawHoveringText(hoverText, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
        }
        GlStateManager.func_179145_e();
    }
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + xSize && mouseY >= y && mouseY <= y + ySize;
    }
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        double compressionProgress = ((TileEntityCompressionTable) this.world.func_175625_s(this.pos)).getProgressAsFraction();
        func_73729_b(76, 35, 14, 176, (int) (compressionProgress * ((double) 23)), 16);
        TileEntityCompressionTable table = (TileEntityCompressionTable) this.world.func_175625_s(this.pos);
        if (table.canProgress()) {
            int pix = getCPRogressScaled(24);
            func_73729_b(this.field_147003_i + 76, this.field_147009_r + 20, this.field_146999_f, 0, pix + 1, 30);
        }
    }
    private int getCPRogressScaled(int pixels) {
        int i = this.inv.func_174887_a_(0);
        if (i != -1) {
            return (i * pixels) / 500;
        }
        return 0;
    }
}
