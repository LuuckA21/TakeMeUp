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

		public static String EDIT_MENU_DESCRIPTION = "Open current world's edit menu";

		public static String RETURN_LOCATION_DESCRIPTION = "Set or Edit return location in the current world";

		public static String RETURN_LOCATION_CREATED = "Return location created!";

		public static String RETURN_LOCATION_UPDATED = "Return location updated!";

		public static String RETURN_LOCATION_NOT_SET = "Return location is not set in the current world! Type '/{label} create' to set";

		public static String RETURN_LOCATION_NOT_SAME_WORLD = "You must be on the same world";

		public static String LIST_DESCRIPTION = "Open world config list menu";

		public static String LIST_FAIL = "Please create a world config first Type '/{label} create' to set";

		private static void init() {
			setPathPrefix("Commands");

			if (isSetDefault("Edit_Menu_Description"))
				EDIT_MENU_DESCRIPTION = getString("Edit_Menu_Description");

			if (isSetDefault("Return_Location_Description"))
				RETURN_LOCATION_DESCRIPTION = getString("Return_Location_Description");

			if (isSetDefault("Return_Location_Created"))
				RETURN_LOCATION_CREATED = getString("Return_Location_Created");

			if (isSetDefault("Return_Location_Updated"))
				RETURN_LOCATION_UPDATED = getString("Return_Location_Updated");

			if (isSetDefault("Return_Location_Not_Set"))
				RETURN_LOCATION_NOT_SET = getString("Return_Location_Not_Set");

			if (isSetDefault("Return_Location_Not_Same_World"))
				RETURN_LOCATION_NOT_SAME_WORLD = getString("Return_Location_Not_Same_World");

			if (isSetDefault("List_Description"))
				LIST_DESCRIPTION = getString("List_Description");

			if (isSetDefault("List_Fail"))
				LIST_FAIL = getString("List_Fail");
		}

	}

	public static final class Conversation {
		public static String Y_LIMIT_QUESTION = "Write in chat the Y limit value! Type 'exit' to quit";

		public static String Y_LIMIT_SUCCESS = "Y limit updated!";

		public static String Y_LIMIT_FAIL = "The Y limit must be a whole number. Got: '{ylimit}'";

		private static void init() {
			setPathPrefix("Conversation");

			if (isSetDefault("Y_Limit_Question"))
				Y_LIMIT_QUESTION = getString("Y_Limit_Question");

			if (isSetDefault("Y_Limit_Success"))
				Y_LIMIT_SUCCESS = getString("Y_Limit_Success");

			if (isSetDefault("Y_Limit_Fail"))
				Y_LIMIT_FAIL = getString("Y_Limit_Fail");

		}
	}
}
