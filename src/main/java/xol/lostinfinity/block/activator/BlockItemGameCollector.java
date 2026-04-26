package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
public class BlockItemGameCollector extends Block {
    public BlockItemGameCollector(String name) {
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
        if (!worldIn.field_72995_K) {
            int crates = 0;
            for (int x = -4; x <= 4; x++) {
                for (int z = -4; z <= 4; z++) {
                    if (worldIn.func_180495_p(pos.func_177982_a(x, 3, z)).func_177230_c() == BlockInit.labyrinthLamp && worldIn.func_180495_p(pos.func_177982_a(x, 2, z)).func_177230_c() == BlockInit.itemChallengeCrate) {
                        crates++;
                    }
                }
            }
            ItemStack held = playerIn.func_184614_ca();
            Item resultFromHold = resultMap(held.func_77973_b());
            if (crates == 4 && resultFromHold != null) {
                held.func_190918_g(1);
                ItemStack mapstack = new ItemStack(resultFromHold);
                mapstack.func_77982_d(new NBTTagCompound());
                mapstack.func_77978_p().func_74768_a("MapEntityType", worldIn.field_73012_v.nextInt(8));
                mapstack.func_77978_p().func_74768_a("MapEntityNum", 2 + worldIn.field_73012_v.nextInt(4));
                EntityItem geoloc = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, mapstack);
                geoloc.field_70159_w = 0.0d;
                geoloc.field_70181_x = 0.0d;
                geoloc.field_70179_y = 0.0d;
                worldIn.func_72838_d(geoloc);
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MACHINE_CRAFT, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    private Item resultMap(Item held) {
        if (held == ItemInit.webbedIdol) {
            return ItemInit.ambitionMap;
        }
        if (held == ItemInit.conductiveBar) {
            return ItemInit.anticipationMap;
        }
        if (held == ItemInit.frostedLog) {
            return ItemInit.resolveMap;
        }
        if (held == ItemInit.relicOfTheMirage) {
            return ItemInit.perceptionMap;
        }
        if (held == ItemInit.jarOfBlood) {
            return ItemInit.crueltyMap;
        }
        return null;
    }
}
