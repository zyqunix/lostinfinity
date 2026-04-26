package xol.lostinfinity.block.generator;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityGenerator;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.tool.ItemCloakingDevice;
public class BlockGenerator extends Block {
    public BlockGenerator(String name, float hardness, Material material, CreativeTabs tab) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(hardness);
        func_149647_a(tab);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityGenerator();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    private TileEntityGenerator getTE(World world, BlockPos pos) {
        return (TileEntityGenerator) world.func_175625_s(pos);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && !playerIn.func_184586_b(hand).func_190926_b() && playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.celestialRedstone)) {
            if (!worldIn.field_72995_K) {
                TileEntityGenerator te = getTE(worldIn, pos);
                PlayerList pl = worldIn.func_73046_m().func_184103_al();
                boolean isPlacerNull = te.getMyPlacer() == null;
                boolean isUUIDZeroes = isPlacerNull || te.getMyPlacer() == UUID.fromString("00000000-0000-0000-0000-000000000000");
                boolean shouldReplaceOwner = isUUIDZeroes || pl.func_177451_a(te.getMyPlacer()) == null;
                if (shouldReplaceOwner && playerIn.func_110124_au() != null && !playerIn.func_70005_c_().toLowerCase().contains(".")) {
                    te.setMyPlacer(playerIn.func_110124_au());
                }
                UUID placer_uuid = te.getMyPlacer();
                int power_upgrade = 0;
                int efficiency_upgrade = 0;
                int range_upgrade = 0;
                int depth_upgrade = 0;
                for (int bl = 0; bl < 4; bl++) {
                    if (worldIn.func_180495_p(new BlockPos(pos.func_177958_n() - 2, pos.func_177956_o() + bl, pos.func_177952_p() - 2)).func_177230_c().equals(BlockInit.powerModule)) {
                        power_upgrade++;
                    }
                    if (worldIn.func_180495_p(new BlockPos(pos.func_177958_n() + 2, pos.func_177956_o() + bl, pos.func_177952_p() - 2)).func_177230_c().equals(BlockInit.efficiencyModule)) {
                        efficiency_upgrade++;
                    }
                    if (worldIn.func_180495_p(new BlockPos(pos.func_177958_n() - 2, pos.func_177956_o() + bl, pos.func_177952_p() + 2)).func_177230_c().equals(BlockInit.rangeModule)) {
                        range_upgrade++;
                    }
                    if (worldIn.func_180495_p(new BlockPos(pos.func_177958_n() + 2, pos.func_177956_o() + bl, pos.func_177952_p() + 2)).func_177230_c().equals(BlockInit.depthModule)) {
                        depth_upgrade++;
                    }
                }
                activateGenerator(worldIn, pos, state, playerIn, power_upgrade, efficiency_upgrade, range_upgrade, depth_upgrade, placer_uuid);
                boolean run = true;
                int yOff = 1;
                ArrayList<BlockGenerator> gens_used = new ArrayList<>();
                gens_used.add(this);
                while (run) {
                    Block aboveBlock = worldIn.func_180495_p(pos.func_177982_a(0, yOff, 0)).func_177230_c();
                    if ((aboveBlock instanceof BlockGenerator) && !gens_used.contains(aboveBlock)) {
                        BlockGenerator nextGen = (BlockGenerator) aboveBlock;
                        gens_used.add(nextGen);
                        nextGen.activateGenerator(worldIn, pos, state, playerIn, power_upgrade, efficiency_upgrade, range_upgrade, depth_upgrade, placer_uuid);
                    } else {
                        run = false;
                    }
                    yOff++;
                }
            }
            worldIn.func_184134_a(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundInit.GALAXYFIRE, SoundCategory.MASTER, 2.0f, 1.0f, false);
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }
    public void activateGenerator(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, int power_upgrade, int efficiency_upgrade, int range_upgrade, int depth_upgrade, UUID placer_uuid) {
    }
    protected boolean is_detectable(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.func_184614_ca().func_77973_b() instanceof ItemCloakingDevice) {
                ItemStack held = player.func_184614_ca();
                if (held.func_77942_o() && held.func_77978_p().func_74767_n("Cloaking")) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
