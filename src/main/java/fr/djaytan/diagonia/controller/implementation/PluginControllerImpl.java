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

package fr.djaytan.diagonia.controller.implementation;

import fr.djaytan.diagonia.controller.api.MessageController;
import fr.djaytan.diagonia.controller.api.PluginController;
import fr.djaytan.diagonia.model.config.data.PluginConfig;
import fr.djaytan.diagonia.plugin.ListenerRegister;
import fr.djaytan.diagonia.view.message.CommonMessage;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Singleton
public class PluginControllerImpl implements PluginController {

  private final CommonMessage commonMessage;
  private final ListenerRegister listenerRegister;
  private final Logger logger;
  private final MessageController messageController;
  private final Plugin plugin;
  private final PluginConfig pluginConfig;

  @Inject
  public PluginControllerImpl(
      @NotNull CommonMessage commonMessage,
      @NotNull ListenerRegister listenerRegister,
      @NotNull Logger logger,
      @NotNull MessageController messageController,
      @NotNull Plugin plugin,
      @NotNull PluginConfig pluginConfig) {
    this.commonMessage = commonMessage;
    this.listenerRegister = listenerRegister;
    this.logger = logger;
    this.messageController = messageController;
    this.plugin = plugin;
    this.pluginConfig = pluginConfig;
  }

  @Override
  public void disablePlugin() {
    logger.info("Plugin successfully disabled");
  }

  @Override
  public void enablePlugin() {
    try {
      // TODO: startup banner in separated class in view
      messageController.sendConsoleMessage(commonMessage.startupBanner());
      messageController.sendConsoleMessage(
          commonMessage.startupBannerVersionLine(plugin.getDescription()));

      messageController.sendConsoleMessage(
          commonMessage.startupBannerProgressionLine("General config file loading"));
      messageController.sendConsoleMessage(
          commonMessage.startupBannerStateLine(
              "Debug Mode", Boolean.toString(pluginConfig.isDebug())));

      messageController.sendConsoleMessage(
          commonMessage.startupBannerProgressionLine("Guice full injection"));

      listenerRegister.registerListeners();

      messageController.sendConsoleMessage(
          commonMessage.startupBannerProgressionLine("Listeners registration"));

      messageController.sendConsoleMessage(commonMessage.startupBannerEnablingSuccessLine());
    } catch (Exception e) {
      // TODO: more centralized error management (listeners, commands, ...)
      messageController.sendConsoleMessage(commonMessage.startupBannerEnablingFailureLine());
      logger.error("Something went wrong and prevent plugin activation.", e);
      logger.error("Disabling plugin...");
      plugin.getServer().getPluginManager().disablePlugin(plugin);
    }
  }
}
