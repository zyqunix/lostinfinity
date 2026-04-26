package xol.lostinfinity.block.activator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.tileentity.TileEntityPortalNexus;
import xol.lostinfinity.block.tileentity.TileEntityPortalNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockPortalNode.class */
public class BlockPortalNode extends BlockBasicBoolState implements ITileEntityProvider {
    public BlockPortalNode(String name) {
        super(name);
    }

    private boolean validInput(Item item) {
        return item.equals(ItemInit.blightedCapacitor);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (validInput(playerIn.func_184586_b(hand).func_77973_b())) {
                if (!worldIn.field_72995_K) {
                    if (state.equals(BlockInit.portalNode.func_176203_a(0))) {
                        if (((TileEntityPortalNode) worldIn.func_175625_s(pos)) != null) {
                            TileEntityPortalNexus nexus = null;
                            BlockPos nexusPos = null;
                            int i = -15;
                            loop0: while (true) {
                                if (i > 15) {
                                    break;
                                }
                                for (int k = -15; k <= 15; k++) {
                                    nexusPos = pos.func_177982_a(i, 0, k);
                                    if (worldIn.func_180495_p(nexusPos).func_177230_c().equals(BlockInit.portalNexus)) {
                                        nexus = (TileEntityPortalNexus) worldIn.func_175625_s(nexusPos);
                                        break loop0;
                                    }
                                }
                                i++;
                            }
                            if (nexus != null && nexusPos != null) {
                                nexus.addNodePos(pos);
                            }
                        }
                        worldIn.func_175656_a(pos, BlockInit.portalNode.func_176203_a(1));
                    }
                } else {
                    TileEntityPortalNode node = (TileEntityPortalNode) worldIn.func_175625_s(pos);
                    if (node != null) {
                        TileEntityPortalNexus nexus2 = null;
                        BlockPos nexusPos2 = null;
                        int i2 = -15;
                        loop2: while (true) {
                            if (i2 > 15) {
                                break;
                            }
                            for (int k2 = -15; k2 <= 15; k2++) {
                                nexusPos2 = pos.func_177982_a(i2, 0, k2);
                                if (worldIn.func_180495_p(nexusPos2).func_177230_c().equals(BlockInit.portalNexus)) {
                                    nexus2 = (TileEntityPortalNexus) worldIn.func_175625_s(nexusPos2);
                                    break loop2;
                                }
                            }
                            i2++;
                        }
                        if (nexus2 != null && nexusPos2 != null) {
                            node.setNexusPos(nexusPos2);
                        }
                    }
                }
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPortalNode();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
}
