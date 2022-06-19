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

package fr.djaytan.diagonia.view.message;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

@Singleton
public class EnchantsMessage {

  private final MiniMessage miniMessage;
  private final ResourceBundle resourceBundle;

  @Inject
  public EnchantsMessage(@NotNull MiniMessage miniMessage, @NotNull ResourceBundle resourceBundle) {
    this.miniMessage = miniMessage;
    this.resourceBundle = resourceBundle;
  }

  public @NotNull Component removedBlacklistedEnchants(
      @NotNull Set<Enchantment> removedBlacklistedEnchants) {
    if (removedBlacklistedEnchants.size() == 1) {
      Enchantment removedBlacklistedEnchantment = removedBlacklistedEnchants.iterator().next();

      return miniMessage.deserialize(
          resourceBundle.getString("diagonia.enchants.message.removed_enchant"),
          TagResolver.resolver(
              Placeholder.component(
                  "diag_enchant", Component.translatable(removedBlacklistedEnchantment))));
    }

    return miniMessage.deserialize(
        resourceBundle.getString("diagonia.enchants.message.removed_enchants"),
        TagResolver.resolver(
            Placeholder.component(
                "diag_enchants",
                Component.join(
                    JoinConfiguration.separators(
                        miniMessage.deserialize(
                            resourceBundle.getString("diagonia.common.separator.list")),
                        miniMessage.deserialize(
                            resourceBundle.getString("diagonia.common.separator.list.last"))),
                    removedBlacklistedEnchants.stream()
                        .map(Component::translatable)
                        .collect(Collectors.toSet())))));
  }
}
