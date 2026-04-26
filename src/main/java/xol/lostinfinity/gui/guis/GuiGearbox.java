package xol.lostinfinity.gui.guis;

import java.io.IOException;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.block.tileentity.TileEntityGearbox;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketGearbox;
import xol.lostinfinity.gui.containers.ContainerGearbox;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiGearbox.class */
public class GuiGearbox extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/gearbox.png");
    private final InventoryPlayer player;
    private final TileEntityGearbox tileentity;
    private static final int pegLeft = 0;
    private static final int pegTop = 166;
    private static final int pegWidth = 4;
    private static final int pegStartX = 57;
    private static final int pegStartY = 55;
    private int selectedGear;

    public GuiGearbox(InventoryPlayer player, TileEntityGearbox tileentity) {
        super(new ContainerGearbox(player, tileentity));
        this.selectedGear = pegLeft;
        this.player = player;
        this.tileentity = tileentity;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        func_73729_b(this.field_147003_i, this.field_147009_r, pegLeft, pegLeft, this.field_146999_f, this.field_147000_g);
        int[] pegs = this.tileentity.getPegPositions();
        int[] gears = this.tileentity.getPlacedGears();
        for (int i = pegLeft; i < pegs.length; i++) {
            func_73729_b(pegStartX + pegs[i] + this.field_147003_i, pegStartY + this.field_147009_r, pegLeft, pegTop, 5, 5);
        }
        for (int i2 = pegLeft; i2 < gears.length; i2++) {
            int gear = gears[i2];
            int gearLeft = pegLeft;
            int gearWidth = pegLeft;
            int yOffset = pegLeft;
            int xOffset = pegLeft;
            switch (gear) {
                case 2:
                    gearLeft = 5;
                    gearWidth = 5;
                    yOffset = pegLeft;
                    xOffset = pegLeft;
                    break;
                case 3:
                    gearLeft = 10;
                    gearWidth = 7;
                    yOffset = -1;
                    xOffset = -1;
                    break;
                case 4:
                    gearLeft = 17;
                    gearWidth = 9;
                    yOffset = -2;
                    xOffset = -2;
                    break;
                case 5:
                    gearLeft = 26;
                    gearWidth = 11;
                    yOffset = -3;
                    xOffset = -3;
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                    gearLeft = 37;
                    gearWidth = 13;
                    yOffset = -4;
                    xOffset = -4;
                    break;
            }
            func_73729_b(pegStartX + pegs[i2] + this.field_147003_i + xOffset, pegStartY + this.field_147009_r + yOffset, gearLeft, pegTop, gearWidth, gearWidth);
        }
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("Gearbox", 66, 6, pegLeft);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (isInRect(53 + this.field_147003_i, 19 + this.field_147009_r, 12, 12, mouseX, mouseY)) {
            this.selectedGear = 6;
        }
        if (isInRect(71 + this.field_147003_i, 21 + this.field_147009_r, 10, 10, mouseX, mouseY)) {
            this.selectedGear = 5;
        }
        if (isInRect(87 + this.field_147003_i, 23 + this.field_147009_r, 8, 8, mouseX, mouseY)) {
            this.selectedGear = 4;
        }
        if (isInRect(101 + this.field_147003_i, 25 + this.field_147009_r, 6, 6, mouseX, mouseY)) {
            this.selectedGear = 3;
        }
        if (isInRect(113 + this.field_147003_i, 27 + this.field_147009_r, 4, 4, mouseX, mouseY)) {
            this.selectedGear = 2;
        }
        if (this.selectedGear != 0) {
            int[] pegs = this.tileentity.getPegPositions();
            int[] gears = this.tileentity.getPlacedGears();
            int[] requiredGears = this.tileentity.getGears();
            for (int i = pegLeft; i < pegs.length; i++) {
                if (isInRect(this.field_147003_i + pegs[i] + pegStartX, this.field_147009_r + pegStartY, 5, 5, mouseX, mouseY)) {
                    boolean canPlace = true;
                    if (this.selectedGear > gears[i]) {
                        if (i - 1 >= 0 && gears[i - 1] + this.selectedGear > requiredGears[i - 1] + requiredGears[i]) {
                            canPlace = pegLeft;
                        } else if (i + 1 < pegs.length && gears[i + 1] + this.selectedGear > requiredGears[i + 1] + requiredGears[i]) {
                            canPlace = pegLeft;
                        } else if (i == 0 && this.selectedGear != requiredGears[pegLeft]) {
                            canPlace = pegLeft;
                        }
                    }
                    if (canPlace) {
                        gears[i] = this.selectedGear;
                    }
                }
            }
            boolean complete = true;
            for (int i2 = pegLeft; i2 < gears.length; i2++) {
                if (gears[i2] != requiredGears[i2]) {
                    complete = pegLeft;
                }
            }
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketGearbox(this.tileentity.func_174877_v(), complete));
            this.tileentity.setPlacedGears(gears);
        }
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + xSize && mouseY >= y && mouseY <= y + ySize;
    }
}
