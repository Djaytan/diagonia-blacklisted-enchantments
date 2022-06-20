/*
 * Blacklisted enchants plugin for Minecraft (Bukkit servers)
 * Copyright (C) 2022 - Loïc DUBOIS-TERMOZ
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package fr.djaytan.minecraft.blacklisted_enchants.plugin;

import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.EnchantItemListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.EntityDeathListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.EntityShootBowListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.LootGenerateListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.PiglinBarterListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.PlayerFishListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.PlayerItemMendListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.PrepareAnvilListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.PrepareItemEnchantListener;
import fr.djaytan.minecraft.blacklisted_enchants.controller.listener.VillagerAcquireTradeListener;
import javax.inject.Inject;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class ListenerRegister {

  private final Plugin plugin;
  private final PluginManager pluginManager;

  private final EnchantItemListener enchantItemListener;
  private final EntityDeathListener entityDeathListener;
  private final EntityShootBowListener entityShootBowListener;
  private final LootGenerateListener lootGenerateListener;
  private final PiglinBarterListener piglinBarterListener;
  private final PlayerFishListener playerFishListener;
  private final PlayerItemMendListener playerItemMendListener;
  private final PrepareAnvilListener prepareAnvilListener;
  private final PrepareItemEnchantListener prepareItemEnchantListener;
  private final VillagerAcquireTradeListener villagerAcquireTradeListener;

  @Inject
  public ListenerRegister(
      @NotNull Plugin plugin,
      @NotNull PluginManager pluginManager,
      @NotNull EnchantItemListener enchantItemListener,
      @NotNull EntityDeathListener entityDeathListener,
      @NotNull EntityShootBowListener entityShootBowListener,
      @NotNull LootGenerateListener lootGenerateListener,
      @NotNull PiglinBarterListener piglinBarterListener,
      @NotNull PlayerFishListener playerFishListener,
      @NotNull PlayerItemMendListener playerItemMendListener,
      @NotNull PrepareAnvilListener prepareAnvilListener,
      @NotNull PrepareItemEnchantListener prepareItemEnchantListener,
      @NotNull VillagerAcquireTradeListener villagerAcquireTradeListener) {
    this.plugin = plugin;
    this.pluginManager = pluginManager;

    this.enchantItemListener = enchantItemListener;
    this.entityDeathListener = entityDeathListener;
    this.entityShootBowListener = entityShootBowListener;
    this.lootGenerateListener = lootGenerateListener;
    this.piglinBarterListener = piglinBarterListener;
    this.playerFishListener = playerFishListener;
    this.playerItemMendListener = playerItemMendListener;
    this.prepareAnvilListener = prepareAnvilListener;
    this.prepareItemEnchantListener = prepareItemEnchantListener;
    this.villagerAcquireTradeListener = villagerAcquireTradeListener;
  }

  public void registerListeners() {
    pluginManager.registerEvents(enchantItemListener, plugin);
    pluginManager.registerEvents(entityDeathListener, plugin);
    pluginManager.registerEvents(entityShootBowListener, plugin);
    pluginManager.registerEvents(lootGenerateListener, plugin);
    pluginManager.registerEvents(piglinBarterListener, plugin);
    pluginManager.registerEvents(playerFishListener, plugin);
    pluginManager.registerEvents(playerItemMendListener, plugin);
    pluginManager.registerEvents(prepareAnvilListener, plugin);
    pluginManager.registerEvents(prepareItemEnchantListener, plugin);
    pluginManager.registerEvents(villagerAcquireTradeListener, plugin);
  }
}
