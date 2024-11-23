package net.saagael.relic_dungeons.common.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.saagael.relic_dungeons.common.templates.ThemeTemplate;
import net.saagael.relic_dungeons.registers.RelicDungeonsThemes;

import java.util.Optional;

public class ThemeResourceKeyArgument extends ResourceKeyArgument<ThemeTemplate> {
    private static final DynamicCommandExceptionType ERROR_INVALID_THEME = new DynamicCommandExceptionType(
            obj -> Component.translatableEscape("argument.theme.id.invalid", obj)
    );

    public ThemeResourceKeyArgument() {
        super(RelicDungeonsThemes.DUNGEON_REGISTRY);
    }

    public static Holder.Reference<ThemeTemplate> getTheme(
            CommandContext<CommandSourceStack> context,
            String argument)
            throws CommandSyntaxException {
        return resolveKey(context, argument, ERROR_INVALID_THEME);
    }

    private static Holder.Reference<ThemeTemplate> resolveKey(
            CommandContext<CommandSourceStack> context,
            String argument,
            DynamicCommandExceptionType exception
    ) throws CommandSyntaxException {
        ResourceKey<ThemeTemplate> resourcekey = getRegistryKey(
                context,
                argument,
                exception);
        return getThemeRegistry(context).getHolder(resourcekey).orElseThrow(() -> exception.create(resourcekey.location()));
    }

    private static ResourceKey<ThemeTemplate> getRegistryKey(
            CommandContext<CommandSourceStack> context,
            String argument,
            DynamicCommandExceptionType exception
    ) throws CommandSyntaxException {
        ResourceKey<?> resourcekey = context.getArgument(argument, ResourceKey.class);
        Optional<ResourceKey<ThemeTemplate>> optional = resourcekey.cast(RelicDungeonsThemes.DUNGEON_REGISTRY);
        return optional.orElseThrow(() -> exception.create(resourcekey));
    }

    private static Registry<ThemeTemplate> getThemeRegistry(
            CommandContext<CommandSourceStack> context) {
        return context.getSource().getServer().registryAccess().registryOrThrow(RelicDungeonsThemes.DUNGEON_REGISTRY);
    }
}
