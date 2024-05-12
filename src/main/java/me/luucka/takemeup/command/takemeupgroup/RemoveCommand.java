package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.Optional;

public class RemoveCommand extends SimpleSubCommand {

	public RemoveCommand(SimpleCommandGroup parent) {
		super(parent, "remove");
		setDescription(Messages.Commands.REMOVE_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<WorldConfig> worldConfig = WorldConfig.find(player.getWorld());
		if (worldConfig.isPresent()) {
			WorldConfig.delete(worldConfig.get());
			tellSuccess(Messages.Commands.REMOVE_SUCCESS);
		} else {
			tellError(Messages.Commands.SPAWN_NOT_SET);
		}
	}
}
