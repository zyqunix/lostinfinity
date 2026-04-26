package xol.lostinfinity.item.activate;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockRemoteControl;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.block.tileentity.TileEntityRemoteControl;
import xol.lostinfinity.common.events.EventsMurk;
import xol.lostinfinity.dimension.data.BlockData;
import xol.lostinfinity.dimension.data.CustomWorldSavedData;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemRemoteControl;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemDarkworldRemote extends ItemRemoteControl {
    private static final int radius = 60;
    public ItemDarkworldRemote(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemRemoteControl, xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2000;
    }
    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public BlockRemoteControl getControlBlock() {
        return (BlockRemoteControl) BlockInit.darkworldConverter;
    }
    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void tickEffect(TileEntityRemoteControl te, World world, BlockPos pos, EntityPlayer owner) {
        if (!world.field_72995_K && te.getExisted() % 10 == 0 && owner.func_70011_f(te.func_174877_v().func_177958_n(), te.func_174877_v().func_177956_o(), te.func_174877_v().func_177952_p()) < 60.0d) {
            owner.func_70690_d(new PotionEffect(PotionInit.OTHERWORLDLY, 30));
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void toggleEffect(TileEntityRemoteControl te, World worldIn, BlockPos checkpos, EntityPlayer owner, boolean active) {
        CustomWorldSavedData savedData = CustomWorldSavedData.get(worldIn);
        ArrayList<BlockData> blockData = savedData.getBlockData(te.func_174877_v());
        if (blockData != null) {
            for (BlockData data : blockData) {
                BlockPos pos = data.getPos();
                int meta = data.getMeta();
                int id = data.getId();
                worldIn.func_175656_a(pos, Block.func_149729_e(id).func_176203_a(meta));
            }
            savedData.clearBlockData(te.func_174877_v());
        }
        if (!active && blockData != null) {
            worldIn.func_184133_a((EntityPlayer) null, owner.func_180425_c(), SoundInit.SLOW_TRANSITION, SoundCategory.PLAYERS, 1.5f, 1.0f);
            return;
        }
        if (active) {
            worldIn.func_184133_a((EntityPlayer) null, owner.func_180425_c(), SoundInit.INSTANT_TRANSITION, SoundCategory.PLAYERS, 1.5f, 1.0f);
            ArrayList<BlockData> newData = new ArrayList<>();
            for (int i = -60; i <= radius; i++) {
                for (int j = -60; j <= radius; j++) {
                    if ((i * i) + (j * j) < 3600) {
                        for (int k = 0; k <= 90.0f; k++) {
                            BlockPos pos2 = checkpos.func_177982_a(i, k, j);
                            IBlockState state = worldIn.func_180495_p(pos2);
                            Block block = state.func_177230_c();
                            int id2 = Block.func_149682_b(block);
                            int meta2 = block.func_176201_c(state);
                            BlockData data2 = new BlockData(pos2, meta2, id2);
                            if (!worldIn.func_175623_d(pos2)) {
                                newData.add(data2);
                            }
                            if (worldIn.func_175623_d(pos2)) {
                                if (!worldIn.func_175623_d(pos2.func_177977_b()) && worldIn.field_73012_v.nextInt(50) == 0) {
                                    CustomParticleConfig config1 = new CustomParticleConfig();
                                    config1.setCount(3);
                                    config1.createInstance().setParticle(ParticleInit.EXPLOSION_BLUE).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                                    config1.createInstance().setParticle(ParticleInit.EXPLOSION_TEAL).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                                    CustomParticleConfig config2 = new CustomParticleConfig();
                                    config2.createInstance().setParticle(ParticleInit.MURK).setSpread(10.0d, 2.0d, 10.0d).setCount(7).setIgnoreRange(true);
                                    IParticleSpawner.spawnParticle(worldIn, config1, pos2.func_177958_n(), pos2.func_177956_o(), pos2.func_177952_p());
                                    IParticleSpawner.spawnParticle(worldIn, config2, pos2.func_177958_n(), pos2.func_177956_o(), pos2.func_177952_p());
                                    if (worldIn.field_73012_v.nextInt(3) == 0) {
                                        worldIn.func_184133_a((EntityPlayer) null, pos2, randomWhisper(worldIn.field_73012_v.nextInt(5)), SoundCategory.PLAYERS, 1.25f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                                    }
                                }
                            } else {
                                boolean canChange = true;
                                if ((!block.func_149686_d(state) && !(block instanceof BlockStairs) && !(block instanceof BlockSlab)) || block.func_149716_u()) {
                                    canChange = false;
                                }
                                if (canChange) {
                                    if (block.getRegistryName().toString().contains("obsidian")) {
                                        worldIn.func_175656_a(pos2, BlockInit.glowingIgneousMurkstone.func_176223_P());
                                    } else {
                                        worldIn.func_175656_a(pos2, EventsMurk.getBlockToPlace(state, pos2.func_177956_o() + k));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (newData.size() > 0) {
                savedData.setBlockData(newData, te.func_174877_v());
            }
        }
    }
    private SoundEvent randomWhisper(int i) {
        switch (i) {
            case 0:
                return SoundInit.WHISPER_1;
            case 1:
                return SoundInit.WHISPER_2;
            case 2:
                return SoundInit.WHISPER_3;
            case 3:
                return SoundInit.WHISPER_4;
            case TileEntityFusionTable.BOARD_ROWS :
                return SoundInit.WHISPER_5;
            default:
                return SoundInit.WHISPER_5;
        }
    }
}
