package me.luucka.takemeup.menu.button;

import me.luucka.takemeup.setting.MenuSettings;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.ButtonRemove;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompItemFlag;

public class RemoveButton extends ButtonRemove {

	public RemoveButton(Menu parentMenu, String toRemoveType, String toRemoveName, Runnable removeAction) {
		super(parentMenu, toRemoveType, toRemoveName, removeAction);
		setButtonReturnBack();
	}

	@Override
	public ItemStack getItem() {
		return ItemCreator
				.of(MenuSettings.InfoMenu.RemoveButton.MATERIAL)
				.name(MenuSettings.InfoMenu.RemoveButton.NAME)
				.lore(MenuSettings.InfoMenu.RemoveButton.LORE)
				.flags(CompItemFlag.HIDE_ATTRIBUTES)
				.make();
	}

	@Override
	public ItemStack getRemoveConfirmItem() {
		return ItemCreator
				.of(MenuSettings.ConfirmRemoveMenu.ConfirmButton.MATERIAL)
				.name(MenuSettings.ConfirmRemoveMenu.ConfirmButton.NAME)
				.lore(MenuSettings.ConfirmRemoveMenu.ConfirmButton.LORE)
				.flags(CompItemFlag.HIDE_ATTRIBUTES)
				.make();
	}

	private void setButtonReturnBack() {
		ButtonReturnBack.setItemStack(
				ItemCreator.of(MenuSettings.ConfirmRemoveMenu.ReturnButton.MATERIAL)
						.name(MenuSettings.ConfirmRemoveMenu.ReturnButton.NAME)
						.lore(MenuSettings.ConfirmRemoveMenu.ReturnButton.LORE)
						.make()
		);
	}

	@Override
	public String getMenuTitle() {
		return MenuSettings.ConfirmRemoveMenu.TITLE;
	}
}
