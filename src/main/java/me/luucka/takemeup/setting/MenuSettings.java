package me.luucka.takemeup.setting;

import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

public class MenuSettings extends YamlStaticConfig {

	@Override
	protected void onLoad() throws Exception {
		loadConfiguration("menu.yml");
	}

	public static final class EditMenu {
		public static String TITLE = "Edit menu";

		private static void init() {
			setPathPrefix("Edit_Menu");

			if (isSetDefault("Title"))
				TITLE = getString("Title");
		}

		public static final class ReturnLocationButton {
			public static CompMaterial MATERIAL = CompMaterial.MAP;

			public static String NAME = "Return location";

			public static List<String> LORE = Arrays.asList(
					"",
					"Current: {x} | {y} | {z}",
					"",
					"&7(Left click)",
					"&6Update coordinates",
					"",
					"&7(Right click)",
					"&aTeleport to coordinates"
			);

			private static void init() {
				setPathPrefix("Edit_Menu.Return_Location_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}

		public static final class YLimitButton {
			public static CompMaterial MATERIAL = CompMaterial.PISTON;

			public static String NAME = "Y Limit";

			public static List<String> LORE = Arrays.asList(
					"",
					"Current: &6{ylimit}",
					"",
					"Click to update"
			);

			private static void init() {
				setPathPrefix("Edit_Menu.Y_Limit_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}

		public static final class StatusOnButton {
			public static CompMaterial MATERIAL = CompMaterial.GREEN_CONCRETE_POWDER;

			public static String NAME = "&7Status &2ON";

			public static List<String> LORE = Arrays.asList(
					"",
					"Click to set &4OFF"
			);

			private static void init() {
				setPathPrefix("Edit_Menu.Status_On_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}

		public static final class StatusOffButton {
			public static CompMaterial MATERIAL = CompMaterial.RED_CONCRETE_POWDER;

			public static String NAME = "&7Status &4OFF";

			public static List<String> LORE = Arrays.asList(
					"",
					"Click to set &2ON"
			);

			private static void init() {
				setPathPrefix("Edit_Menu.Status_Off_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}

		public static final class RemoveButton {
			public static CompMaterial MATERIAL = CompMaterial.BARRIER;

			public static String NAME = "&4&lRemove return location";

			public static List<String> LORE = Arrays.asList(
					"",
					"&7The config of the",
					"&7current world will",
					"&7be removed permanently."
			);

			private static void init() {
				setPathPrefix("Info_Menu.Remove_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}
	}

	public static final class ConfirmRemoveMenu {
		public static String TITLE = "&0Confirm removal";

		private static void init() {
			setPathPrefix("Confirm_Remove_Menu");

			if (isSetDefault("Title"))
				TITLE = getString("Title");
		}

		public static final class ConfirmButton {
			public static CompMaterial MATERIAL = CompMaterial.RED_WOOL;

			public static String NAME = "&6&lRemove return location";

			public static List<String> LORE = Arrays.asList(
					"",
					"&7Confirm that will remove",
					"&7return location permanently.",
					"&cCannot be undone."
			);

			private static void init() {
				setPathPrefix("Confirm_Remove_Menu.Confirm_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}

		public static final class ReturnButton {
			public static CompMaterial MATERIAL = CompMaterial.OAK_DOOR;

			public static String NAME = "&4&lReturn";

			public static List<String> LORE = Arrays.asList(
					"",
					"Return back."
			);

			private static void init() {
				setPathPrefix("Confirm_Remove_Menu.Return_Button");

				if (isSetDefault("Material"))
					MATERIAL = getMaterial("Material");

				if (isSetDefault("Name"))
					NAME = getString("Name");

				if (isSetDefault("Lore"))
					LORE = getStringList("Lore");
			}
		}
	}

}
