package xol.lostinfinity.mob.entity.boss;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityAtlasAttack;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityAtlasSpire extends EntityMob implements IMaxAttack {
    private static final DataParameter<Integer> CRYSTAL_STYLE = EntityDataManager.func_187226_a(EntityAtlasCrystal.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ROUNDS_PASSED = EntityDataManager.func_187226_a(EntityAtlasCrystal.class, DataSerializers.field_187192_b);
    public EntityAtlasSpire(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 6.0f);
        func_184224_h(true);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(CRYSTAL_STYLE, 0);
        this.field_70180_af.func_187214_a(ROUNDS_PASSED, 0);
    }
    public int getStyle() {
        return ((Integer) this.field_70180_af.func_187225_a(CRYSTAL_STYLE)).intValue();
    }
    public void setStyle(int f) {
        this.field_70180_af.func_187227_b(CRYSTAL_STYLE, Integer.valueOf(f));
    }
    public void passRound() {
        int roundsSoFar = getRounds();
        this.field_70180_af.func_187227_b(ROUNDS_PASSED, Integer.valueOf(roundsSoFar + 1));
        int style = 0;
        boolean run = true;
        while (run) {
            style = this.field_70146_Z.nextInt(4);
            if (style != getStyle()) {
                run = false;
            }
        }
        setStyle(style);
        func_184185_a(SoundInit.SPIRE_TARGET, 1.0f, 1.0f);
        messageRound(style, roundsSoFar + 1);
    }
    private void messageRound(int newStyle, int round) {
        String msg;
        switch (newStyle) {
            case 0:
                msg = "Spawning Crystals Rapidly... If more than 15 exist, you die.";
                break;
            case 1:
                msg = "Spawning Crystals... Dealing 5% HP Damage Per Crystal.";
                break;
            case 2:
                msg = "Repeatedly dealing damage. SURVIVE.";
                break;
            case 3:
                msg = "Rapid fire mode enabled";
                break;
            default:
                msg = "Error";
                break;
        }
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "ROUND: " + round));
            near_pl.func_145747_a(new TextComponentString(TextFmt.Aqua + msg));
        }
    }
    public int getRounds() {
        return ((Integer) this.field_70180_af.func_187225_a(ROUNDS_PASSED)).intValue();
    }
    private void setRounds(int r) {
        this.field_70180_af.func_187227_b(ROUNDS_PASSED, Integer.valueOf(r));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("SpawnForm", getStyle());
        tag.func_74768_a("Rounds", getRounds());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setStyle(tag.func_74762_e("SpawnForm"));
        setRounds(tag.func_74762_e("Rounds"));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50000.0d);
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }
    private void fireBlast() {
        if (!this.field_70170_p.field_72995_K) {
            boolean fired = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (!near_pl.func_184812_l_()) {
                    fired = true;
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 8.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityAtlasAttack shot = new EntityAtlasAttack(this.field_70170_p, this);
                    shot.func_70186_c(d2, d3, d4, 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            if (fired) {
                func_184185_a(SoundInit.GALAXYFIRE, 2.0f, 1.0f);
            }
        }
    }
    private void spawnCrystals(int count) {
        for (int crystal = 0; crystal < count; crystal++) {
            EntityAtlasCrystal crystal_spawn = new EntityAtlasCrystal(this.field_70170_p);
            boolean inAir = false;
            int x_pos = 0;
            int z_pos = 0;
            while (!inAir) {
                x_pos = this.field_70146_Z.nextInt(30);
                z_pos = this.field_70146_Z.nextInt(80);
                if (this.field_70170_p.func_175623_d(new BlockPos(0 + x_pos, 63, (-140) + z_pos))) {
                    inAir = true;
                }
            }
            crystal_spawn.func_70107_b(0 + x_pos, 63.0d, (-140) + z_pos);
            this.field_70170_p.func_72838_d(crystal_spawn);
        }
    }
    private int getCrystalCount() {
        int num = 0;
        for (EntityAtlasCrystal entityAtlasCrystal : this.field_70170_p.func_72872_a(EntityAtlasCrystal.class, getArenaAABB())) {
            num++;
        }
        return num;
    }
    public void func_70636_d() {
        int count;
        super.func_70636_d();
        if (func_110143_aJ() > 0.0f) {
            func_70606_j(func_110138_aP());
            if (!this.field_70170_p.field_72995_K) {
                if (this.field_70173_aa % 40 == 0) {
                    fireBlast();
                }
                switch (getStyle()) {
                    case 0:
                        if (this.field_70173_aa % 60 == 0) {
                            if (getCrystalCount() >= 15) {
                                for (EntityPlayer player : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                                    IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) player, 1, 20.0f);
                                    player.func_145747_a(new TextComponentString(TextFmt.Red + "Atlas Spire: DEALING SEVERE DAMAGE"));
                                }
                            } else {
                                spawnCrystals(this.field_70146_Z.nextInt(2) + 2);
                            }
                        }
                        break;
                    case 1:
                        if (this.field_70173_aa % 40 == 0 && (count = getCrystalCount()) > 0) {
                            for (EntityPlayer player2 : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                                IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) player2, 20, count);
                                player2.func_145747_a(new TextComponentString(TextFmt.Red + "Atlas Spire: Dealing " + (5 * count) + "% health damage."));
                            }
                        }
                        if (this.field_70173_aa % 60 == 0 && getCrystalCount() <= 15) {
                            spawnCrystals(this.field_70146_Z.nextInt(1) + 1);
                        }
                        break;
                    case 2:
                        if (this.field_70173_aa % 60 == 0) {
                            for (EntityPlayer player3 : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                                IMaxAttack.dealMaxHealth(this, player3, 3);
                                player3.func_145747_a(new TextComponentString(TextFmt.Red + "Atlas Spire: SURVIVE."));
                            }
                        }
                        break;
                    case 3:
                        if ((this.field_70173_aa + 5) % 20 == 0) {
                            fireBlast();
                        }
                        break;
                }
                if ((this.field_70173_aa + 395) % 400 == 0) {
                    if (getRounds() < 15) {
                        passRound();
                        return;
                    }
                    Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
                    while (it.hasNext()) {
                        ((EntityPlayer) it.next()).func_145747_a(new TextComponentString(TextFmt.Gold + "Atlas Spire: Challenge Complete."));
                    }
                    func_70106_y();
                    EntityInfinityStone imposStone = new EntityInfinityStone(this.field_70170_p);
                    imposStone.setStoneNum((byte) 9);
                    imposStone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(imposStone);
                    func_145779_a(ItemInit.arenaCard, 1);
                }
            }
        }
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }
    protected SoundEvent func_184615_bR() {
        return null;
    }
    public boolean func_70104_M() {
        return false;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
}
