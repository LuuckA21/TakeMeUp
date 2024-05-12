package me.luucka.takemeup.menu;

import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.MenuSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.Replacer;

public class WorldConfigListMenu extends MenuPagged<WorldConfig> {

	public WorldConfigListMenu(Player player) {
		super(WorldConfig.getAllItems());
		setViewer(player);
		setTitle(MenuSettings.WorldConfigListMenu.TITLE);
		setSize(9 * 5);
	}

	@Override
	protected ItemStack convertToItemStack(WorldConfig worldConfig) {
		return ItemCreator.of(
						MenuSettings.WorldConfigListMenu.WorldButton.MATERIAL,
						Replacer.replaceArray(MenuSettings.WorldConfigListMenu.WorldButton.NAME, "{world}", worldConfig.getName()),
						Replacer.replaceArray(MenuSettings.WorldConfigListMenu.WorldButton.LORE, "{world}", worldConfig.getName())
				)
				.glow(worldConfig.getWorld().getUID().compareTo(getViewer().getWorld().getUID()) == 0)
				.make();
	}

	@Override
	protected void onPageClick(Player player, WorldConfig worldConfig, ClickType clickType) {
		new InfoMenu(player, worldConfig).displayTo(player);
	}
}
