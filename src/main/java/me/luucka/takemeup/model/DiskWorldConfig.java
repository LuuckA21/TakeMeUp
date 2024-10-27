package me.luucka.takemeup.model;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.World;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.SerializeUtil;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.remain.CompSound;
import org.mineacademy.fo.settings.ConfigItems;
import org.mineacademy.fo.settings.YamlConfig;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
public class DiskWorldConfig extends YamlConfig {

	private static final ConfigItems<DiskWorldConfig> LOADED_DISK_WORLD_CONFIG = ConfigItems.fromFolder("worlds", DiskWorldConfig.class);

	private Location returnLocation;
	private Integer yLimit;
	private Status status;
	private DiskSound soundAfterTeleport;

	private DiskWorldConfig(String worldName) {
		this(worldName, null);
	}

	public DiskWorldConfig(String worldName, Location returnLocation) {
		this(
				worldName,
				returnLocation,
				-100,
				Status.ON,
				DiskSound.deserialize(SerializedMap.ofArray(
						"Type", CompSound.SUCCESSFUL_HIT,
						"Volume", CompSound.DEFAULT_VOLUME,
						"Pitch", CompSound.DEFAULT_PITCH
				))
		);
	}

	public DiskWorldConfig(String worldName, Location returnLocation, @Nullable Integer yLimit, @Nullable Status status, @Nullable DiskSound soundAfterTeleport) {
		this.returnLocation = returnLocation;
		this.yLimit = yLimit;
		this.status = status;
		this.soundAfterTeleport = soundAfterTeleport;

		setHeader(
				Common.configLine(),
				"Location is formatted like this:",
				"    <world> <x> <y> <z> <yaw> <pitch>",
				Common.configLine() + "\n"
		);

		loadConfiguration(NO_DEFAULT, "worlds/" + worldName + ".yml");
	}

	public World getWorld() {
		return returnLocation.getWorld();
	}

	public void setReturnLocation(Location returnLocation) {
		this.returnLocation = returnLocation;
		save();
	}

	public void setyLimit(Integer yLimit) {
		this.yLimit = yLimit;
		save();
	}

	public void setStatus(Status status) {
		this.status = status;
		save();
	}

	public void setStatusOn() {
		setStatus(Status.ON);
	}

	public void setStatusOff() {
		setStatus(Status.OFF);
	}

	public boolean isOn() {
		return status == Status.ON;
	}

	public Status getInvertedStatus() {
		return isOn() ? Status.OFF : Status.ON;
	}

	public void setSoundAfterTeleport(DiskSound soundAfterTeleport) {
		this.soundAfterTeleport = soundAfterTeleport;
		save();
	}

	@Override
	protected void onLoad() {
		if (returnLocation != null && yLimit != null && status != null) {
			save();
			return;
		}

		returnLocation = SerializeUtil.deserializeLocationD(getString("Location"));
		yLimit = getInteger("YLimit");
		status = get("Status", Status.class);
		soundAfterTeleport = get("Sound", DiskSound.class);
	}

	@Override
	protected void onSave() {
		set("Location", SerializeUtil.serializeLocD(returnLocation));
		set("YLimit", yLimit);
		set("Status", status);
		set("Sound", soundAfterTeleport);
	}

	// ------------------------------------------------------------------------------------------------------------
	// Static
	// ------------------------------------------------------------------------------------------------------------

	public static void createDiskWorldConfig(@NonNull Location location) {
		String worldName = location.getWorld().getName();
		LOADED_DISK_WORLD_CONFIG.loadOrCreateItem(worldName, () -> new DiskWorldConfig(worldName, location));
	}

	public static void loadDiskWoldConfigs() {
		LOADED_DISK_WORLD_CONFIG.loadItems();
	}

	public static void removeDiskWorldConfig(final DiskWorldConfig diskWorldConfig) {
		LOADED_DISK_WORLD_CONFIG.removeItem(diskWorldConfig);
	}

	public static boolean isDiskWorldConfigLoaded(final String worldName) {
		return LOADED_DISK_WORLD_CONFIG.isItemLoaded(worldName);
	}

	public static Optional<DiskWorldConfig> findDiskWorldConfig(final Location location) {
		return findDiskWorldConfig(location.getWorld());
	}

	public static Optional<DiskWorldConfig> findDiskWorldConfig(final World world) {
		return findDiskWorldConfig(world.getName());
	}

	public static Optional<DiskWorldConfig> findDiskWorldConfig(@NonNull final String worldName) {
		return Optional.ofNullable(LOADED_DISK_WORLD_CONFIG.findItem(worldName));
	}

	public static List<DiskWorldConfig> getAllDiskWorldConfig() {
		return LOADED_DISK_WORLD_CONFIG.getItems();
	}

	public static Set<String> getAllDiskWorldConfigNames() {
		return LOADED_DISK_WORLD_CONFIG.getItemNames();
	}

	public enum Status {
		ON,
		OFF
	}

}
