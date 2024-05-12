package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.menu.InfoMenu;
import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.Optional;

public class InfoMenuCommand extends SimpleSubCommand {

	public InfoMenuCommand(SimpleCommandGroup parent) {
		super(parent, "info");
		setDescription(Messages.Commands.INFO_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<WorldConfig> worldConfig = WorldConfig.find(player.getWorld());
		if (worldConfig.isPresent()) {
			new InfoMenu(player, worldConfig.get()).displayTo(player);
		} else {
			tellError(Messages.Commands.SPAWN_NOT_SET);
		}
	}
}
