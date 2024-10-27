package me.luucka.takemeup;

import me.luucka.takemeup.model.DiskWorldConfig;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.SerializeUtil;
import org.mineacademy.fo.plugin.SimplePlugin;

public class TakeMeUp extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		SerializeUtil.addSerializer(DiskWorldConfig.Status.class, Enum::name);
	}

	@Override
	protected void onReloadablesStart() {
		DiskWorldConfig.loadDiskWoldConfigs();
		TeleportManager.init();
	}

	@Override
	protected void onPluginStop() {
		Common.cancelTasks();
	}

}
