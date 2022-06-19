/*
 * Copyright (c) 2022 - Loïc DUBOIS-TERMOZ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.djaytan.diagonia.plugin;

import co.aikar.commands.PaperCommandManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fr.djaytan.diagonia.controller.api.ConfigController;
import fr.djaytan.diagonia.controller.api.EnchantmentController;
import fr.djaytan.diagonia.controller.api.MessageController;
import fr.djaytan.diagonia.controller.api.PluginController;
import fr.djaytan.diagonia.controller.implementation.ConfigControllerImpl;
import fr.djaytan.diagonia.controller.implementation.EnchantmentControllerImpl;
import fr.djaytan.diagonia.controller.implementation.MessageControllerImpl;
import fr.djaytan.diagonia.controller.implementation.PluginControllerImpl;
import fr.djaytan.diagonia.model.config.data.PluginConfig;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Named;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/** General Guice module. */
public class GuiceBlacklistedEnchantmentsModule extends AbstractModule {

  private final JavaPlugin javaPlugin;
  private final Logger logger;
  // TODO: use a provider of a config instead (required for reload feature of plugin)
  private final PluginConfig pluginConfig;

  public GuiceBlacklistedEnchantmentsModule(
      @NotNull Logger logger, @NotNull JavaPlugin javaPlugin, @NotNull PluginConfig pluginConfig) {
    this.javaPlugin = javaPlugin;
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
    return ResourceBundle.getBundle("diagonia", Locale.FRANCE);
  }

  @Provides
  @Singleton
  public @NotNull MiniMessage provideMiniMessage() {
    return MiniMessage.miniMessage();
  }

  @Provides
  @Singleton
  public @NotNull PaperCommandManager provideAcfPaperCommandManager() {
    return new PaperCommandManager(javaPlugin);
  }
}
