package xol.lostinfinity.mob.entity.galaxy;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockAstroBarrier;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityGalaxyBlast;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityGalaxySpire extends EntityMob implements IMaxAttack {
    private int crystalsGiven;
    private int challengeState;
    private int itemType;
    private int gameStyle;
    private boolean elite_mode;
    public EntityGalaxySpire(World worldIn) {
        super(worldIn);
        this.crystalsGiven = 0;
        this.challengeState = 0;
        this.itemType = 0;
        this.gameStyle = 0;
        this.elite_mode = false;
        func_70105_a(3.5f, 6.0f);
        func_184224_h(true);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void setItemDrop(int d) {
        this.itemType = d;
    }
    public void setElite() {
        this.elite_mode = true;
    }
    public boolean getElite() {
        return this.elite_mode;
    }
    public void setGameStyle(int style) {
        this.gameStyle = style;
    }
    public int getGameStyle() {
        return this.gameStyle;
    }
    public boolean func_70104_M() {
        return false;
    }
    private AxisAlignedBB getMyAABB() {
        switch (this.gameStyle) {
            case 1:
                return GalaxyCoordinates.getBlueAABB();
            case 2:
                return GalaxyCoordinates.getGreenAABB();
            case 3:
                return GalaxyCoordinates.getPinkAABB();
            case TileEntityFusionTable.BOARD_ROWS :
                return GalaxyCoordinates.getYellowAABB();
            case 5:
                return GalaxyCoordinates.getSwordAABB();
            case TileEntityFusionTable.BOARD_COLUMNS :
                return GalaxyCoordinates.getBombAABB();
            case 7:
                return GalaxyCoordinates.getKnifeAABB();
            default:
                return GalaxyCoordinates.getBlueAABB();
        }
    }
    private void messagePlayers(String str) {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
            near_pl.func_145747_a(new TextComponentString(str));
        }
    }
    private void randomizeTarget() {
        AxisAlignedBB box = getMyAABB();
        double height = box.field_72338_b + 6.0d;
        int target_choose = this.field_70146_Z.nextInt(getElite() ? 8 : 4);
        for (int bl_count = 0; bl_count < 4; bl_count++) {
            this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 1.0d, height + ((double) Math.floorDiv(bl_count, 2)), box.field_72339_c + 15.0d + ((double) (bl_count % 2))), target_choose == 0 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
        }
        for (int bl_count2 = 0; bl_count2 < 4; bl_count2++) {
            this.field_70170_p.func_175656_a(new BlockPos(box.field_72336_d - 1.0d, height + ((double) Math.floorDiv(bl_count2, 2)), box.field_72339_c + 15.0d + ((double) (bl_count2 % 2))), target_choose == 1 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
        }
        for (int bl_count3 = 0; bl_count3 < 4; bl_count3++) {
            this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 15.0d + ((double) (bl_count3 % 2)), height + ((double) Math.floorDiv(bl_count3, 2)), box.field_72339_c + 1.0d), target_choose == 2 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
        }
        for (int bl_count4 = 0; bl_count4 < 4; bl_count4++) {
            this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 15.0d + ((double) (bl_count4 % 2)), height + ((double) Math.floorDiv(bl_count4, 2)), box.field_72334_f - 1.0d), target_choose == 3 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
        }
        if (getElite()) {
            for (int bl_count5 = 0; bl_count5 < 4; bl_count5++) {
                this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 1.0d, height + ((double) Math.floorDiv(bl_count5, 2)), box.field_72339_c + 47.0d + ((double) (bl_count5 % 2))), target_choose == 4 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
            }
            for (int bl_count6 = 0; bl_count6 < 4; bl_count6++) {
                this.field_70170_p.func_175656_a(new BlockPos(box.field_72336_d - 1.0d, height + ((double) Math.floorDiv(bl_count6, 2)), box.field_72339_c + 47.0d + ((double) (bl_count6 % 2))), target_choose == 5 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
            }
            for (int bl_count7 = 0; bl_count7 < 4; bl_count7++) {
                this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 47.0d + ((double) (bl_count7 % 2)), height + ((double) Math.floorDiv(bl_count7, 2)), box.field_72339_c + 1.0d), target_choose == 6 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
            }
            for (int bl_count8 = 0; bl_count8 < 4; bl_count8++) {
                this.field_70170_p.func_175656_a(new BlockPos(box.field_72340_a + 47.0d + ((double) (bl_count8 % 2)), height + ((double) Math.floorDiv(bl_count8, 2)), box.field_72334_f - 1.0d), target_choose == 7 ? BlockInit.galDungeonTarget.func_176223_P() : BlockInit.astroSteel.func_176223_P());
            }
        }
    }
    public void func_70636_d() {
        int targetInterval;
        super.func_70636_d();
        if (func_110143_aJ() > 0.0f) {
            func_70606_j(func_110138_aP());
        }
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 15 == 0) {
                hurtFlying();
            }
            if (!getElite()) {
                targetInterval = 200 - (this.challengeState >= 1 ? 70 : 0);
            } else {
                targetInterval = 400;
            }
            if ((this.field_70173_aa + 195) % targetInterval == 0) {
                randomizeTarget();
                playSoundToPlayers(SoundInit.SPIRE_TARGET);
            }
            if (this.field_70173_aa % (40 - (this.challengeState == 3 ? 20 : 0)) == 0) {
                boolean fired = false;
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
                    if (!near_pl.func_184812_l_() && func_70032_d(near_pl) > 6.0f) {
                        func_70676_i(1.0f);
                        double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                        double makeY = this.field_70163_u + 5.0d;
                        double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                        double d2 = near_pl.field_70165_t - makeX;
                        double d3 = (0.5d + ((near_pl.func_174813_aQ().field_72338_b + near_pl.func_174813_aQ().field_72337_e) / 2.0d)) - makeY;
                        double d4 = near_pl.field_70161_v - makeZ;
                        EntityGalaxyBlast shot = new EntityGalaxyBlast(this.field_70170_p, makeX, makeY, makeZ);
                        shot.setThrower(this);
                        shot.func_70186_c(d2, d3, d4, getElite() ? 2.0f : 1.0f, 0.0f);
                        this.field_70170_p.func_72838_d(shot);
                        fired = true;
                    }
                }
                if (fired) {
                    playSoundToPlayers(SoundInit.GALAXYFIRE);
                }
            }
        }
    }
    private void playSoundToPlayers(SoundEvent sound) {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl.func_180425_c(), sound, SoundCategory.MASTER, 1.0f, 1.0f);
        }
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.func_184586_b(hand);
        if (itemstack.func_77973_b().equals(ItemInit.chargedGalaxyCrystal)) {
            clearGroundCrystals();
            if (!player.func_184812_l_()) {
                for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
                    if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.chargedGalaxyCrystal) {
                        player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
                    }
                }
            }
            if (!player.field_70170_p.field_72995_K) {
                playSoundToPlayers(SoundEvents.field_187626_cN);
                this.crystalsGiven++;
                int rate = this.elite_mode ? 4 : 2;
                if (this.crystalsGiven % rate == 0) {
                    playSoundToPlayers(SoundInit.SPIRE_DIFFICULTY);
                    this.challengeState++;
                    handleDifficulty();
                    return true;
                }
                return true;
            }
            return true;
        }
        return false;
    }
    public void winChallenge() {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
                BlockPos teleport_back = GalaxyCoordinates.galaxyDungeonEntry();
                near_pl.func_70634_a(teleport_back.func_177958_n(), teleport_back.func_177956_o(), teleport_back.func_177952_p());
                near_pl.field_70170_p.func_184133_a((EntityPlayer) null, new BlockPos(teleport_back), SoundEvents.field_187802_ec, SoundCategory.MASTER, 2.0f, 1.0f);
                if (!near_pl.func_191521_c(new ItemStack(getMyItem(), 1))) {
                    EntityItem reward = new EntityItem(this.field_70170_p, near_pl.field_70165_t, near_pl.field_70163_u, near_pl.field_70161_v, new ItemStack(getMyItem()));
                    reward.field_70159_w = 0.0d;
                    reward.field_70181_x = 0.0d;
                    reward.field_70179_y = 0.0d;
                    this.field_70170_p.func_72838_d(reward);
                }
            }
            func_70106_y();
        }
    }
    private void handleDifficulty() {
        switch (this.challengeState) {
            case 1:
                if (!getElite()) {
                    messagePlayers(TextFmt.Italic + "Targets now switch faster.");
                } else {
                    summonLaserSpires();
                    messagePlayers(TextFmt.Italic + "Laser Spires have been summoned.");
                }
                break;
            case 2:
                removeFloor();
                messagePlayers(TextFmt.Italic + "The floor has been removed.");
                break;
            case 3:
                if (!getElite()) {
                    messagePlayers(TextFmt.Italic + "The Spire now fires faster.");
                } else {
                    upgradeLaserSpires();
                    messagePlayers(TextFmt.Italic + "EVERYTHING now fires faster.");
                }
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                winChallenge();
                break;
        }
    }
    private void summonLaserSpires() {
        AxisAlignedBB aabb = getMyAABB();
        for (int i = 0; i < 4; i++) {
            EntityLaserSpire spire = new EntityLaserSpire(this.field_70170_p);
            spire.func_70107_b(aabb.field_72340_a + 16.0d + (32.0d * Math.floor(i / 2)), aabb.field_72338_b + 9.0d, aabb.field_72339_c + 16.0d + ((double) (32 * (i % 2))));
            spire.setGameStyle(this.gameStyle);
            this.field_70170_p.func_72838_d(spire);
        }
    }
    private void upgradeLaserSpires() {
        for (EntityLaserSpire spire : this.field_70170_p.func_72872_a(EntityLaserSpire.class, getMyAABB())) {
            spire.setFastFire();
        }
    }
    private void removeFloor() {
        AxisAlignedBB box = getMyAABB();
        int size = 32;
        if (getElite()) {
            size = 64;
        }
        for (int blx = 0; blx < size; blx++) {
            for (int blz = 0; blz < size; blz++) {
                BlockPos pos = new BlockPos(box.field_72340_a + ((double) blx), box.field_72338_b, box.field_72339_c + ((double) blz));
                if (this.field_70170_p.func_180495_p(pos).func_177230_c() instanceof BlockAstroBarrier) {
                    this.field_70170_p.func_175698_g(pos);
                }
            }
        }
    }
    private void hurtFlying() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
            if (!near_pl.func_184812_l_() && near_pl.field_71075_bZ.field_75100_b) {
                IMaxAttack.dealTrueDamage(this, near_pl, near_pl.func_110138_aP() - 1.0f);
                near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "FLIGHT DETECTED. DEALING SEVERE DAMAGE"));
            }
        }
    }
    private void clearGroundCrystals() {
        for (EntityItem item : this.field_70170_p.func_72872_a(EntityItem.class, getMyAABB())) {
            if (item.func_92059_d().func_77973_b().equals(ItemInit.chargedGalaxyCrystal)) {
                item.func_70106_y();
            }
        }
    }
    private Item getMyItem() {
        switch (this.gameStyle) {
            case 1:
                switch (this.itemType) {
                    case 0:
                        return ItemInit.moonglowBlade;
                    case 1:
                        return ItemInit.moonglowWand;
                    case 2:
                        return ItemInit.moonglowThrowingKnife;
                    case 3:
                        return ItemInit.moonglowBomb;
                }
            case 2:
                switch (this.itemType) {
                    case 0:
                        return ItemInit.aurorusBlade;
                    case 1:
                        return ItemInit.aurorusWand;
                    case 2:
                        return ItemInit.aurorusThrowingKnife;
                    case 3:
                        return ItemInit.aurorusBomb;
                }
            case 3:
                switch (this.itemType) {
                    case 0:
                        return ItemInit.novacronBlade;
                    case 1:
                        return ItemInit.novacronWand;
                    case 2:
                        return ItemInit.novacronThrowingKnife;
                    case 3:
                        return ItemInit.novacronBomb;
                }
            case TileEntityFusionTable.BOARD_ROWS :
                switch (this.itemType) {
                    case 0:
                        return ItemInit.starfireBlade;
                    case 1:
                        return ItemInit.starfireWand;
                    case 2:
                        return ItemInit.starfireThrowingKnife;
                    case 3:
                        return ItemInit.starfireBomb;
                }
            case 5:
                return ItemInit.incarnationOfTheSword;
            case TileEntityFusionTable.BOARD_COLUMNS :
                return ItemInit.incarnationOfTheBomb;
            case 7:
                return ItemInit.incarnationOfTheKnife;
        }
        return ItemInit.starfireBlade;
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("Crystals", this.crystalsGiven);
        tag.func_74768_a("ChallengeState", this.challengeState);
        tag.func_74768_a("ItemStyle", this.itemType);
        tag.func_74768_a("GameStyle", this.gameStyle);
        tag.func_74757_a("EliteMode", this.elite_mode);
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.crystalsGiven = tag.func_74762_e("Crystals");
        this.challengeState = tag.func_74762_e("ChallengeState");
        this.itemType = tag.func_74762_e("ItemStyle");
        this.gameStyle = tag.func_74762_e("GameStyle");
        this.elite_mode = tag.func_74767_n("EliteMode");
    }
    protected boolean func_70692_ba() {
        return false;
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
}
