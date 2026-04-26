package xol.lostinfinity.gui.guis;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityWeldingChamber;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketWeldingChamber;
import xol.lostinfinity.gui.containers.ContainerWeldingChamber;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.Reference;
public class GuiWeldingChamber extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/welding_chamber.png");
    private final TileEntityWeldingChamber tileEntity;
    private final InventoryPlayer invPlayer;
    public GuiWeldingChamber(InventoryPlayer invPlayer, TileEntityWeldingChamber tileEntity) {
        super(new ContainerWeldingChamber(invPlayer, tileEntity));
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("Welding Chamber", 51, 6, 4210752);
        if (isInRect(this.field_147003_i + 38, this.field_147009_r + 27, 98, 14, mouseX, mouseY)) {
            List<String> hoverText = new ArrayList<>();
            int percent = smeltProgressToPercent();
            hoverText.add("Welding Progress:");
            hoverText.add(percent + "%");
            drawHoveringText(hoverText, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
        }
        if (isInRect(this.field_147003_i + 38, this.field_147009_r + 45, 98, 8, mouseX, mouseY)) {
            List<String> hoverText2 = new ArrayList<>();
            int level = this.tileEntity.func_174887_a_(2);
            hoverText2.add("Acetylene Level:");
            hoverText2.add(level + "/7");
            drawHoveringText(hoverText2, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
        }
        if (isInRect(this.field_147003_i + 38, this.field_147009_r + 15, 98, 8, mouseX, mouseY)) {
            List<String> hoverText3 = new ArrayList<>();
            int heat = this.tileEntity.func_174887_a_(1);
            hoverText3.add("Heat:");
            hoverText3.add(heat + "C");
            drawHoveringText(hoverText3, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
        }
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        int acetyleneX = this.field_147003_i + 38;
        int acetyleneY = this.field_147009_r + 45;
        int acetyleneLevel = this.tileEntity.func_174887_a_(2);
        int acetyleneLevelWidth = (98 * acetyleneLevel) / 7;
        int greenButtonX = this.field_147003_i + 119;
        int greenButtonY = this.field_147009_r + 57;
        int redButtonX = this.field_147003_i + 39;
        int redButtonY = this.field_147009_r + 57;
        int heatX = this.field_147003_i + 38;
        int heatY = this.field_147009_r + 15;
        int heatProgressWidth = (heatProgressPercent() * 98) / 100;
        int progressX = this.field_147003_i + 38;
        int progressY = this.field_147009_r + 27;
        int currentProgressWidth = (smeltProgressToPercent() * 98) / 100;
        func_73729_b(acetyleneX, acetyleneY, 0, 218, acetyleneLevelWidth, 8);
        if (isInRect(greenButtonX, greenButtonY, 17, 14, mouseX, mouseY)) {
            func_73729_b(greenButtonX, greenButtonY, 18, 228, 17, 14);
        }
        if (isInRect(redButtonX, redButtonY, 17, 14, mouseX, mouseY)) {
            func_73729_b(redButtonX, redButtonY, 18, 242, 17, 14);
        }
        func_73729_b(progressX, progressY, 0, 182, currentProgressWidth, 14);
        func_73729_b(heatX, heatY, 0, 208, heatProgressWidth, 8);
    }
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        int greenButtonX = this.field_147003_i + 119;
        int greenButtonY = this.field_147009_r + 57;
        int redButtonX = this.field_147003_i + 39;
        int redButtonY = this.field_147009_r + 57;
        if (isInRect(greenButtonX, greenButtonY, 17, 14, mouseX, mouseY)) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketWeldingChamber(this.tileEntity.func_174877_v(), this.tileEntity.func_174887_a_(2) + 1));
            this.invPlayer.field_70458_d.func_184185_a(SoundInit.GENERIC_UI_5, 1.0f, 1.0f);
        }
        if (isInRect(redButtonX, redButtonY, 17, 14, mouseX, mouseY)) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketWeldingChamber(this.tileEntity.func_174877_v(), this.tileEntity.func_174887_a_(2) - 1));
            this.invPlayer.field_70458_d.func_184185_a(SoundInit.GENERIC_UI_5, 1.0f, 1.0f);
        }
    }
    private boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + xSize && mouseY >= y && mouseY <= y + ySize;
    }
    public int smeltProgressToPercent() {
        if (this.tileEntity.func_174887_a_(0) == 0) {
            return 0;
        }
        return (this.tileEntity.func_174887_a_(0) * 100) / this.tileEntity.getSmeltTime();
    }
    public int heatProgressPercent() {
        if (this.tileEntity.func_174887_a_(1) == 0) {
            return 0;
        }
        return (this.tileEntity.func_174887_a_(1) * 100) / this.tileEntity.getMaxHeat();
    }
}
