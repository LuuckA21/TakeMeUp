package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.Optional;

public class SpawnCommand extends SimpleSubCommand {

	public SpawnCommand(SimpleCommandGroup parent) {
		super(parent, "spawn");
		setDescription(Messages.Commands.SPAWN_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<WorldConfig> worldConfig = WorldConfig.find(player.getWorld());
		if (worldConfig.isPresent()) {
			worldConfig.get().setSpawnLocation(player.getLocation());
			tellSuccess(Messages.Commands.SPAWN_UPDATED);
		} else {
			WorldConfig.createNew(player.getLocation());
			tellSuccess(Messages.Commands.SPAWN_CREATED);
		}
	}
}
