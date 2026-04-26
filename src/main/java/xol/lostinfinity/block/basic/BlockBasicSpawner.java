package xol.lostinfinity.block.basic;

import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.load.CustomLazyLoad;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicSpawner.class */
public class BlockBasicSpawner extends BlockMobSpawner {
    protected String mobName;
    private final LazyLoadBase<ITileEntityProvider> spawnerReference;

    public BlockBasicSpawner(String name, String mobName) {
        this.mobName = mobName;
        func_149663_c(name);
        setRegistryName(Reference.MODID, name);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149711_c(5.0f);
        func_149675_a(true);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
        this.spawnerReference = new CustomLazyLoad(() -> {
            ITileEntityProvider iTileEntityProvider = (Block) ForgeRegistries.BLOCKS.getValue(new ResourceLocation("mob_spawner"));
            if (iTileEntityProvider instanceof ITileEntityProvider) {
                return iTileEntityProvider;
            }
            return null;
        });
    }

    public TileEntity func_149915_a(World world, int par1) {
        TileEntity spawner = null;
        ITileEntityProvider entityProvider = (ITileEntityProvider) this.spawnerReference.func_179281_c();
        if (entityProvider != null) {
            spawner = entityProvider.func_149915_a(world, par1);
            if (spawner != null) {
                insertModData(spawner);
            }
        }
        return spawner;
    }

    private void insertModData(@Nonnull TileEntity spawner) {
        NBTTagCompound spawnerNBT = spawner.func_189515_b(new NBTTagCompound());
        spawnerNBT.func_74778_a("SpawnPotentials", this.mobName);
        NBTTagCompound compound2 = new NBTTagCompound();
        compound2.func_74778_a("id", "lostinfinity:" + this.mobName);
        spawnerNBT.func_74782_a("SpawnData", compound2);
        spawner.func_145839_a(spawnerNBT);
        spawner.func_70296_d();
    }
}
