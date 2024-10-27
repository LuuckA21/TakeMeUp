package me.luucka.takemeup.menu;

import me.luucka.takemeup.conversation.SoundAfterTeleportPrompt;
import me.luucka.takemeup.conversation.YLimitUpdatePrompt;
import me.luucka.takemeup.menu.button.RemoveButton;
import me.luucka.takemeup.model.DiskWorldConfig;
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
import org.mineacademy.fo.remain.CompMaterial;

public final class EditDiskWorldConfigMenu extends Menu {

	private final DiskWorldConfig diskWorldConfig;

	@Position(9 * 1 + 1)
	private final Button returnLocationButton;

	@Position(9 * 1 + 4)
	private final Button yLimitButton;

	@Position(9 * 1 + 7)
	private final Button statusButton;

	@Position(9 * 2 + 2)
	private final Button soundButton;

	@Position(9 * 4 - 1)
	private final Button removeButton;

	public EditDiskWorldConfigMenu(Player player, DiskWorldConfig diskWorldConfig) {
		this.diskWorldConfig = diskWorldConfig;
		setViewer(player);
		setTitle(MenuSettings.EditMenu.TITLE);
		setSize(9 * 4);

//		setSlotNumbersVisible();

		returnLocationButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				if (click == ClickType.LEFT) {
					if (diskWorldConfig.getWorld().getUID().compareTo(player.getWorld().getUID()) == 0) {
						diskWorldConfig.setReturnLocation(player.getLocation());
						restartMenu(Messages.Commands.RETURN_LOCATION_UPDATED);
					} else {
						animateTitle(Messages.Commands.RETURN_LOCATION_NOT_SAME_WORLD);
					}
				} else if (click == ClickType.RIGHT) {
					Common.runLater(() -> player.teleport(diskWorldConfig.getReturnLocation()));
				}
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(
						MenuSettings.EditMenu.ReturnLocationButton.MATERIAL,
						MenuSettings.EditMenu.ReturnLocationButton.NAME,
						Replacer.replaceArray(MenuSettings.EditMenu.ReturnLocationButton.LORE,
								"x", diskWorldConfig.getReturnLocation().getBlockX(),
								"y", diskWorldConfig.getReturnLocation().getBlockY(),
								"z", diskWorldConfig.getReturnLocation().getBlockZ()
						)
				).make();
			}
		};

		yLimitButton = new ButtonConversation(
				new YLimitUpdatePrompt(this.diskWorldConfig, true),
				ItemCreator.of(
						MenuSettings.EditMenu.YLimitButton.MATERIAL,
						MenuSettings.EditMenu.YLimitButton.NAME,
						Replacer.replaceArray(MenuSettings.EditMenu.YLimitButton.LORE,
								"ylimit", diskWorldConfig.getYLimit()
						)
				));

		statusButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				diskWorldConfig.setStatus(diskWorldConfig.getInvertedStatus());
				restartMenu(diskWorldConfig.isOn() ? MenuSettings.EditMenu.StatusOnButton.NAME : MenuSettings.EditMenu.StatusOffButton.NAME);
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(
						diskWorldConfig.isOn() ? MenuSettings.EditMenu.StatusOnButton.MATERIAL : MenuSettings.EditMenu.StatusOffButton.MATERIAL,
						diskWorldConfig.isOn() ? MenuSettings.EditMenu.StatusOnButton.NAME : MenuSettings.EditMenu.StatusOffButton.NAME,
						diskWorldConfig.isOn() ? MenuSettings.EditMenu.StatusOnButton.LORE : MenuSettings.EditMenu.StatusOffButton.LORE
				).make();
			}
		};

		soundButton = new ButtonConversation(
				new SoundAfterTeleportPrompt(diskWorldConfig, true),
				ItemCreator.of(
						CompMaterial.NOTE_BLOCK,
						"Change Sound, Volume and Pitch",
						"",
						"Click to Change"
				)
		);

		removeButton = new RemoveButton(
				this,
				"config",
				diskWorldConfig.getReturnLocation().getWorld().getName(),
				() -> DiskWorldConfig.removeDiskWorldConfig(diskWorldConfig)
		);
	}

	@Override
	public Menu newInstance() {
		return new EditDiskWorldConfigMenu(getViewer(), diskWorldConfig);
	}
}
