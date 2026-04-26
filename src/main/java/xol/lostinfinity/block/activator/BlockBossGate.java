package xol.lostinfinity.block.activator;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
public class BlockBossGate extends BlockBasicGlass {
    public BlockBossGate(String name) {
        super(name);
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            playerIn.func_70606_j(0.5f);
            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "You'll never get through that gate " + playerIn.func_70005_c_() + ". You will have to open a portal to my prison."));
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.ELECTRIC_SHOCK, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.field_72995_K && !entityIn.field_70128_L && (entityIn instanceof EntityLivingBase)) {
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SCANNER, SoundCategory.MASTER, 1.0f, 1.0f);
            EntityLivingBase living = (EntityLivingBase) entityIn;
            living.func_70606_j(0.0f);
        }
    }
}
