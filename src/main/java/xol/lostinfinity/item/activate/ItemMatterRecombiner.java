package xol.lostinfinity.item.activate;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.ISwitchModels;
public class ItemMatterRecombiner extends ItemBasic implements ISwitchModels {
    public ItemMatterRecombiner(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
        func_77625_d(1);
        setModelSwitch("separation", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            int type = stack.func_77978_p().func_74762_e("separation_data");
            if (type == 0 && playerIn.func_180425_c().func_177956_o() > worldIn.func_189649_b(playerIn.func_180425_c().func_177958_n(), playerIn.func_180425_c().func_177952_p()) + 3) {
                return super.func_77659_a(worldIn, playerIn, handIn);
            }
            stack.func_77978_p().func_74768_a("separation_data", 1);
            if (!worldIn.field_72995_K) {
                if (type == 0) {
                    stack.func_77978_p().func_74772_a("SeparationTime", System.currentTimeMillis());
                    int numBlocks = worldIn.field_73012_v.nextInt(6) + 5;
                    stack.func_77978_p().func_74768_a("NumBlocks", numBlocks);
                    int blockCount = 0;
                    ArrayList<BlockPos> blocks = new ArrayList<>();
                    for (int i = -70; i <= 70; i++) {
                        for (int j = -70; j <= 70; j++) {
                            int x = playerIn.func_180425_c().func_177958_n() + i;
                            int z = playerIn.func_180425_c().func_177952_p() + j;
                            int y = worldIn.func_189649_b(x, z);
                            blocks.add(new BlockPos(x, y, z));
                        }
                    }
                    Collections.shuffle(blocks);
                    for (BlockPos randPos : blocks) {
                        if (worldIn.func_175623_d(randPos)) {
                            worldIn.func_175656_a(randPos, BlockInit.separatedMatter.func_176223_P());
                            blockCount++;
                        }
                        if (blockCount >= numBlocks) {
                            break;
                        }
                    }
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLASK_EXPLODE, SoundCategory.PLAYERS, 1.5f, 1.0f);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + String.format("Separation initiated. Quickly collect %d blocks of seperated matter in order to recombine it", Integer.valueOf(numBlocks))));
                } else if (type == 1) {
                    long timeDiff = System.currentTimeMillis() - stack.func_77978_p().func_74763_f("SeparationTime");
                    int numBlocks2 = stack.func_77978_p().func_74762_e("NumBlocks");
                    boolean blocksFound = false;
                    if (timeDiff < 300000) {
                        int count = 0;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= playerIn.field_71071_by.func_70302_i_()) {
                                break;
                            }
                            ItemStack invStack = playerIn.field_71071_by.func_70301_a(i2);
                            if (invStack.func_77973_b().equals(Item.func_150898_a(BlockInit.separatedMatter))) {
                                int stackSize = invStack.func_190916_E();
                                count += stackSize;
                                if (count > numBlocks2) {
                                    playerIn.field_71071_by.func_70299_a(i2, new ItemStack(BlockInit.separatedMatter, stackSize - numBlocks2));
                                } else if (count == numBlocks2 || count < numBlocks2) {
                                    playerIn.field_71071_by.func_70299_a(i2, ItemStack.field_190927_a);
                                }
                                if (count >= numBlocks2) {
                                    blocksFound = true;
                                    break;
                                }
                            }
                            i2++;
                        }
                        if (blocksFound) {
                            stack.func_190918_g(1);
                            playerIn.func_191521_c(new ItemStack(ItemInit.reconfiguredMatter, 1));
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "Matter successfully recombined"));
                            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_22, SoundCategory.PLAYERS, 1.5f, 1.0f);
                        } else {
                            playerIn.func_191521_c(new ItemStack(BlockInit.separatedMatter, count));
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "Insufficient matter to recombine."));
                        }
                    }
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
}
