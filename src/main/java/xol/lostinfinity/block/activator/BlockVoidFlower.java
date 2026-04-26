package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicPlant;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
public class BlockVoidFlower extends BlockBasicPlant {
    public BlockVoidFlower(String name) {
        super(name, Material.field_151585_k);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.vacuumContainer)) {
            if (!worldIn.field_72995_K) {
                worldIn.func_175656_a(pos, BlockInit.voidVacuum.func_176223_P());
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }
}
