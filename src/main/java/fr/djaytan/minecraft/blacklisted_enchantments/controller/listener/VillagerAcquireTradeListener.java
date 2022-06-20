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
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

@Singleton
public class VillagerAcquireTradeListener implements Listener {

  private final EnchantmentController enchantmentController;

  @Inject
  public VillagerAcquireTradeListener(@NotNull EnchantmentController enchantmentController) {
    this.enchantmentController = enchantmentController;
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onVillagerAcquireTrade(@NotNull VillagerAcquireTradeEvent event) {
    MerchantRecipe initRecipe = event.getRecipe();
    enchantmentController.adjustEnchantments(initRecipe.getResult());
    MerchantRecipe adjustedRecipe =
        new MerchantRecipe(
            initRecipe.getResult(),
            initRecipe.getUses(),
            initRecipe.getMaxUses(),
            initRecipe.hasExperienceReward(),
            initRecipe.getVillagerExperience(),
            initRecipe.getPriceMultiplier(),
            initRecipe.getDemand(),
            initRecipe.getSpecialPrice(),
            initRecipe.shouldIgnoreDiscounts());
    event.getRecipe().getIngredients().forEach(adjustedRecipe::addIngredient);
    event.setRecipe(adjustedRecipe);
  }
}
