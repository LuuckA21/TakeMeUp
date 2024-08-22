package me.luucka.takemeup;

import me.luucka.takemeup.model.WorldConfig;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.SerializeUtil;
import org.mineacademy.fo.plugin.SimplePlugin;

public class TakeMeUp extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		SerializeUtil.addSerializer(WorldConfig.Status.class, Enum::name);
	}

	@Override
	protected void onReloadablesStart() {
		WorldConfig.init();
		TeleportManager.init();
	}

	@Override
	protected void onPluginStop() {
		Common.cancelTasks();
	}

}
