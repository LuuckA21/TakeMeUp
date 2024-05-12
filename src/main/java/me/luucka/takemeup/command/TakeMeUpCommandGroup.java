package me.luucka.takemeup.command;

import me.luucka.takemeup.command.takemeupgroup.*;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.DebugCommand;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.model.SimpleComponent;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.SimpleLocalization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@AutoRegister
public final class TakeMeUpCommandGroup extends SimpleCommandGroup {

	public TakeMeUpCommandGroup() {
		super("takemeup|tmu");
	}

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new SpawnCommand(this));
		registerSubcommand(new RemoveCommand(this));
		registerSubcommand(new OffsetCommand(this));
		registerSubcommand(new StatusCommand(this));
		registerSubcommand(new InfoMenuCommand(this));
		registerSubcommand(new WorldConfigListMenuCommand(this));
		registerSubcommand(new ReloadCommand());
		registerSubcommand(new DebugCommand());
	}

	@Override
	protected String[] getHelpHeader() {
		return new String[]{
				"&8",
				"&8" + Common.chatLineSmooth(),
				getHeaderPrefix() + "  " + SimplePlugin.getNamed() + getHeaderPrefix() + "&8\u2122" + " &7" + SimplePlugin.getVersion(),
				" ",
				"&2  [] &f= " + SimpleLocalization.Commands.LABEL_OPTIONAL_ARGS,
				getTheme() + "  <> &f= " + SimpleLocalization.Commands.LABEL_REQUIRED_ARGS,
				" "
		};
	}

	@Override
	protected List<SimpleComponent> getNoParamsHeader() {
		final int foundedYear = SimplePlugin.getInstance().getFoundedYear();
		final int yearNow = Calendar.getInstance().get(Calendar.YEAR);

		final List<String> messages = new ArrayList<>();

		messages.add("&8" + Common.chatLineSmooth());
		messages.add(getHeaderPrefix() + "  " + SimplePlugin.getNamed() + getHeaderPrefix() + "&8\u2122" + " &7" + SimplePlugin.getVersion());
		messages.add(" ");

		{
			final String authors = String.join(", ", SimplePlugin.getInstance().getDescription().getAuthors());

			if (!authors.isEmpty())
				messages.add("   &7" + SimpleLocalization.Commands.LABEL_AUTHORS + " &f" + authors + (foundedYear != -1 ? " &7\u00A9 " + foundedYear + (yearNow != foundedYear ? " - " + yearNow : "") : ""));
		}

		{
			final String credits = getCredits();

			if (!credits.isEmpty())
				messages.add("   " + credits);
		}

		messages.add("&8" + Common.chatLineSmooth());

		return Common.convert(messages, SimpleComponent::of);
	}

	@Override
	protected String getCredits() {
		return "&7Visit &fgithub.com/LuuckA21 &7for more information.";
	}
}
