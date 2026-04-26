package xol.lostinfinity.mob.entity.misc;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityPlasmaSlicer.class */
public class EntityPlasmaSlicer extends Entity {
    private static final float collapseDist = 2.5f;
    private int collapseTimer;
    private static final float distRange = 25.0f;
    protected static final DataParameter<Optional<UUID>> EMITTER_ID = EntityDataManager.func_187226_a(EntityPlasmaSlicer.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Optional<UUID>> RECEIVER_ID = EntityDataManager.func_187226_a(EntityPlasmaSlicer.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Float> STABLE_DIST = EntityDataManager.func_187226_a(EntityPlasmaSlicer.class, DataSerializers.field_187193_c);

    public EntityPlasmaSlicer(World worldIn) {
        super(worldIn);
        this.collapseTimer = 60;
        func_184224_h(true);
    }

    public float getCollapseDist() {
        return collapseDist;
    }

    public void func_70071_h_() {
        float newDist;
        super.func_70071_h_();
        EntityPlayer emitter = getEmitter();
        EntityPlayer receiver = getReceiver();
        if ((this.field_70173_aa > 2 && emitter == null) || receiver == null) {
            if (!this.field_70170_p.field_72995_K) {
                func_70106_y();
                return;
            }
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (emitter.func_184614_ca().func_77973_b() != ItemInit.plasmaEmitter || receiver.func_184614_ca().func_77973_b() != ItemInit.plasmaReceiver) {
                func_70106_y();
                return;
            }
            if (this.field_70173_aa % 80 == 0) {
                float curDist = getStableDist();
                float fMax = Math.max(this.field_70146_Z.nextFloat() * distRange, 8.0f);
                while (true) {
                    newDist = fMax;
                    if (Math.abs(curDist - newDist) <= 6.0f) {
                        break;
                    } else {
                        fMax = Math.max(this.field_70146_Z.nextFloat() * distRange, 8.0f);
                    }
                }
                setStableDist(newDist);
            }
            float dist = emitter.func_70032_d(receiver);
            func_70634_a(emitter.field_70165_t, emitter.field_70163_u + (((double) emitter.field_70131_O) / 2.0d), emitter.field_70161_v);
            if (this.field_70173_aa % 4 == 0) {
                ArrayList<EntityLivingBase> hitEntities = new ArrayList<>();
                Vec3d start = func_174791_d();
                Vec3d end = receiver.func_174791_d().func_72441_c(0.0d, ((double) receiver.field_70131_O) / 2.0d, 0.0d);
                Vec3d dir = end.func_178788_d(start).func_72432_b();
                double d = 0.0d;
                while (true) {
                    double i = d;
                    if (i >= dist) {
                        break;
                    }
                    Vec3d pos = start.func_72441_c(dir.field_72450_a * i, dir.field_72448_b * i, dir.field_72449_c * i);
                    AxisAlignedBB checkBox = new AxisAlignedBB(pos.field_72450_a - 1.0d, pos.field_72448_b - 1.0d, pos.field_72449_c - 1.0d, pos.field_72450_a + 1.0d, pos.field_72448_b + 1.0d, pos.field_72449_c + 1.0d);
                    for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
                        if (!(entity instanceof EntityImmaterial) && !entity.func_110124_au().equals(emitter.func_110124_au()) && !entity.func_110124_au().equals(receiver.func_110124_au()) && !hitEntities.contains(entity)) {
                            if (IMaxAttack.dealTrueDamage(emitter, entity, entity.func_110138_aP() * 0.1f).wasTargetKilled() && (entity instanceof EntityPlayer)) {
                                EntityItem reward = new EntityItem(this.field_70170_p, entity.field_70165_t, entity.field_70163_u + 0.4d, entity.field_70161_v, new ItemStack(ItemInit.plasmaticCranium));
                                reward.field_70159_w = 0.0d;
                                reward.field_70181_x = 0.0d;
                                reward.field_70179_y = 0.0d;
                                this.field_70170_p.func_72838_d(reward);
                            }
                            hitEntities.add(entity);
                        }
                    }
                    d = i + 0.5d;
                }
            }
            if (Math.abs(dist - getStableDist()) > collapseDist) {
                if (this.field_70173_aa % 20 == 0) {
                    if (dist - getStableDist() < -1.25f) {
                        emitter.func_145747_a(new TextComponentString("You are too close together, move apart to stabilize the plasma!"));
                        receiver.func_145747_a(new TextComponentString("You are too close together, move apart to stabilize the plasma!"));
                    } else if (dist - getStableDist() > 1.25f) {
                        emitter.func_145747_a(new TextComponentString("You are too far apart, move closer to stabilize the plasma!"));
                        receiver.func_145747_a(new TextComponentString("You are too far apart, move closer to stabilize the plasma!"));
                    }
                }
                if (this.collapseTimer > 0) {
                    this.collapseTimer--;
                    return;
                }
                emitter.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "Plasma Destabilized"));
                receiver.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "Plasma Destabilized"));
                func_70106_y();
                return;
            }
            this.collapseTimer = 60;
        }
    }

    public EntityPlayer getEmitter() {
        if (((Optional) this.field_70180_af.func_187225_a(EMITTER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(EMITTER_ID)).get());
        }
        return null;
    }

    public void setEmitter(EntityPlayer player) {
        this.field_70180_af.func_187227_b(EMITTER_ID, Optional.fromNullable(player.func_110124_au()));
    }

    public EntityPlayer getReceiver() {
        if (((Optional) this.field_70180_af.func_187225_a(RECEIVER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(RECEIVER_ID)).get());
        }
        return null;
    }

    public void setReceiver(EntityPlayer player) {
        this.field_70180_af.func_187227_b(RECEIVER_ID, Optional.fromNullable(player.func_110124_au()));
    }

    public void setStableDist(float dist) {
        this.field_70180_af.func_187227_b(STABLE_DIST, Float.valueOf(dist));
    }

    public float getStableDist() {
        return ((Float) this.field_70180_af.func_187225_a(STABLE_DIST)).floatValue();
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(EMITTER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(RECEIVER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(STABLE_DIST, Float.valueOf(0.0f));
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }
}
