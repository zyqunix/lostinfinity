package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.misc.ItemGeolocationOrb;
public class BlockGeolocator extends Block {
    private int blockType;
    public BlockGeolocator(String name, int locatorType) {
        super(Material.field_151573_f);
        this.blockType = 0;
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        this.blockType = locatorType;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (this.blockType != 4) {
                if (playerIn.func_184586_b(hand).func_77973_b() instanceof ItemGeolocationOrb) {
                    ItemStack stack = playerIn.func_184586_b(hand);
                    boolean flag = false;
                    if (stack.func_77942_o() && stack.func_77978_p().func_74762_e("geolocator") == this.blockType) {
                        if (stack.func_77978_p().func_74762_e("geocount") == 1) {
                            playerIn.func_184611_a(hand, new ItemStack(ItemInit.geocoordinatedOrb, 2));
                            if (!worldIn.field_72995_K) {
                                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187626_cN, SoundCategory.MASTER, 1.0f, 1.0f);
                            }
                        } else {
                            stack.func_77978_p().func_74768_a("geocount", stack.func_77978_p().func_74762_e("geocount") - 1);
                            if (!worldIn.field_72995_K) {
                                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SCANNER, SoundCategory.MASTER, 1.0f, 1.0f);
                            }
                            boolean run = true;
                            int newType = -1;
                            while (run) {
                                newType = worldIn.field_73012_v.nextInt(4);
                                if (newType != this.blockType) {
                                    run = false;
                                }
                            }
                            stack.func_77978_p().func_74768_a("geolocator", newType);
                        }
                    } else {
                        flag = true;
                    }
                    if (flag) {
                        if (!worldIn.field_72995_K) {
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Gray + "The orb smashes as you attempt to sync it with the geolocator."));
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187561_bM, SoundCategory.MASTER, 1.0f, 1.0f);
                        }
                        playerIn.func_184611_a(hand, ItemStack.field_190927_a);
                        return true;
                    }
                    return true;
                }
                return true;
            }
            if (playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.deviantGlobe)) {
                playerIn.func_184586_b(hand).func_190918_g(1);
                if (!worldIn.field_72995_K) {
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
                    ItemStack geostack = new ItemStack(ItemInit.geolocationOrb);
                    geostack.func_77982_d(new NBTTagCompound());
                    geostack.func_77978_p().func_74768_a("geocount", 3);
                    geostack.func_77978_p().func_74768_a("geolocator", worldIn.field_73012_v.nextInt(4));
                    EntityItem geoloc = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, geostack);
                    geoloc.field_70159_w = 0.0d;
                    geoloc.field_70181_x = 0.0d;
                    geoloc.field_70179_y = 0.0d;
                    worldIn.func_72838_d(geoloc);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
