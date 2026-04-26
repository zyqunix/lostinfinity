package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockBlightEyeBattery.class */
public class BlockBlightEyeBattery extends BlockBasic {
    public BlockBlightEyeBattery(String name) {
        super(name);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K) {
            ArrayList<BlockPos> eyes = new ArrayList<>();
            int eyeCount = 0;
            for (int i = -6; i <= 6; i++) {
                for (int j = -6; j < 6; j++) {
                    if (worldIn.func_180495_p(pos.func_177982_a(i, 0, j)).func_177230_c().equals(BlockInit.blightEye)) {
                        eyeCount++;
                        eyes.add(pos.func_177982_a(i, 0, j));
                    }
                }
            }
            if (eyeCount >= 6) {
                ArrayList<BlockPos> mouthBlocks = new ArrayList<>();
                for (int i2 = -6; i2 <= 6; i2++) {
                    for (int j2 = -6; j2 < 6; j2++) {
                        if (worldIn.func_180495_p(pos.func_177982_a(i2, 0, j2)).func_177230_c().equals(BlockInit.blightMouth)) {
                            mouthBlocks.add(pos.func_177982_a(i2, 0, j2));
                        }
                    }
                }
                boolean stack1 = false;
                boolean stack2 = false;
                boolean stack3 = false;
                boolean stack4 = false;
                EntityItem stack1Entity = null;
                EntityItem stack2Entity = null;
                EntityItem stack3Entity = null;
                EntityItem stack4Entity = null;
                for (BlockPos mouthPos : mouthBlocks) {
                    AxisAlignedBB aabb = new AxisAlignedBB(mouthPos.func_177984_a());
                    for (EntityItem itemEntity : worldIn.func_72872_a(EntityItem.class, aabb)) {
                        Item testItem = itemEntity.func_92059_d().func_77973_b();
                        if (testItem == ItemInit.basicStorageChip && !stack1) {
                            if (itemEntity.func_92059_d().func_190916_E() >= 3) {
                                stack1Entity = itemEntity;
                                stack1 = true;
                            }
                        } else if (testItem == ItemInit.organicPlate && !stack2) {
                            if (itemEntity.func_92059_d().func_190916_E() >= 10) {
                                stack2Entity = itemEntity;
                                stack2 = true;
                            }
                        } else if (testItem == ItemInit.gloominessenceCubes && !stack3) {
                            if (itemEntity.func_92059_d().func_190916_E() >= 20) {
                                stack3Entity = itemEntity;
                                stack3 = true;
                            }
                        } else if (testItem == ItemInit.organicWire && !stack4 && itemEntity.func_92059_d().func_190916_E() >= 5) {
                            stack4Entity = itemEntity;
                            stack4 = true;
                        }
                    }
                }
                if (stack1 && stack2 && stack3 && stack4) {
                    EntityItem resultItem = new EntityItem(worldIn, mouthBlocks.get(0).func_177958_n(), mouthBlocks.get(0).func_177956_o() + 2, mouthBlocks.get(0).func_177952_p(), new ItemStack(ItemInit.darkworldDataChip));
                    resultItem.field_70159_w = 0.0d;
                    resultItem.field_70181_x = 1.0d;
                    resultItem.field_70179_y = 0.0d;
                    resultItem.field_70133_I = true;
                    worldIn.func_72838_d(resultItem);
                    worldIn.func_184133_a((EntityPlayer) null, pos.func_177984_a(), SoundInit.CRUNCHING, SoundCategory.BLOCKS, 2.0f, 1.0f);
                    stack1Entity.func_92059_d().func_190918_g(stack1Entity.func_92059_d().func_190916_E());
                    stack2Entity.func_92059_d().func_190918_g(stack2Entity.func_92059_d().func_190916_E());
                    stack3Entity.func_92059_d().func_190918_g(stack3Entity.func_92059_d().func_190916_E());
                    stack4Entity.func_92059_d().func_190918_g(stack4Entity.func_92059_d().func_190916_E());
                    for (BlockPos eye : eyes) {
                        worldIn.func_175656_a(eye, BlockInit.blightEyeEmpty.func_176223_P());
                    }
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Feed the darkness, and it will feed you in return..."));
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
