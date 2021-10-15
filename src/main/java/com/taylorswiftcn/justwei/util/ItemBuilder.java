package com.taylorswiftcn.justwei.util;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;
    private NBTContainer compound;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
        compound = new NBTContainer();
    }

    public ItemBuilder(ConfigurationSection section) {
        String id = section.getString("id");
        short data = Short.parseShort(section.getString("data"));
        String name = section.getString("name");
        List<String> lore = section.getStringList("lore");

        item = new ItemStack(Material.getMaterial(id.toUpperCase()));
        meta = item.getItemMeta();
        compound = new NBTContainer();

        this
                .setDurability(data)
                .setDisplayName(name)
                .setLore(lore);
    }

    public ItemBuilder setDurability(short id) {
        item.setDurability(id);

        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);

        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(MegumiUtil.onReplace(name));

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(MegumiUtil.onReplace(lore));

        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        meta.addEnchant(enchant, level, true);

        return this;
    }

    public ItemBuilder addEnchants(HashMap<Enchantment, Integer> enchants) {
        enchants.forEach((k, v) -> meta.addEnchant(k, v, true));

        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        meta.addItemFlags(flag);

        return this;
    }

    public ItemBuilder addFlags(List<ItemFlag> flags) {
        flags.forEach(flag -> meta.addItemFlags(flag));

        return this;
    }

    public ItemBuilder setNBT(String s, Object obj) {
        if (obj instanceof Integer) compound.setInteger(s, (int) obj);
        if (obj instanceof Long) compound.setLong(s, (long) obj);
        if (obj instanceof Float) compound.setFloat(s, (float) obj);
        if (obj instanceof Double) compound.setDouble(s, (double) obj);
        if (obj instanceof Short) compound.setShort(s, (short) obj);
        if (obj instanceof Byte) compound.setByte(s, (byte) obj);
        if (obj instanceof String) compound.setString(s, (String) obj);
        if (obj instanceof Boolean) compound.setBoolean(s, (boolean) obj);

        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.mergeCompound(compound);
        nbtItem.applyNBT(item);

        return item;
    }
}
