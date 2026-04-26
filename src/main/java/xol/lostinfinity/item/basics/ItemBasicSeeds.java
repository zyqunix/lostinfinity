package xol.lostinfinity.item.basics;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
public class ItemBasicSeeds extends ItemBasic implements IPlantable {
    Block cropBlock;
    Block soilBlock;
    private String customDescription;
    public ItemBasicSeeds(String name) {
        super(name, TabsInit.TAB_AUXMATS);
        this.cropBlock = null;
        this.soilBlock = null;
        this.customDescription = "";
    }
    public void setDescription(String string) {
        this.customDescription = string;
    }
    public void setCropAndSoil(Block b1, Block b2) {
        this.cropBlock = b1;
        this.soilBlock = b2;
    }
    public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.func_184586_b(hand);
        IBlockState state = worldIn.func_180495_p(pos);
        if (facing == EnumFacing.UP && player.func_175151_a(pos.func_177972_a(facing), facing, itemstack) && state.func_177230_c() == this.soilBlock && worldIn.func_175623_d(pos.func_177984_a())) {
            worldIn.func_175656_a(pos.func_177984_a(), this.cropBlock.func_176223_P());
            if (player instanceof EntityPlayerMP) {
                CriteriaTriggers.field_193137_x.func_193173_a((EntityPlayerMP) player, pos.func_177984_a(), itemstack);
            }
            itemstack.func_190918_g(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.cropBlock.func_176223_P();
    }
    public Block getSoilBlock() {
        return this.soilBlock;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this.customDescription.isEmpty()) {
            tooltip.add(TextFmt.Italic + "These seem like they will be very difficult to plant.");
        } else {
            tooltip.add(this.customDescription);
        }
    }
}
