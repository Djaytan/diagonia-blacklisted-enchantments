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

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fr.djaytan.minecraft.blacklisted_enchants.controller.api.ConfigController;
import fr.djaytan.minecraft.blacklisted_enchants.controller.api.EnchantmentController;
import fr.djaytan.minecraft.blacklisted_enchants.controller.api.MessageController;
import fr.djaytan.minecraft.blacklisted_enchants.controller.api.PluginController;
import fr.djaytan.minecraft.blacklisted_enchants.controller.implementation.ConfigControllerImpl;
import fr.djaytan.minecraft.blacklisted_enchants.controller.implementation.EnchantmentControllerImpl;
import fr.djaytan.minecraft.blacklisted_enchants.controller.implementation.MessageControllerImpl;
import fr.djaytan.minecraft.blacklisted_enchants.controller.implementation.PluginControllerImpl;
import fr.djaytan.minecraft.blacklisted_enchants.model.config.data.PluginConfig;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Named;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/** General Guice module. */
public class GuicePluginModule extends AbstractModule {

  private final Logger logger;
  // TODO: use a provider of a config instead (required for reload feature of plugin)
  private final PluginConfig pluginConfig;

  public GuicePluginModule(
      @NotNull Logger logger, @NotNull PluginConfig pluginConfig) {
    this.logger = logger;
    this.pluginConfig = pluginConfig;
  }

  @Override
  public void configure() {
    bind(ConfigController.class).to(ConfigControllerImpl.class);
    bind(EnchantmentController.class).to(EnchantmentControllerImpl.class);
    bind(MessageController.class).to(MessageControllerImpl.class);
    bind(PluginController.class).to(PluginControllerImpl.class);
  }

  @Provides
  @Named("debugMode")
  public Boolean provideDebugMode() {
    logger.info("Debug mode: {}", pluginConfig.isDebug());
    return pluginConfig.isDebug();
  }

  @Provides
  public PluginConfig providePluginConfig() {
    return pluginConfig;
  }

  @Provides
  @Singleton
  public @NotNull ResourceBundle provideResourceBundle() {
    return ResourceBundle.getBundle("blacklisted_enchants", Locale.FRANCE);
  }

  @Provides
  @Singleton
  public @NotNull MiniMessage provideMiniMessage() {
    return MiniMessage.miniMessage();
  }
}
