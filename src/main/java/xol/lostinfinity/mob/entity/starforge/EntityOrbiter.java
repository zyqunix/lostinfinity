package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.harvest.BlockCatatoniteOre;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityOrbiter extends EntityFloatingBase implements IMaxAttack {
    private int recentDash;
    boolean hitBlock;
    private Vec3d dir;
    private BlockPos recentlyKnocked;
    public EntityOrbiter(World worldIn) {
        super(worldIn);
        this.recentDash = 0;
        this.hitBlock = false;
        this.dir = null;
        this.recentlyKnocked = null;
        func_70105_a(1.85f, 1.85f);
        this.rawFlySpeed = 0.6f;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (this.recentDash > 0) {
                IMaxAttack.dealMaxHealth(this, func_70638_az(), 1);
                return true;
            }
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.recentDash > 0) {
                this.recentDash--;
            }
            if (func_70638_az() != null) {
                if (!this.hitBlock && this.recentDash > 0) {
                    if (this.field_70170_p.func_180495_p(func_180425_c()).func_177230_c() == BlockInit.bumblefruit && (this.recentlyKnocked == null || !this.recentlyKnocked.equals(func_180425_c()))) {
                        this.recentlyKnocked = func_180425_c();
                        EntityItem blossom = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemInit.bumbleBlossom, 1));
                        blossom.field_70159_w = this.field_70159_w / 2.0d;
                        blossom.field_70181_x = this.field_70181_x / 2.0d;
                        blossom.field_70179_y = this.field_70179_y / 2.0d;
                        this.field_70170_p.func_72838_d(blossom);
                    }
                    Vec3d forward = this.dir;
                    BlockPos forwardBlock = new BlockPos(this.field_70165_t + (forward.field_72450_a * 1.85d), this.field_70163_u + (forward.field_72448_b * 1.85d) + 0.5d, this.field_70161_v + (forward.field_72449_c * 1.85d));
                    IBlockState state = this.field_70170_p.func_180495_p(forwardBlock);
                    Block block = state.func_177230_c();
                    if (block instanceof BlockCatatoniteOre) {
                        int meta = ((BlockCatatoniteOre) block).func_176201_c(state);
                        if (meta < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock, ((BlockCatatoniteOre) block).func_176203_a(meta + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state2 = this.field_70170_p.func_180495_p(forwardBlock.func_177977_b());
                    Block block2 = state2.func_177230_c();
                    if (block2 instanceof BlockCatatoniteOre) {
                        int meta2 = ((BlockCatatoniteOre) block2).func_176201_c(state2);
                        if (meta2 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177977_b(), ((BlockCatatoniteOre) block2).func_176203_a(meta2 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state3 = this.field_70170_p.func_180495_p(forwardBlock.func_177984_a());
                    Block block3 = state3.func_177230_c();
                    if (block3 instanceof BlockCatatoniteOre) {
                        int meta3 = ((BlockCatatoniteOre) block3).func_176201_c(state3);
                        if (meta3 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177984_a(), ((BlockCatatoniteOre) block3).func_176203_a(meta3 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state4 = this.field_70170_p.func_180495_p(forwardBlock.func_177974_f());
                    Block block4 = state4.func_177230_c();
                    if (block4 instanceof BlockCatatoniteOre) {
                        int meta4 = ((BlockCatatoniteOre) block4).func_176201_c(state4);
                        if (meta4 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177974_f(), ((BlockCatatoniteOre) block4).func_176203_a(meta4 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state5 = this.field_70170_p.func_180495_p(forwardBlock.func_177976_e());
                    Block block5 = state5.func_177230_c();
                    if (block5 instanceof BlockCatatoniteOre) {
                        int meta5 = ((BlockCatatoniteOre) block5).func_176201_c(state5);
                        if (meta5 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177976_e(), ((BlockCatatoniteOre) block5).func_176203_a(meta5 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state6 = this.field_70170_p.func_180495_p(forwardBlock.func_177976_e().func_177984_a());
                    Block block6 = state6.func_177230_c();
                    if (block6 instanceof BlockCatatoniteOre) {
                        int meta6 = ((BlockCatatoniteOre) block6).func_176201_c(state6);
                        if (meta6 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177976_e().func_177984_a(), ((BlockCatatoniteOre) block6).func_176203_a(meta6 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state7 = this.field_70170_p.func_180495_p(forwardBlock.func_177976_e().func_177977_b());
                    Block block7 = state7.func_177230_c();
                    if (block7 instanceof BlockCatatoniteOre) {
                        int meta7 = ((BlockCatatoniteOre) block7).func_176201_c(state7);
                        if (meta7 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177976_e().func_177977_b(), ((BlockCatatoniteOre) block7).func_176203_a(meta7 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state8 = this.field_70170_p.func_180495_p(forwardBlock.func_177974_f().func_177984_a());
                    Block block8 = state8.func_177230_c();
                    if (block8 instanceof BlockCatatoniteOre) {
                        int meta8 = ((BlockCatatoniteOre) block8).func_176201_c(state8);
                        if (meta8 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177974_f().func_177984_a(), ((BlockCatatoniteOre) block8).func_176203_a(meta8 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                    IBlockState state9 = this.field_70170_p.func_180495_p(forwardBlock.func_177974_f().func_177977_b());
                    Block block9 = state9.func_177230_c();
                    if (block9 instanceof BlockCatatoniteOre) {
                        int meta9 = ((BlockCatatoniteOre) block9).func_176201_c(state9);
                        if (meta9 < 3) {
                            this.field_70170_p.func_175656_a(forwardBlock.func_177974_f().func_177977_b(), ((BlockCatatoniteOre) block9).func_176203_a(meta9 + 1));
                            this.field_70170_p.func_184133_a((EntityPlayer) null, forwardBlock, SoundInit.ROCK_TUMBLE, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                        }
                        this.hitBlock = true;
                    }
                }
                int tickRem = this.field_70173_aa % 120;
                if (tickRem <= 50) {
                    if (this.dir != null) {
                        func_70024_g(this.dir.field_72450_a / 6.0d, this.dir.field_72448_b / 6.0d, this.dir.field_72449_c / 6.0d);
                        this.field_70133_I = true;
                    }
                    if (tickRem == 0) {
                        this.dir = func_70638_az().func_174791_d().func_178788_d(func_174791_d()).func_72432_b();
                        this.recentDash = 50;
                        this.hitBlock = false;
                        func_184185_a(SoundInit.ORBITER_ATTACK, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                    }
                }
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public int getRecentDash() {
        return this.recentDash;
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.ORBITER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.ORBITER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.ORBITER_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 4;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_ORBITER;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
