package com.guia31.painelled;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PainelLedMod implements ModInitializer {
	public static final String MOD_ID = "painel_led";

	public static Block PAINEL_LED;
	public static Item PAINEL_LED_ITEM;

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id("painel_led"));
		PAINEL_LED = Registry.register(BuiltInRegistries.BLOCK, blockKey,
				new PainelLedBlock(BlockBehaviour.Properties.of()
						.setId(blockKey)
						.strength(0.3F)
						.sound(SoundType.GLASS)
						.lightLevel(state -> 15)
						.noOcclusion()));

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id("painel_led"));
		PAINEL_LED_ITEM = Registry.register(BuiltInRegistries.ITEM, itemKey,
				new BlockItem(PAINEL_LED, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
				.register(entries -> entries.accept(PAINEL_LED_ITEM));
	}
}
