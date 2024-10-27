package me.luucka.takemeup.conversation;

import me.luucka.takemeup.model.DiskWorldConfig;
import org.bukkit.conversations.ConversationContext;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.conversation.SimpleStringPrompt;
import org.mineacademy.fo.model.Replacer;
import org.mineacademy.fo.remain.CompSound;

public class SoundAfterTeleportPrompt extends SimpleStringPrompt {

	private final DiskWorldConfig diskWorldConfig;

	public SoundAfterTeleportPrompt(DiskWorldConfig diskWorldConfig, boolean openMenu) {
		super(openMenu);
		this.diskWorldConfig = diskWorldConfig;

		setQuestion("Set new sound, format: [s:sound] [v:volume] [p:pitch]");
		setSuccessAction(string -> {
			String[] args = string.split(" ");

			for (String currentArg : args) {
				String property = currentArg.substring(0, 1).toLowerCase();
				String value = currentArg.substring(2);

				switch (property) {
					case "s":
						diskWorldConfig.getSoundAfterTeleport().setSound(CompSound.fromName(value));
						break;
					case "v":
						diskWorldConfig.getSoundAfterTeleport().setVolume(Float.parseFloat(value));
						break;
					case "p":
						diskWorldConfig.getSoundAfterTeleport().setPitch(Float.parseFloat(value));
						break;
				}
			}

			diskWorldConfig.save();
		});
	}

	@Override
	protected boolean isInputValid(ConversationContext context, String input) {
		String[] args = input.split(" ");

		for (String currentArg : args) {
			String property = currentArg.substring(0, 1).toLowerCase();
			String value = currentArg.substring(2);

			try {
				switch (property) {
					case "s":
						if (CompSound.fromName(value) == null) {
							return false;
						}
						break;
					case "v":
					case "p":
						Float.parseFloat(value);
						break;
				}
			} catch (NumberFormatException e) {
				return false;
			}

		}

		return true;
	}

	@Override
	protected String getFailedValidationText(ConversationContext context, String invalidInput) {
		Messenger.error(getPlayer(context), Replacer.replaceArray("Wrong input! Sound must be a valid sound and Volume/Pitch must be a decimal number. Got: {input}", "input", invalidInput));
		return null;
	}
}
