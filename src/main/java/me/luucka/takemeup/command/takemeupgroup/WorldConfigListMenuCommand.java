package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.menu.WorldConfigListMenu;
import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class WorldConfigListMenuCommand extends SimpleSubCommand {

	public WorldConfigListMenuCommand(SimpleCommandGroup parent) {
		super(parent, "list");
		setDescription(Messages.Commands.LIST_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		if (!WorldConfig.getAllItems().isEmpty()) {
			new WorldConfigListMenu(player).displayTo(player);
		} else {
			tellError(Messages.Commands.LIST_FAIL);
		}
	}
}
