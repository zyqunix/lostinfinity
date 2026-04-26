package xol.lostinfinity.gui.guis;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketFusionTable;
import xol.lostinfinity.gui.containers.ContainerFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.Reference;
public class GuiFusionTable extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/fusion_table.png");
    private static final int SHAPE_WIDTH = 12;
    private static final int SHAPE_HEIGHT = 12;
    private final TileEntityFusionTable tileEntity;
    private final InventoryPlayer invPlayer;
    public GuiFusionTable(InventoryPlayer invPlayer, TileEntityFusionTable tileEntity) {
        super(new ContainerFusionTable(invPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.invPlayer = invPlayer;
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        int boardX = this.field_147003_i + 40;
        int boardY = this.field_147009_r + 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int offsetX = boardX + (12 * j) + (5 * j);
                int offsetY = boardY + (12 * i) + (5 * i);
                int boardIndex = (i * 6) + j;
                if (this.tileEntity.func_174887_a_(boardIndex) > 0 && this.tileEntity.func_174887_a_(boardIndex) < 6) {
                    func_73729_b(offsetX, offsetY, TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(boardIndex)).getPixelX(), TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(boardIndex)).getPixelY(), 12, 12);
                }
                int offSetX2 = this.field_147003_i + 39 + (13 * j) + (4 * j);
                int offSetY2 = this.field_147009_r + 15 + (13 * i) + (4 * i);
                if (isInRect(offsetX, offsetY, 12, 12, mouseX, mouseY) && (this.tileEntity.func_174887_a_(boardIndex) <= 0 || this.tileEntity.func_174887_a_(boardIndex) >= 6)) {
                    func_73729_b(offSetX2, offSetY2, 0, 186, 14, 14);
                }
            }
        }
        int upcomingX1 = this.field_147003_i + 21;
        int upcomingY1 = this.field_147009_r + 65;
        if (this.tileEntity.func_174887_a_(24) > 0) {
            func_73729_b(upcomingX1, upcomingY1, TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(24)).getPixelX(), TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(24)).getPixelY(), 12, 12);
        }
        int upcomingX2 = this.field_147003_i + 21;
        int upcomingY2 = this.field_147009_r + 50;
        if (this.tileEntity.func_174887_a_(25) > 0) {
            func_73729_b(upcomingX2, upcomingY2, TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(25)).getPixelX(), TileEntityFusionTable.Shapes.getShapeFromId(this.tileEntity.func_174887_a_(25)).getPixelY(), 12, 12);
        }
        int progressX = this.field_147003_i + 39;
        int progressY = this.field_147009_r + 5;
        for (int i2 = 0; i2 < this.tileEntity.func_174887_a_(27); i2++) {
            func_73729_b(progressX + (14 * i2) + (3 * i2), progressY, 0, 178, 14, 8);
        }
    }
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        int boardX = this.field_147003_i + 40;
        int boardY = this.field_147009_r + 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int offsetX = boardX + (12 * j) + (5 * j);
                int offsetY = boardY + (12 * i) + (5 * i);
                int boardIndex = (i * 6) + j;
                if (isInRect(offsetX, offsetY, 12, 12, mouseX, mouseY) && (this.tileEntity.func_174887_a_(boardIndex) <= 0 || this.tileEntity.func_174887_a_(boardIndex) >= 6)) {
                    lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(this.tileEntity.func_174877_v(), this.tileEntity.func_174887_a_(24), boardIndex));
                    this.tileEntity.func_174885_b(24, this.tileEntity.func_174887_a_(25));
                    this.tileEntity.func_174885_b(25, ThreadLocalRandom.current().nextInt(1, 6));
                    lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(this.tileEntity.func_174877_v(), this.tileEntity.func_174887_a_(24), 24));
                    lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(this.tileEntity.func_174877_v(), this.tileEntity.func_174887_a_(25), 25));
                    this.invPlayer.field_70458_d.func_184185_a(SoundInit.GENERIC_UI_5, 1.0f, 1.0f);
                }
            }
        }
    }
    private boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + xSize && mouseY >= y && mouseY <= y + ySize;
    }
}
