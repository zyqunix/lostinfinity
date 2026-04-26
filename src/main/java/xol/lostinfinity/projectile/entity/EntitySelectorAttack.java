package xol.lostinfinity.projectile.entity;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.item.weapon.ItemTheSelector;
import xol.lostinfinity.item.weapon.data.IonizerNode;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntitySelectorAttack extends Entity implements IMaxAttack {
    protected static final DataParameter<Optional<UUID>> CASTER_ID = EntityDataManager.func_187226_a(EntitySelectorAttack.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Integer> ORIGIN_ID = EntityDataManager.func_187226_a(EntitySelectorAttack.class, DataSerializers.field_187192_b);
    protected static final DataParameter<Integer> CHARACTER_DATA = EntityDataManager.func_187226_a(EntitySelectorAttack.class, DataSerializers.field_187192_b);
    private IonizerNode targets;
    private ArrayList<EntityLivingBase> visited;
    private static final double radius = 7.0d;
    public EntitySelectorAttack(World worldIn) {
        super(worldIn);
        this.targets = null;
        this.visited = null;
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        EntityPlayer caster = getCaster();
        if (caster == null) {
            if (!this.field_70170_p.field_72995_K) {
                func_70106_y();
                return;
            }
            return;
        }
        func_70107_b(caster.field_70165_t, caster.field_70163_u + ((double) (caster.field_70131_O / 2.0f)), caster.field_70161_v);
        if (this.targets == null) {
            this.visited = new ArrayList<>();
            this.targets = getTargetsNode(caster, (EntityLivingBase) this.field_70170_p.func_73045_a(getOriginID()), 0);
        } else {
            activateNodes(caster, this.targets);
        }
        if (this.field_70173_aa == 1000 && !this.field_70170_p.field_72995_K) {
            func_70106_y();
        }
    }
    private int getOriginID() {
        return ((Integer) this.field_70180_af.func_187225_a(ORIGIN_ID)).intValue();
    }
    public void setOriginID(int id) {
        this.field_70180_af.func_187227_b(ORIGIN_ID, Integer.valueOf(id));
    }
    private void activateNodes(EntityPlayer caster, IonizerNode node) {
        if (node == null) {
            return;
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa == node.getTimer() && node.getOrigin() != null) {
            IMaxAttack.dealTrueDamage(caster, node.getOrigin(), node.getOrigin().func_110138_aP() * 0.25f);
            if (node.getOrigin() instanceof EntityMultipleLives) {
                EntityMultipleLives multiTarget = node.getOrigin();
                multiTarget.takeawayNumLives(4);
            }
        }
        if (this.field_70173_aa >= node.getTimer() && this.field_70173_aa <= node.getTimer() + 10) {
            node.setActive(true);
        } else {
            node.setActive(false);
        }
        if (node.getTargets() != null) {
            for (IonizerNode target : node.getTargets()) {
                activateNodes(caster, target);
            }
        }
    }
    private IonizerNode getTargetsNode(EntityPlayer caster, EntityLivingBase origin, int count) {
        if (origin == null || origin.func_70032_d(caster) > 70.0f) {
            return null;
        }
        ArrayList<IonizerNode> targetNodes = new ArrayList<>();
        ArrayList<EntityLivingBase> toAdd = new ArrayList<>();
        AxisAlignedBB checkBox = new AxisAlignedBB(origin.func_180425_c().func_177963_a(-7.0d, -7.0d, -7.0d), origin.func_180425_c().func_177963_a(radius, radius, radius));
        for (EntityLivingBase entity : (ArrayList) this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
            String hit_name = entity.func_70005_c_().toLowerCase();
            if (!hit_name.isEmpty() && ItemTheSelector.regexMatch(getCharacterData(), hit_name.substring(0, 1)) && !entity.func_110124_au().equals(caster.func_110124_au()) && !this.visited.contains(entity)) {
                if (!this.field_70170_p.field_72995_K) {
                }
                this.visited.add(entity);
                toAdd.add(entity);
            }
        }
        for (EntityLivingBase entity2 : toAdd) {
            int count2 = count + 1;
            targetNodes.add(getTargetsNode(caster, entity2, count2));
        }
        IonizerNode node = new IonizerNode(origin);
        node.setTargets(targetNodes);
        node.setTimer(count * 5);
        return node;
    }
    private int getCharacterData() {
        return ((Integer) this.field_70180_af.func_187225_a(CHARACTER_DATA)).intValue();
    }
    public IonizerNode getTargets() {
        return this.targets;
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(CASTER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(ORIGIN_ID, 0);
        this.field_70180_af.func_187214_a(CHARACTER_DATA, 0);
    }
    public EntityPlayer getCaster() {
        if (((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).get());
        }
        return null;
    }
    public void setCaster(EntityPlayer player) {
        this.field_70180_af.func_187227_b(CASTER_ID, Optional.fromNullable(player.func_110124_au()));
    }
    public void setCharacterData(int data) {
        this.field_70180_af.func_187227_b(CHARACTER_DATA, Integer.valueOf(data));
    }
}
