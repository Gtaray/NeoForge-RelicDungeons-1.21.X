package net.saagael.relic_dungeons.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.commands.GenerateDungeonCommand;
import net.saagael.relic_dungeons.common.commands.ListThemesCommand;

@EventBusSubscriber(modid = RelicDungeons.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class RelicDungeonEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new GenerateDungeonCommand(event.getDispatcher(), event.getBuildContext());
        new ListThemesCommand(event.getDispatcher(), event.getBuildContext());

        ConfigCommand.register(event.getDispatcher());
    }
}
