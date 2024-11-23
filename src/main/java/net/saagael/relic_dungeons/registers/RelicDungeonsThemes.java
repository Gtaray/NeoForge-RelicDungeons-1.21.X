package net.saagael.relic_dungeons.registers;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.templates.ThemeTemplate;

import java.util.Optional;

public class RelicDungeonsThemes {
    public static final ResourceKey<Registry<ThemeTemplate>> DUNGEON_REGISTRY =
            ResourceKey.createRegistryKey(
                    ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "dungeons"));

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                // The registry key.
                DUNGEON_REGISTRY,
                // The codec of the registry contents.
                ThemeTemplate.CODEC,
                // The network codec of the registry contents. Often identical to the normal codec.
                // May be a reduced variant of the normal codec that omits data that is not needed on the client.
                // May be null. If null, registry entries will not be synced to the client at all.
                // May be omitted, which is functionally identical to passing null (a method overload
                // with two parameters is called that passes null to the normal three parameter method).
                ThemeTemplate.CODEC
        );
    }
}
