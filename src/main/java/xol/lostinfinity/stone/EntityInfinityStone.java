package xol.lostinfinity.stone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
public class EntityInfinityStone extends EntityImmaterial {
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityInfinityStone.class, DataSerializers.field_187191_a);
    public EntityInfinityStone(World worldIn) {
        super(worldIn);
        func_184224_h(false);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
    }
    public byte getStoneNum() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }
    public void setStoneNum(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("StoneType", getStoneNum());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setStoneNum(tag.func_74771_c("StoneType"));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70067_L() {
        return true;
    }
    protected void func_184651_r() {
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
    private Item getStoneItem() {
        switch (getStoneNum()) {
            case 0:
                return ItemInit.dualityCube;
            case 1:
                return ItemInit.aspirationCube;
            case 2:
                return ItemInit.corruptionCube;
            case 3:
                return ItemInit.ingenuityCube;
            case TileEntityFusionTable.BOARD_ROWS :
                return ItemInit.misdirectionCube;
            case 5:
                return ItemInit.vengeanceCube;
            case TileEntityFusionTable.BOARD_COLUMNS :
                return ItemInit.retrospectionCube;
            case 7:
                return ItemInit.dreadCube;
            case 8:
                return ItemInit.anxietyCube;
            case 9:
                return ItemInit.impositionCube;
            case ItemHeadCollector.CHARGE_LIMIT :
                return ItemInit.ambitionCube;
            case 11:
                return ItemInit.perceptionCube;
            case 12:
                return ItemInit.anticipationCube;
            case 13:
                return ItemInit.resolveCube;
            case 14:
                return ItemInit.crueltyCube;
            default:
                return ItemInit.dualityCube;
        }
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        func_70106_y();
        Item stone_item = getStoneItem();
        if (!player.func_191521_c(new ItemStack(stone_item)) && !this.field_70170_p.field_72995_K) {
            func_145779_a(stone_item, 1);
        }
        if (!this.field_70170_p.field_72995_K && getStoneNum() >= 13) {
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Light_Purple) + "Looming voice: Yet another stone " + player.func_70005_c_() + "... And if that wasn't enough, your helping a dark force we've locked away. Don't push your luck " + player.func_70005_c_() + ", you don't want to deal with us."));
            return true;
        }
        return true;
    }
}
