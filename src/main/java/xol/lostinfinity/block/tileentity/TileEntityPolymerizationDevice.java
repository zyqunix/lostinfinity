package xol.lostinfinity.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityPolymerizationDevice.class */
public class TileEntityPolymerizationDevice extends TileEntity {
    private boolean active = false;
    private int nextMonomer = 0;

    public AxisAlignedBB getTank() {
        int minY = this.field_174879_c.func_177956_o() + 3;
        int minX = this.field_174879_c.func_177958_n() - 2;
        int minZ = this.field_174879_c.func_177952_p() - 2;
        int maxY = minY + 1;
        int maxX = minX + 4;
        int maxZ = minZ + 4;
        return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public boolean isActivated() {
        return this.active;
    }

    public void activate(EntityPlayer playerIn, EnumHand hand) {
        Item nextMonomer;
        IBlockState state;
        if (!this.active) {
            clearTank();
            this.nextMonomer = this.field_145850_b.field_73012_v.nextInt(5);
            for (EntityPlayer player : this.field_145850_b.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_174877_v().func_177982_a(-7, -4, -7), func_174877_v().func_177982_a(7, 4, 7)))) {
                ItemStack newStack = new ItemStack(ItemInit.monomerCollector, 1);
                newStack.func_77982_d(new NBTTagCompound());
                newStack.func_77978_p().func_74772_a("EndTime", System.currentTimeMillis() + 200000);
                player.func_191521_c(newStack);
                player.func_145747_a(new TextComponentString(TextFmt.Green + "Collect monomer samples around the labyrinth and return them to start the polymerization process."));
                messageMonomer(player);
                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GENERIC_UI_4, SoundCategory.PLAYERS, 1.25f, 1.0f);
            }
            this.active = true;
            return;
        }
        if (this.active) {
            switch (this.nextMonomer) {
                case 0:
                    nextMonomer = ItemInit.purpleMonomerSample;
                    break;
                case 1:
                    nextMonomer = ItemInit.blueMonomerSample;
                    break;
                case 2:
                    nextMonomer = ItemInit.redMonomerSample;
                    break;
                case 3:
                    nextMonomer = ItemInit.yellowMonomerSample;
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    nextMonomer = ItemInit.greenMonomerSample;
                    break;
                default:
                    nextMonomer = ItemInit.purpleMonomerSample;
                    break;
            }
            ItemStack stack = playerIn.func_184586_b(hand);
            Item item = stack.func_77973_b();
            if (item.equals(nextMonomer)) {
                if (item.equals(ItemInit.purpleMonomerSample)) {
                    state = BlockInit.monomerCube.func_176203_a(0);
                } else if (item.equals(ItemInit.blueMonomerSample)) {
                    state = BlockInit.monomerCube.func_176203_a(1);
                } else if (item.equals(ItemInit.redMonomerSample)) {
                    state = BlockInit.monomerCube.func_176203_a(2);
                } else if (item.equals(ItemInit.yellowMonomerSample)) {
                    state = BlockInit.monomerCube.func_176203_a(3);
                } else if (item.equals(ItemInit.greenMonomerSample)) {
                    state = BlockInit.monomerCube.func_176203_a(4);
                } else {
                    return;
                }
                AxisAlignedBB tank = getTank();
                int xMin = (int) tank.field_72340_a;
                int xMax = (int) tank.field_72336_d;
                int zMin = (int) tank.field_72339_c;
                int zMax = (int) tank.field_72334_f;
                int yMin = (int) tank.field_72338_b;
                int yMax = (int) tank.field_72337_e;
                boolean placed = false;
                int i = yMin;
                while (true) {
                    if (i <= yMax) {
                        for (int j = xMin; j <= xMax; j++) {
                            for (int k = zMin; k <= zMax; k++) {
                                BlockPos checkPos = new BlockPos(j, i, k);
                                if (this.field_145850_b.func_175623_d(checkPos)) {
                                    this.field_145850_b.func_175656_a(checkPos, state);
                                    placed = true;
                                    stack.func_190918_g(1);
                                }
                            }
                        }
                        i++;
                    }
                }
                if (!placed) {
                    for (EntityPlayer player2 : this.field_145850_b.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_174877_v().func_177982_a(-7, -4, -7), func_174877_v().func_177982_a(7, 4, 7)))) {
                        player2.func_145747_a(new TextComponentString(TextFmt.Green + "Sucessfully Combined Monomers into Polymer!"));
                        for (int i2 = 0; i2 < player2.field_71071_by.func_70302_i_(); i2++) {
                            if (player2.field_71071_by.func_70301_a(i2).func_77973_b() == ItemInit.monomerCollector) {
                                player2.field_71071_by.func_70299_a(i2, ItemStack.field_190927_a);
                            }
                        }
                        player2.func_191521_c(new ItemStack(ItemInit.dimensionalPolymer, 1));
                        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.CHEMICAL_MIXING, SoundCategory.PLAYERS, 0.7f, 0.7f + (this.field_145850_b.field_73012_v.nextFloat() * 0.6f));
                    }
                    clearTank();
                    this.active = false;
                    return;
                }
                int iNextInt = this.field_145850_b.field_73012_v.nextInt(5);
                while (true) {
                    int randMonomer = iNextInt;
                    if (randMonomer == this.nextMonomer) {
                        iNextInt = this.field_145850_b.field_73012_v.nextInt(5);
                    } else {
                        this.nextMonomer = randMonomer;
                        messageMonomer(playerIn);
                        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundEvents.field_187620_cL, SoundCategory.PLAYERS, 1.0f, 0.7f + (this.field_145850_b.field_73012_v.nextFloat() * 0.6f));
                        return;
                    }
                }
            } else {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "That is not the correct monomer!"));
                messageMonomer(playerIn);
            }
        }
    }

    private void messageMonomer(EntityPlayer player) {
        String monomer;
        TextFmt fmt;
        TextFmt textFmt = TextFmt.Dark_Purple;
        switch (this.nextMonomer) {
            case 0:
                monomer = "Purple";
                fmt = TextFmt.Dark_Purple;
                break;
            case 1:
                monomer = "Blue";
                fmt = TextFmt.Dark_Blue;
                break;
            case 2:
                monomer = "Red";
                fmt = TextFmt.Dark_Red;
                break;
            case 3:
                monomer = "Yellow";
                fmt = TextFmt.Yellow;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                monomer = "Green";
                fmt = TextFmt.Green;
                break;
            default:
                monomer = "Purple";
                fmt = TextFmt.Dark_Purple;
                break;
        }
        player.func_145747_a(new TextComponentString(String.format(fmt + "%s monomer is needed next", monomer)));
    }

    private void clearTank() {
        AxisAlignedBB tank = getTank();
        int xMin = (int) tank.field_72340_a;
        int xMax = (int) tank.field_72336_d;
        int zMin = (int) tank.field_72339_c;
        int zMax = (int) tank.field_72334_f;
        int yMin = (int) tank.field_72338_b;
        int yMax = (int) tank.field_72337_e;
        for (BlockPos pos : BlockPos.func_191532_a(xMin, yMin, zMin, xMax, yMax, zMax)) {
            this.field_145850_b.func_175698_g(pos);
        }
    }
}
