package xol.lostinfinity.block.misc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockPoweredHyperMelder extends BlockBasic {
    public BlockPoweredHyperMelder(String name) {
        super(name);
        func_149715_a(1.0f);
        func_149675_a(true);
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        List<BlockPos> rememberPos = new ArrayList<>();
        if (!worldIn.field_72995_K) {
            int generators = 0;
            for (int addX = -9; addX < 9; addX++) {
                for (int addZ = -9; addZ < 9; addZ++) {
                    BlockPos testPos = new BlockPos(pos.func_177982_a(addX, -1, addZ));
                    if (worldIn.func_180495_p(testPos).func_177230_c() == BlockInit.hyperGeneratorPowered) {
                        generators++;
                        rememberPos.add(testPos);
                    }
                }
            }
            if (generators < 8) {
                worldIn.func_175656_a(pos, BlockInit.hyperMelderUnpowered.func_176223_P());
                return;
            }
            AxisAlignedBB aabb = new AxisAlignedBB(pos.func_177982_a(-2, 0, -2), pos.func_177982_a(2, 3, 2));
            boolean recycler = false;
            boolean powerBank = false;
            EntityItem reyclerEntity = null;
            EntityItem bankEntity = null;
            for (EntityItem itemEntity : worldIn.func_72872_a(EntityItem.class, aabb)) {
                Item testItem = itemEntity.func_92059_d().func_77973_b();
                if (testItem == ItemInit.clovinitePowerBank && !powerBank) {
                    if (itemEntity.func_92059_d().func_190916_E() >= 10) {
                        bankEntity = itemEntity;
                        powerBank = true;
                    }
                } else if (testItem == ItemInit.electronRecycler && !recycler && itemEntity.func_92059_d().func_190916_E() >= 20) {
                    reyclerEntity = itemEntity;
                    recycler = true;
                }
            }
            if (recycler && powerBank) {
                EntityItem hubItem = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.clovinitePowerHub));
                hubItem.field_70159_w = 0.0d;
                hubItem.field_70181_x = 0.0d;
                hubItem.field_70179_y = 0.0d;
                worldIn.func_72838_d(hubItem);
                worldIn.func_184133_a((EntityPlayer) null, pos.func_177984_a(), SoundInit.SPECIAL_CRAFT, SoundCategory.BLOCKS, 2.0f, 1.0f);
                bankEntity.func_92059_d().func_190918_g(10);
                reyclerEntity.func_92059_d().func_190918_g(20);
                for (BlockPos rep : rememberPos) {
                    worldIn.func_175656_a(rep, BlockInit.hyperGeneratorUnpowered.func_176223_P());
                }
            }
        }
    }
}
