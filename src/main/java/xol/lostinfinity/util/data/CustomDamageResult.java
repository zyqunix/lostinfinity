package xol.lostinfinity.util.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/data/CustomDamageResult.class */
public class CustomDamageResult {
    private EntityLivingBase target;
    private Entity attacker;
    private float preTargetHealth;
    private boolean validHit = true;
    private boolean sucessfulHit = true;
    private boolean targetLifeTaken = false;
    private boolean targetKilled = false;
    private float intendedDamage = 0.0f;
    private float postTargetHealth = 0.0f;
    private float damageDealt = 0.0f;
    private List<Float> damageReductions = new ArrayList();
    private List<Float> damageAmplifications = new ArrayList();
    private List<String> damageClassifications = new ArrayList();

    public CustomDamageResult(Entity attacker, EntityLivingBase target) {
        this.preTargetHealth = 0.0f;
        this.target = target;
        this.preTargetHealth = target.func_110143_aJ();
        this.attacker = attacker;
    }

    public void addClassifications(List<String> damageTypes) {
        if (damageTypes != null) {
            this.damageClassifications.addAll(damageTypes);
        }
    }

    public void setIntendedDamage(float intended) {
        this.intendedDamage = intended;
    }

    public void setHitMissed() {
        this.sucessfulHit = false;
    }

    public void setHitInvalid() {
        this.validHit = false;
    }

    public void addReduction(float reduction) {
        this.damageReductions.add(Float.valueOf(reduction));
    }

    public void addAmplification(float amplification) {
        this.damageAmplifications.add(Float.valueOf(amplification));
    }

    public void finishHitData(float final_damage, float postHealth) {
        this.damageDealt = final_damage;
        this.postTargetHealth = postHealth;
        if (postHealth <= 0.0f) {
            takeLife();
        }
    }

    public void takeLife() {
        this.targetLifeTaken = true;
        if (this.target instanceof EntityMultipleLives) {
            EntityMultipleLives multiTarget = this.target;
            if (multiTarget.onFinalLife()) {
                this.targetKilled = true;
                return;
            }
            return;
        }
        if (this.target instanceof EntityMultiLivesTameable) {
            EntityMultiLivesTameable multiTarget2 = this.target;
            if (multiTarget2.onFinalLife()) {
                this.targetKilled = true;
                return;
            }
            return;
        }
        this.targetKilled = true;
    }

    public EntityLivingBase getDamageTarget() {
        return this.target;
    }

    public Entity getAttacker() {
        return this.attacker;
    }

    public boolean didSuccessfulHit() {
        return this.sucessfulHit && this.validHit;
    }

    public boolean wasHitValid() {
        return this.validHit;
    }

    public boolean hitBlockedOrDodged() {
        return this.validHit && !didSuccessfulHit();
    }

    public boolean didTargetLoseLife() {
        return this.targetLifeTaken;
    }

    public boolean wasTargetKilled() {
        return this.targetKilled;
    }

    public float getIntendedDamage() {
        return this.intendedDamage;
    }

    public float getInitialTargetHealth() {
        return this.preTargetHealth;
    }

    public float getTargetEndHealth() {
        return this.postTargetHealth;
    }

    public float getDamageDealt() {
        if (this.damageDealt < 0.0f) {
            return 0.0f;
        }
        return this.damageDealt;
    }

    public boolean targetHealthChanged() {
        return this.preTargetHealth != this.postTargetHealth;
    }

    public int numberOfReductions() {
        return this.damageReductions.size();
    }

    public int numberOfAmplifications() {
        return this.damageAmplifications.size();
    }

    public float extraDamageDealt() {
        if (numberOfAmplifications() == 0) {
            return 0.0f;
        }
        float extra = 0.0f;
        Iterator<Float> it = this.damageAmplifications.iterator();
        while (it.hasNext()) {
            float f = it.next().floatValue();
            extra += f;
        }
        return extra;
    }

    public float damageReduced() {
        if (numberOfReductions() == 0) {
            return 0.0f;
        }
        float reduced = 0.0f;
        Iterator<Float> it = this.damageReductions.iterator();
        while (it.hasNext()) {
            float f = it.next().floatValue();
            reduced += f;
        }
        return reduced;
    }
}
