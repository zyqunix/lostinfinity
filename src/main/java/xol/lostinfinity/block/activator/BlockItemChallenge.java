package xol.lostinfinity.block.activator;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockItemChallenge.class */
public class BlockItemChallenge extends Block {
    private int item_num;

    public BlockItemChallenge(String name, int itemNum) {
        super(Material.field_151573_f);
        this.item_num = 0;
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        this.item_num = itemNum;
        if (itemNum != 0) {
            BlockInit.ITEM_CHALLENGE_BLOCKS.add(this);
        }
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.item_num != 0) {
            ItemStack held = playerIn.func_184614_ca();
            if (held.func_77973_b() == getItemNeeded()) {
                boolean run = true;
                int up_check = 2;
                while (run) {
                    BlockPos check = pos.func_177982_a(0, up_check, 0);
                    if (worldIn.func_175623_d(check)) {
                        run = false;
                        held.func_190918_g(1);
                        if (!worldIn.field_72995_K) {
                            worldIn.func_175656_a(check, BlockInit.itemChallengeCrate.func_176223_P());
                            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187620_cL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        }
                    } else if (worldIn.func_180495_p(check).func_177230_c() == BlockInit.labyrinthLamp) {
                        run = false;
                    }
                    up_check++;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private Item getItemNeeded() {
        switch (this.item_num) {
            case 1:
                return ItemInit.acidicTongue;
            case 2:
                return ItemInit.firefeather;
            case 3:
                return ItemInit.hypnoticEye;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return ItemInit.nightmarePowder;
            case 5:
                return ItemInit.stickyHide;
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return ItemInit.mysteriousGloop;
            case 7:
                return ItemInit.glowBulb;
            case 8:
                return ItemInit.metalBranch;
            case 9:
                return ItemInit.shadowHide;
            case ItemHeadCollector.CHARGE_LIMIT /* 10 */:
                return ItemInit.heavyCrystal;
            case 11:
                return ItemInit.duskerEggs;
            case 12:
                return ItemInit.explosiveSack;
            case 13:
                return ItemInit.hardBone;
            case 14:
                return ItemInit.magicStone;
            case 15:
                return ItemInit.livingSlime;
            case 16:
                return ItemInit.reflectiveShard;
            case 17:
                return ItemInit.rockspine;
            case 18:
                return ItemInit.razorFangs;
            case 19:
                return ItemInit.potentPolarcronite;
            case 20:
                return ItemInit.organicFuse;
            default:
                return ItemInit.masterCraftedAlloy;
        }
    }

    public static Block randomItemBlock(Random rand) {
        return BlockInit.ITEM_CHALLENGE_BLOCKS.get(rand.nextInt(BlockInit.ITEM_CHALLENGE_BLOCKS.size()));
    }
}
