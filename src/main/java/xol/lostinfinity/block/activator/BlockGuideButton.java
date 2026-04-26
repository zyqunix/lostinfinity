package xol.lostinfinity.block.activator;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockTrackTrigger;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.coordinates.CelestialCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockGuideButton.class */
public class BlockGuideButton extends Block {
    public BlockGuideButton(String name) {
        super(Material.field_151573_f);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int side;
        if (!worldIn.field_72995_K) {
            BlockPos baseReference = CelestialCoordinates.mazeReferencePos();
            BlockPos guidePos = null;
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
            int x = 0;
            loop0: while (true) {
                if (x > 29) {
                    break;
                }
                for (int z = 0; z <= 29; z++) {
                    if (worldIn.func_180495_p(baseReference.func_177982_a(x, 1, z)).func_177230_c() == BlockInit.guideBlock) {
                        guidePos = baseReference.func_177982_a(x, 1, z);
                        break loop0;
                    }
                }
                x++;
            }
            if (guidePos != null) {
                if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == BlockInit.arenaBrickBlack) {
                    side = 0;
                } else if (worldIn.func_180495_p(pos.func_177982_a(1, 0, 0)).func_177230_c() == BlockInit.arenaBrickBlack) {
                    side = 1;
                } else if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == BlockInit.arenaBrickBlack) {
                    side = 2;
                } else {
                    side = 3;
                }
                switch (side) {
                    case 0:
                        int track = trackStyle(worldIn.func_180495_p(guidePos.func_177982_a(0, -1, 1)).func_177230_c());
                        if (worldIn.func_175623_d(guidePos.func_177982_a(0, 0, 1)) && track > 0) {
                            if (track == 2) {
                                BlockTrackTrigger trigger = (BlockTrackTrigger) worldIn.func_180495_p(guidePos.func_177982_a(0, -1, 1)).func_177230_c();
                                trigger.trigger(worldIn, guidePos.func_177982_a(0, -1, 1));
                            }
                            if (track != 3) {
                                worldIn.func_175656_a(guidePos.func_177982_a(0, -1, 1), BlockInit.blockTrackUsed.func_176223_P());
                            }
                            worldIn.func_175656_a(guidePos.func_177982_a(0, 0, 1), BlockInit.guideBlock.func_176223_P());
                            worldIn.func_175698_g(guidePos);
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.MASTER, 0.75f, 1.0f);
                            break;
                        }
                        break;
                    case 1:
                        int track2 = trackStyle(worldIn.func_180495_p(guidePos.func_177982_a(-1, -1, 0)).func_177230_c());
                        if (worldIn.func_175623_d(guidePos.func_177982_a(-1, 0, 0)) && track2 > 0) {
                            if (track2 == 2) {
                                BlockTrackTrigger trigger2 = (BlockTrackTrigger) worldIn.func_180495_p(guidePos.func_177982_a(-1, 0, 0)).func_177230_c();
                                trigger2.trigger(worldIn, guidePos.func_177982_a(-1, 0, 0));
                            }
                            if (track2 != 3) {
                                worldIn.func_175656_a(guidePos.func_177982_a(-1, -1, 0), BlockInit.blockTrackUsed.func_176223_P());
                            }
                            worldIn.func_175656_a(guidePos.func_177982_a(-1, 0, 0), BlockInit.guideBlock.func_176223_P());
                            worldIn.func_175698_g(guidePos);
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.MASTER, 0.75f, 1.0f);
                            break;
                        }
                        break;
                    case 2:
                        int track3 = trackStyle(worldIn.func_180495_p(guidePos.func_177982_a(0, -1, -1)).func_177230_c());
                        if (worldIn.func_175623_d(guidePos.func_177982_a(0, 0, -1)) && track3 > 0) {
                            if (track3 == 2) {
                                BlockTrackTrigger trigger3 = (BlockTrackTrigger) worldIn.func_180495_p(guidePos.func_177982_a(0, -1, -1)).func_177230_c();
                                trigger3.trigger(worldIn, guidePos.func_177982_a(0, -1, -1));
                            }
                            if (track3 != 3) {
                                worldIn.func_175656_a(guidePos.func_177982_a(0, -1, -1), BlockInit.blockTrackUsed.func_176223_P());
                            }
                            worldIn.func_175656_a(guidePos.func_177982_a(0, 0, -1), BlockInit.guideBlock.func_176223_P());
                            worldIn.func_175698_g(guidePos);
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.MASTER, 0.75f, 1.0f);
                            break;
                        }
                        break;
                    case 3:
                        int track4 = trackStyle(worldIn.func_180495_p(guidePos.func_177982_a(1, -1, 0)).func_177230_c());
                        if (worldIn.func_175623_d(guidePos.func_177982_a(1, 0, 0)) && track4 > 0) {
                            if (track4 == 2) {
                                BlockTrackTrigger trigger4 = (BlockTrackTrigger) worldIn.func_180495_p(guidePos.func_177982_a(1, -1, 0)).func_177230_c();
                                trigger4.trigger(worldIn, guidePos.func_177982_a(1, -1, 0));
                            }
                            if (track4 != 3) {
                                worldIn.func_175656_a(guidePos.func_177982_a(1, -1, 0), BlockInit.blockTrackUsed.func_176223_P());
                            }
                            worldIn.func_175656_a(guidePos.func_177982_a(1, 0, 0), BlockInit.guideBlock.func_176223_P());
                            worldIn.func_175698_g(guidePos);
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.MASTER, 0.75f, 1.0f);
                            break;
                        }
                        break;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private int trackStyle(Block block) {
        if (block instanceof BlockTrackTrigger) {
            return 2;
        }
        if (block == BlockInit.blockTrack) {
            return 1;
        }
        if (block == BlockInit.blockTrackWin) {
            return 3;
        }
        return 0;
    }
}
