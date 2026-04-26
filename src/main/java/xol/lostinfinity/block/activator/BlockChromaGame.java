package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityChromaGame;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockChromaGame extends BlockBasic implements ITileEntityProvider {
    public BlockChromaGame(String name) {
        super(name);
    }
    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.frostedLog);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (validInput(playerIn.func_184586_b(hand))) {
                double closest = 99999.0d;
                double nextClosest = 99999.1d;
                AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177963_a(-10.0d, -10.0d, -10.0d), pos.func_177963_a(10.0d, 10.0d, 10.0d));
                List<EntityPlayer> players = worldIn.func_72872_a(EntityPlayer.class, checkBox);
                EntityPlayer first = null;
                EntityPlayer second = null;
                for (EntityPlayer player : players) {
                    double dist = player.func_174818_b(pos);
                    if (player.func_174818_b(pos) < closest) {
                        first = player;
                        closest = dist;
                    }
                }
                players.remove(first);
                for (EntityPlayer player2 : players) {
                    double dist2 = player2.func_174818_b(pos);
                    if (player2.func_174818_b(pos) < nextClosest) {
                        second = player2;
                        nextClosest = dist2;
                    }
                }
                if (first != null && second != null) {
                    if (!worldIn.field_72995_K) {
                        setPlayers(worldIn, pos, first, second);
                        reset(pos, worldIn, playerIn, hand, facing, hitX, hitY, hitZ);
                    }
                    playerIn.func_184586_b(hand).func_190918_g(1);
                    return true;
                }
                return true;
            }
            if (!worldIn.field_72995_K) {
                BlockPos ref = pos.func_177982_a(0, 0, 0);
                Vec3i dir = findTileDir(worldIn, ref);
                int xDir = dir.func_177958_n();
                int zDir = dir.func_177952_p();
                BlockPos ref2 = ref.func_177971_a(dir);
                boolean flag = true;
                for (int i = 0; i < 6; i++) {
                    int j = 0;
                    while (true) {
                        if (j < 6) {
                            BlockPos tile = ref2.func_177982_a(xDir * i, 0, zDir * j);
                            if (worldIn.func_180495_p(tile).equals(BlockInit.chromaTile.func_176203_a(3))) {
                                j++;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                if (flag) {
                    ItemStack mapstack = new ItemStack(ItemInit.resolveMap);
                    mapstack.func_77982_d(new NBTTagCompound());
                    mapstack.func_77978_p().func_74768_a("MapEntityType", worldIn.field_73012_v.nextInt(8));
                    mapstack.func_77978_p().func_74768_a("MapEntityNum", 2 + worldIn.field_73012_v.nextInt(4));
                    EntityItem geoloc = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, mapstack);
                    geoloc.field_70159_w = 0.0d;
                    geoloc.field_70181_x = 0.0d;
                    geoloc.field_70179_y = 0.0d;
                    worldIn.func_72838_d(geoloc);
                    reset(pos, worldIn, playerIn, hand, facing, hitX, hitY, hitZ);
                    setPlayers(worldIn, pos, null, null);
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MACHINE_CRAFT, SoundCategory.MASTER, 1.0f, 1.0f);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private void setPlayers(World worldIn, BlockPos pos, EntityPlayer first, EntityPlayer second) {
        if (worldIn.func_175625_s(pos) != null && (worldIn.func_175625_s(pos) instanceof TileEntityChromaGame)) {
            TileEntityChromaGame tileentity = (TileEntityChromaGame) worldIn.func_175625_s(pos);
            tileentity.setPlayers(first, second);
        }
    }
    private EntityPlayer getRandomPlayer(World worldIn, BlockPos pos) {
        if (worldIn.func_175625_s(pos) != null && (worldIn.func_175625_s(pos) instanceof TileEntityChromaGame)) {
            TileEntityChromaGame tileentity = (TileEntityChromaGame) worldIn.func_175625_s(pos);
            boolean rand = worldIn.field_73012_v.nextBoolean();
            if (rand) {
                return tileentity.getFirst();
            }
            return tileentity.getSecond();
        }
        return null;
    }
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void reset(net.minecraft.util.math.BlockPos r12, net.minecraft.world.World r13, net.minecraft.entity.player.EntityPlayer r14, net.minecraft.util.EnumHand r15, net.minecraft.util.EnumFacing r16, float r17, float r18, float r19) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.block.activator.BlockChromaGame.reset(net.minecraft.util.math.BlockPos, net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand, net.minecraft.util.EnumFacing, float, float, float):void");
    }
    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(1, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.chromaTile)) {
                return dir;
            }
        }
        return null;
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    public boolean func_149716_u() {
        return true;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityChromaGame();
    }
}
