package xol.lostinfinity.gui.guis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityShipmentFiller;
import xol.lostinfinity.gui.containers.ContainerShipmentFiller;
import xol.lostinfinity.item.misc.ItemShipmentBox;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.client.GuiUtil;
public class GuiShipmentFiller extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/shipment_filler.png");
    private final InventoryPlayer player;
    private final TileEntityShipmentFiller tileEntity;
    public GuiShipmentFiller(InventoryPlayer invPlayer, TileEntityShipmentFiller tileEntity) {
        super(new ContainerShipmentFiller(invPlayer, tileEntity));
        this.player = invPlayer;
        this.tileEntity = tileEntity;
    }
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (mouseX <= this.field_147003_i + 38 || mouseX >= this.field_147003_i + 167 || mouseY <= this.field_147009_r + 6 || mouseY >= this.field_147009_r + 78) {
            func_191948_b(mouseX, mouseY);
        }
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        if (!this.tileEntity.func_70301_a(1).func_190926_b()) {
            ItemStack box = this.tileEntity.func_70301_a(1);
            if (!box.func_77942_o()) {
                box.func_77982_d(new NBTTagCompound());
            }
            int progressHeight = GuiUtil.getCurrentBarSize(73, box.func_77978_p().func_74762_e("weight"), 100, 0);
            int progressX = this.field_147003_i + 27;
            int progressY = this.field_147009_r + 6 + (73 - progressHeight);
            func_73729_b(progressX, progressY, 0, 166, 8, progressHeight);
        }
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        int progressX = this.field_147003_i + 27;
        int progressY = this.field_147009_r + 6;
        if (!this.tileEntity.func_70301_a(1).func_190926_b() && GuiUtil.isInRect(progressX, progressY, 8, 73, mouseX, mouseY)) {
            func_146279_a("Weight: " + this.tileEntity.func_70301_a(1).func_77978_p().func_74762_e("weight") + "/100 lbs", mouseX - this.field_147003_i, mouseY - this.field_147009_r);
        }
        Slot hoveredSlot = getSlotUnderMouse();
        if (hoveredSlot != null) {
            ItemStack hoveredStack = hoveredSlot.func_75211_c();
            int itemWeight = ItemShipmentBox.getItemWeight(hoveredStack.func_77973_b());
            if (itemWeight > 0 && mouseX >= this.field_147003_i + 38 && mouseX <= this.field_147003_i + 167 && mouseY >= this.field_147009_r + 6 && mouseY <= this.field_147009_r + 78) {
                func_146283_a(GuiUtil.getHoverText(hoveredStack.func_82833_r(), "Weight: " + itemWeight + " lbs"), mouseX - this.field_147003_i, mouseY - this.field_147009_r);
            }
        }
    }
}
