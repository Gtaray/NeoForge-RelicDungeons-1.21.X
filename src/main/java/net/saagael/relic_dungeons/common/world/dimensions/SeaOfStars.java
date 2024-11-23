package net.saagael.relic_dungeons.common.world.dimensions;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.world.biomes.SeaOfStarsBiome;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class SeaOfStars {
    public static final ResourceKey<Level> SEA_OF_STARS_DIMENSION_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "sea_of_stars"));
    public static final ResourceKey<DimensionType> SEA_OF_STARS_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "sea_of_stars"));
    public static final ResourceKey<LevelStem> SEA_OF_STARS_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "sea_of_stars"));
    public static final ResourceLocation SEA_OF_STARS_EFFECTS = ResourceLocation
            .fromNamespaceAndPath(RelicDungeons.MOD_ID, "sea_of_stars");

    public static void bootstrapSeaOfStarsDimensionType(BootstrapContext<DimensionType> context) {
        context.register(SEA_OF_STARS_DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(18000),
                false,
                false,
                false,
                false,
                1.0,
                false,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // <- This controls sun/moon/skybox/clouds
                1.0f,
                new DimensionType.MonsterSettings(
                        false,
                        false,
                        ConstantInt.of(0),
                        0)
        ));
    }

    public static void boostrapSeaOfStarsStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);


        FlatLevelGeneratorSettings settings = new FlatLevelGeneratorSettings(
                Optional.empty(),
                biomes.getOrThrow(SeaOfStarsBiome.SEA_OF_STARS_BIOME_KEY),
                List.of());
        FlatLevelSource chunkGen = new FlatLevelSource(settings);

        LevelStem stem = new LevelStem(
                dimTypes.getOrThrow(SEA_OF_STARS_DIMENSION_TYPE),
                chunkGen);

        context.register(SEA_OF_STARS_LEVEL_STEM, stem);
    }
}
