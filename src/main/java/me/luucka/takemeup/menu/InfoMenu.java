package me.luucka.takemeup.menu;

import me.luucka.takemeup.conversation.OffsetUpdatePrompt;
import me.luucka.takemeup.menu.button.RemoveButton;
import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.MenuSettings;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonConversation;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.Replacer;

public final class InfoMenu extends Menu {

	private final WorldConfig worldConfig;

	@Position(9 * 1 + 1)
	private final Button spawnPointButton;

	@Position(9 * 1 + 4)
	private final Button offsetButton;

	@Position(9 * 1 + 7)
	private final Button statusButton;

	@Position(9 * 4 - 1)
	private final Button removeButton;

	public InfoMenu(Player player, WorldConfig worldConfig) {
		this.worldConfig = worldConfig;
		setViewer(player);
		setTitle(MenuSettings.InfoMenu.TITLE);
		setSize(9 * 4);

//		setSlotNumbersVisible();

		spawnPointButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				if (click == ClickType.LEFT) {
					if (worldConfig.getWorld().getUID().compareTo(player.getWorld().getUID()) == 0) {
						worldConfig.setSpawnLocation(player.getLocation());
						restartMenu(Messages.Commands.SPAWN_UPDATED);
					} else {
						animateTitle(Messages.Commands.SPAWN_NOT_SAME_WORLD);
					}
				} else if (click == ClickType.RIGHT) {
					Common.runLater(() -> player.teleport(worldConfig.getSpawnLocation()));
				}
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(
						MenuSettings.InfoMenu.SpawnLocationButton.MATERIAL,
						MenuSettings.InfoMenu.SpawnLocationButton.NAME,
						Replacer.replaceArray(MenuSettings.InfoMenu.SpawnLocationButton.LORE,
								"x", worldConfig.getSpawnLocation().getBlockX(),
								"y", worldConfig.getSpawnLocation().getBlockY(),
								"z", worldConfig.getSpawnLocation().getBlockZ()
						)
				).make();
			}
		};

		offsetButton = new ButtonConversation(
				new OffsetUpdatePrompt(this.worldConfig, true),
				ItemCreator.of(
						MenuSettings.InfoMenu.OffsetButton.MATERIAL,
						MenuSettings.InfoMenu.OffsetButton.NAME,
						Replacer.replaceArray(MenuSettings.InfoMenu.OffsetButton.LORE,
								"offset", worldConfig.getOffset()
						)
				));

		statusButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				worldConfig.setStatus(worldConfig.getInvertedStatus());
				restartMenu(worldConfig.isOn() ? MenuSettings.InfoMenu.StatusOnButton.NAME : MenuSettings.InfoMenu.StatusOffButton.NAME);
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(
						worldConfig.isOn() ? MenuSettings.InfoMenu.StatusOnButton.MATERIAL : MenuSettings.InfoMenu.StatusOffButton.MATERIAL,
						worldConfig.isOn() ? MenuSettings.InfoMenu.StatusOnButton.NAME : MenuSettings.InfoMenu.StatusOffButton.NAME,
						worldConfig.isOn() ? MenuSettings.InfoMenu.StatusOnButton.LORE : MenuSettings.InfoMenu.StatusOffButton.LORE
				).make();
			}
		};
		removeButton = new RemoveButton(
				this,
				"config",
				worldConfig.getSpawnLocation().getWorld().getName(),
				() -> WorldConfig.delete(worldConfig)
		);
	}

	@Override
	public Menu newInstance() {
		return new InfoMenu(getViewer(), worldConfig);
	}
}
