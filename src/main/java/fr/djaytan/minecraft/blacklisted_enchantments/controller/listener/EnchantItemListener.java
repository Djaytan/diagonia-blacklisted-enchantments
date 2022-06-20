/*
 *  Blacklisting enchantments plugin for Minecraft (Bukkit servers)
 *  Copyright (C) 2022 - Loïc DUBOIS-TERMOZ
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package fr.djaytan.minecraft.blacklisted_enchantments.controller.listener;

import fr.djaytan.minecraft.blacklisted_enchantments.controller.api.EnchantmentController;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

@Singleton
public class EnchantItemListener implements Listener {

  private final EnchantmentController enchantmentController;

  @Inject
  public EnchantItemListener(@NotNull EnchantmentController enchantmentController) {
    this.enchantmentController = enchantmentController;
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onItemEnchant(@NotNull EnchantItemEvent event) {
    Map<Enchantment, Integer> enchantsToAdd = event.getEnchantsToAdd();

    enchantmentController.removeBlacklistedEnchantments(enchantsToAdd);
    // The list of enchants to add mustn't be empty, otherwise things will not work properly
    // TODO: try to re-roll the enchantments with ContainerEnchantTable from NMS instead
    enchantmentController.addFallbackEnchantmentIfEmpty(enchantsToAdd);
  }
}
