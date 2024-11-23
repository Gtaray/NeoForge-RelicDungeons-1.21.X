package net.saagael.relic_dungeons.common.world.biomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.saagael.relic_dungeons.RelicDungeons;

public class SeaOfStarsBiome {
    public static final ResourceKey<Biome> SEA_OF_STARS_BIOME_KEY = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "sea_of_stars"));

    public static void bootstrapSeaOfStarsBiome(BootstrapContext<Biome> context) {
        final var biomeSpecialEffects = new BiomeSpecialEffects.Builder()
                .fogColor(10518688)
                .waterColor(4159204)
                .waterFogColor(329011)
                .skyColor(0)
                .build();

        final var biomeMobSpawnSettings = new MobSpawnSettings.Builder()
                .creatureGenerationProbability(0)
                .build();

        final var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        final var configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        final var biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers)
                .build();

        final var nothingBiome = new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.4f)
                .specialEffects(biomeSpecialEffects)
                .mobSpawnSettings(biomeMobSpawnSettings)
                .generationSettings(biomeGenerationSettings)
                .build();

        context.register(SEA_OF_STARS_BIOME_KEY, nothingBiome);
    }
}
