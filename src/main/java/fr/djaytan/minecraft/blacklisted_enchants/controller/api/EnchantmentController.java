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

package fr.djaytan.minecraft.blacklisted_enchants.controller.api;

import java.util.Map;
import java.util.Set;
import net.kyori.adventure.audience.Audience;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EnchantmentController {

  void adjustEnchantments(@Nullable ItemStack itemStack);

  @NotNull
  Set<Enchantment> removeBlacklistedEnchantments(@Nullable ItemStack itemStack);

  void removeBlacklistedEnchantments(@NotNull Map<Enchantment, Integer> enchantments);

  /**
   * Add fallback enchantment to the item with {@link
   * Enchantment#DURABILITY} at max level. The items with meta corresponding
   * to an {@link org.bukkit.inventory.meta.EnchantmentStorageMeta} are taken into account by this
   * method.
   *
   * <p>Note: A fallback enchantment must be added only to items which are no longer enchanted
   * because of the remove of one or several blacklisted ones.
   *
   * <p>If the specified item already have enchantment(s), this method will do nothing.
   *
   * @param itemStack A mutable item.
   */
  void addFallbackEnchantmentIfEmpty(@Nullable ItemStack itemStack);

  void addFallbackEnchantmentIfEmpty(@NotNull Map<Enchantment, Integer> enchantments);

  void applyFallbackEnchantmentOffer(@NotNull EnchantmentOffer enchantmentOffer);

  boolean isBlacklistedEnchantment(@NotNull Enchantment enchantment);

  void sendRemovedBlacklistedEnchantmentsMessage(
      @NotNull Audience audience, @NotNull Set<Enchantment> removedBlacklistedEnchantments);
}
