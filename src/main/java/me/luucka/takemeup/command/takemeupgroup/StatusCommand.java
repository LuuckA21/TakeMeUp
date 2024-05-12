package me.luucka.takemeup.command.takemeupgroup;

import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.List;
import java.util.Optional;

public class StatusCommand extends SimpleSubCommand {

	public StatusCommand(SimpleCommandGroup parent) {
		super(parent, "status");
		setDescription(Messages.Commands.STATUS_DESCRIPTION);
		setUsage("<on|off>");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		Optional<WorldConfig> worldConfig = WorldConfig.find(player.getWorld());
		if (worldConfig.isPresent()) {
			if ("on".equalsIgnoreCase(args[0])) {
				worldConfig.get().setStatusOn();
				tellSuccess(Messages.Commands.STATUS_ON);
			} else if ("off".equalsIgnoreCase(args[0])) {
				worldConfig.get().setStatusOff();
				tellSuccess(Messages.Commands.STATUS_OFF);
			} else {
				tellError(Messages.Commands.STATUS_FAIL);
			}
		} else {
			tellError(Messages.Commands.SPAWN_NOT_SET);
		}
	}

	@Override
	protected List<String> tabComplete() {
		checkConsole();

		if (args.length == 1) {
			return completeLastWord("on", "off");
		}

		return NO_COMPLETE;
	}
}
