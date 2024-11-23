package net.saagael.relic_dungeons.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.registers.RelicDungeonsThemes;

public class ListThemesCommand {
    public ListThemesCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
            Commands.literal("list")
                //.requires(stack -> stack.hasPermission(2))
                .then(Commands.literal("themes")
                .executes((command) -> {
                    RegistryAccess registries = Minecraft.getInstance().getConnection().registryAccess();
                    var registry = registries.registry(RelicDungeonsThemes.DUNGEON_REGISTRY);
                    registry.get().forEach((themeTemplate -> RelicDungeons.LOGGER.debug("Theme: " + themeTemplate)));
                    return 1;
                })));
    }
}
