package xol.lostinfinity.item.weapon.droid;
public class ItemDroidRelocatorMK2 extends ItemDroidRelocatorStorage {
    public ItemDroidRelocatorMK2(String regName) {
        super(regName);
    }
    @Override // xol.lostinfinity.item.weapon.droid.ItemDroidRelocatorStorage
    protected int getSummonGrade() {
        return 1;
    }
}
