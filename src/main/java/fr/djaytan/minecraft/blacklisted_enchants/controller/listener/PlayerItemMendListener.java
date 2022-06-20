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

package fr.djaytan.minecraft.blacklisted_enchants.controller.listener;

import fr.djaytan.minecraft.blacklisted_enchants.controller.api.EnchantmentController;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.jetbrains.annotations.NotNull;

@Singleton
public class PlayerItemMendListener implements Listener {

  private final EnchantmentController enchantmentController;

  @Inject
  public PlayerItemMendListener(@NotNull EnchantmentController enchantmentController) {
    this.enchantmentController = enchantmentController;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerItemMend(@NotNull PlayerItemMendEvent event) {
    event.setCancelled(true);

    Set<Enchantment> removedBlacklistedEnchantments =
        enchantmentController.removeBlacklistedEnchantments(event.getItem());

    if (removedBlacklistedEnchantments.isEmpty()) {
      return;
    }

    enchantmentController.sendRemovedBlacklistedEnchantmentsMessage(
        event.getPlayer(), removedBlacklistedEnchantments);
  }
}
