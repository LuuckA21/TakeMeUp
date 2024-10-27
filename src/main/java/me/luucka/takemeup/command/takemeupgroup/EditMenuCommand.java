package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.menu.EditDiskWorldConfigMenu;
import me.luucka.takemeup.model.DiskWorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.Optional;

public class EditMenuCommand extends SimpleSubCommand {

	public EditMenuCommand(SimpleCommandGroup parent) {
		super(parent, "edit");
		setDescription(Messages.Commands.EDIT_MENU_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<DiskWorldConfig> worldConfig = DiskWorldConfig.findDiskWorldConfig(player.getWorld());
		if (worldConfig.isPresent()) {
			new EditDiskWorldConfigMenu(player, worldConfig.get()).displayTo(player);
		} else {
			tellError(Messages.Commands.RETURN_LOCATION_NOT_SET);
		}
	}
}
