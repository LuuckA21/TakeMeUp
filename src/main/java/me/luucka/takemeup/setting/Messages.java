package me.luucka.takemeup.setting;

import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.SimpleSettings;
import org.mineacademy.fo.settings.YamlStaticConfig;

public final class Messages extends YamlStaticConfig {

	@Override
	protected void onLoad() throws Exception {
		final String localePath = "localization/messages_" + SimpleSettings.LOCALE_PREFIX + ".yml";
		final Object content = FileUtil.getInternalFileContent(localePath);

		Valid.checkNotNull(content, SimplePlugin.getNamed() + " does not support the localization: messages_" + SimpleSettings.LOCALE_PREFIX
				+ ".yml (For custom locale, set the Locale to 'en' and edit your English file instead)");

		loadConfiguration(localePath);
	}

	public static final class Commands {

		public static String INFO_DESCRIPTION = "Open current world's info menu";

		public static String SPAWN_DESCRIPTION = "Set or Update spawn point in the current world";

		public static String SPAWN_CREATED = "Spawn point created!";

		public static String SPAWN_UPDATED = "Spawn point updated!";

		public static String SPAWN_NOT_SET = "Spawn point is not set in the current world! Type '/takemeup spawn' to set";

		public static String SPAWN_NOT_SAME_WORLD = "You must be on the same world";

		public static String REMOVE_DESCRIPTION = "Remove spawn point in the current world";

		public static String REMOVE_SUCCESS = "Spawn point removed!";

		public static String STATUS_DESCRIPTION = "Switch on/off teleport in the current world";

		public static String STATUS_ON = "Status &2ON";

		public static String STATUS_OFF = "Status &4OFF";

		public static String STATUS_FAIL = "Status '{0}' not valid. Type /{label} <on | off>";

		public static String OFFSET_DESCRIPTION = "Set offset in the current world";

		public static String LIST_DESCRIPTION = "Open world config list menu";

		public static String LIST_FAIL = "Please create a world config first Type '/takemeup spawn' to set";

		private static void init() {
			setPathPrefix("Commands");

			if (isSetDefault("Info_Description"))
				INFO_DESCRIPTION = getString("Info_Description");

			if (isSetDefault("Spawn_Description"))
				SPAWN_DESCRIPTION = getString("Spawn_Description");

			if (isSetDefault("Spawn_Created"))
				SPAWN_CREATED = getString("Spawn_Created");

			if (isSetDefault("Spawn_Updated"))
				SPAWN_UPDATED = getString("Spawn_Updated");

			if (isSetDefault("Spawn_Not_Set"))
				SPAWN_NOT_SET = getString("Spawn_Not_Set");

			if (isSetDefault("Spawn_Not_Same_World"))
				SPAWN_NOT_SAME_WORLD = getString("Spawn_Not_Same_World");

			if (isSetDefault("Remove_Description"))
				REMOVE_DESCRIPTION = getString("Remove_Description");

			if (isSetDefault("Remove_Success"))
				REMOVE_SUCCESS = getString("Remove_Success");

			if (isSetDefault("Status_Description"))
				STATUS_DESCRIPTION = getString("Status_Description");

			if (isSetDefault("Status_On"))
				STATUS_ON = getString("Status_On");

			if (isSetDefault("Status_Off"))
				STATUS_OFF = getString("Status_Off");

			if (isSetDefault("Status_Fail"))
				STATUS_FAIL = getString("Status_Fail");

			if (isSetDefault("Offset_Description"))
				OFFSET_DESCRIPTION = getString("Offset_Description");

			if (isSetDefault("List_Description"))
				LIST_DESCRIPTION = getString("List_Description");

			if (isSetDefault("List_Fail"))
				LIST_FAIL = getString("List_Fail");
		}

	}

	public static final class Conversation {
		public static String OFFSET_QUESTION = "Write in chat the offset value! Type 'exit' to quit";

		public static String OFFSET_SUCCESS = "Offset updated!";

		public static String OFFSET_FAIL = "The Offset must be a whole number. Got: '{offset}'";

		private static void init() {
			setPathPrefix("Conversation");

			if (isSetDefault("Offset_Question"))
				OFFSET_QUESTION = getString("Offset_Question");

			if (isSetDefault("Offset_Success"))
				OFFSET_SUCCESS = getString("Offset_Success");

			if (isSetDefault("Offset_Fail"))
				OFFSET_FAIL = getString("Offset_Fail");

		}
	}
}
