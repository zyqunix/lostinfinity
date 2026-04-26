package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.projectile.entity.EntityPoisonousBubble;
import xol.lostinfinity.util.data.IMaxAttack;
public class BlockAcidicConcoctionGel extends Block implements IMaxAttack {
    protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0d, 0.0d, 0.0d, 1.0d, 0.0625d, 1.0d);
    public BlockAcidicConcoctionGel(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }
    public BlockAcidicConcoctionGel(String name, float hardness, Material material, CreativeTabs tab) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149647_a(tab);
        func_149722_s();
        func_149672_a(SoundType.field_185859_l);
        func_149675_a(true);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CARPET_AABB;
    }
    public boolean func_149662_c(IBlockState state) {
        return false;
    }
    public boolean func_149686_d(IBlockState state) {
        return false;
    }
    public boolean func_176196_c(World worldIn, BlockPos pos) {
        return super.func_176196_c(worldIn, pos) && canBlockStay(worldIn, pos);
    }
    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        checkForDrop(worldIn, pos, state);
    }
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.field_72995_K && (entityIn instanceof EntityLivingBase) && !(entityIn instanceof EntityImmaterial) && entityIn.field_70173_aa % 4 == 2) {
            if ((entityIn instanceof EntityPlayer) && ((EntityPlayer) entityIn).func_70644_a(PotionInit.ACIDIC)) {
                return;
            }
            IMaxAttack.dealPotionDamage((EntityLivingBase) entityIn, ((EntityLivingBase) entityIn).func_110138_aP() / 10.0f);
        }
    }
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (rand.nextBoolean()) {
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
        } else {
            releasePoison(worldIn, pos);
        }
    }
    private static void releasePoison(World worldIn, BlockPos pos) {
        if (!worldIn.field_72995_K) {
            for (BlockPos poisonPos : BlockPos.func_177980_a(pos.func_177982_a(-2, -2, -2), pos.func_177982_a(2, 2, 2))) {
                if (worldIn.func_180495_p(poisonPos).equals(BlockInit.acidicConcoctionGel.func_176223_P()) && worldIn.field_73012_v.nextInt(3) == 0) {
                    for (int i = 0; i < 2; i++) {
                        EntityPoisonousBubble bubble = new EntityPoisonousBubble(worldIn);
                        bubble.func_70634_a((((double) poisonPos.func_177958_n()) + (worldIn.field_73012_v.nextDouble() * 0.5d)) - 0.25d, poisonPos.func_177956_o() + 0.2f, (((double) poisonPos.func_177952_p()) + (worldIn.field_73012_v.nextDouble() * 0.5d)) - 0.25d);
                        bubble.func_70186_c(0.0d, 1.0d, 0.0d, 0.2f, 3.0f);
                        worldIn.func_72838_d(bubble);
                    }
                }
            }
        }
    }
    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
        if (!canBlockStay(worldIn, pos)) {
            worldIn.func_175698_g(pos);
            return false;
        }
        return true;
    }
    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return !worldIn.func_175623_d(pos.func_177977_b());
    }
    @SideOnly(Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.UP || blockAccess.func_180495_p(pos.func_177972_a(side)).func_177230_c() == this) {
            return true;
        }
        return super.func_176225_a(blockState, blockAccess, pos, side);
    }
    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
}
