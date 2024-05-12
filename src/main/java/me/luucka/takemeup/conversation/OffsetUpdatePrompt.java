package me.luucka.takemeup.conversation;

import me.luucka.takemeup.model.WorldConfig;
import me.luucka.takemeup.setting.Messages;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.conversation.SimplePrompt;
import org.mineacademy.fo.model.Replacer;

public class OffsetUpdatePrompt extends SimplePrompt {

	private final WorldConfig config;

	public OffsetUpdatePrompt(WorldConfig config, boolean openMenu) {
		super(openMenu);
		this.config = config;
	}

	@Override
	protected String getPrompt(ConversationContext context) {
		return Messages.Conversation.OFFSET_QUESTION;
	}

	@Override
	protected boolean isInputValid(ConversationContext context, String input) {
		return Valid.isInteger(input);
	}

	@Override
	protected String getFailedValidationText(ConversationContext context, String invalidInput) {
		Messenger.error(getPlayer(context), Replacer.replaceArray(Messages.Conversation.OFFSET_FAIL, "offset", invalidInput));
		return null;
	}

	@Nullable
	@Override
	protected Prompt acceptValidatedInput(@NotNull ConversationContext conversationContext, @NotNull String input) {
		int offset = Integer.parseInt(input);
		config.setOffset(offset);
		Messenger.success(getPlayer(conversationContext), Messages.Conversation.OFFSET_SUCCESS);

		return END_OF_CONVERSATION;
	}
}
