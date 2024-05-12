package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.conversation.OffsetUpdatePrompt;
import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.Optional;

public class OffsetCommand extends SimpleSubCommand {

	public OffsetCommand(SimpleCommandGroup parent) {
		super(parent, "offset");
		setDescription(Messages.Commands.OFFSET_DESCRIPTION);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<WorldConfig> worldConfig = WorldConfig.find(player.getWorld());
		if (worldConfig.isPresent()) {
			new OffsetUpdatePrompt(worldConfig.get(), false).show(player);
		} else {
			tellError(Messages.Commands.SPAWN_NOT_SET);
		}
	}
}
