package com.cisco.craft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CiscoCraftMod.MODID)
public final class CiscoCraftMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cisco_craft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "cisco_craft" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "cisco_craft" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "cisco_craft" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "cisco_craft:cisco_block", combining the namespace and path
    public static final RegistryObject<Block> CISCO_BLOCK = BLOCKS.register("cisco_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .setId(BLOCKS.key("cisco_block"))
            .mapColor(MapColor.STONE)
        )
    );
    // Creates a new BlockItem with the id "cisco_craft:cisco_block", combining the namespace and path
    public static final RegistryObject<Item> CISCO_BLOCK_ITEM = ITEMS.register("cisco_block",
        () -> new BlockItem(CISCO_BLOCK.get(), new Item.Properties().setId(ITEMS.key("cisco_block")))
    );

    // Creates a new networking item with the id "cisco_craft:network_cable", nutrition 1 and saturation 2
    public static final RegistryObject<Item> NETWORK_CABLE = ITEMS.register("network_cable",
        () -> new Item(new Item.Properties()
            .setId(ITEMS.key("network_cable"))
            .food(new FoodProperties.Builder()
                .alwaysEdible()
                .nutrition(1)
                .saturationModifier(2f)
                .build()
            )
        )
    );

    // Creates a creative tab with the id "cisco_craft:cisco_tab" for the networking items, that is placed after the redstone tab
    public static final RegistryObject<CreativeModeTab> CISCO_TAB = CREATIVE_MODE_TABS.register("cisco_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.REDSTONE_BLOCKS)
            .icon(() -> NETWORK_CABLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(NETWORK_CABLE.get()); // Add the network cable to the tab
                output.accept(CISCO_BLOCK_ITEM.get()); // Add the cisco block to the tab
            }).build());

    public CiscoCraftMod(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        // Register the commonSetup method for modloading
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modBusGroup);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modBusGroup);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modBusGroup);

        // Register the item to a creative tab
        BuildCreativeModeTabContentsEvent.getBus(modBusGroup).addListener(CiscoCraftMod::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("CISCO CRAFT MOD INITIALIZED");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the cisco block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(CISCO_BLOCK_ITEM);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("CISCO CRAFT CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
