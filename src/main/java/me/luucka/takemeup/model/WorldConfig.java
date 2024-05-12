package me.luucka.takemeup.model;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.SerializeUtil;
import org.mineacademy.fo.remain.Remain;
import org.mineacademy.fo.settings.ConfigItems;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.*;

@Getter
public class WorldConfig extends YamlConfig {

	private static final ConfigItems<WorldConfig> LOADED_WORLD_CONFIG = ConfigItems.fromFolder("worlds", WorldConfig.class);

	private Location spawnLocation;
	private Integer offset;
	private Status status;

	private WorldConfig(String worldName) {
		loadConfiguration(NO_DEFAULT, "worlds/" + worldName + ".yml");
	}

	public WorldConfig(Location spawnLocation, Integer offset, Status status) {
		this.spawnLocation = spawnLocation;
		this.offset = offset;
		this.status = status;

		setHeader(
				Common.configLine(),
				"Location is formatted like this:",
				"    <world> <x> <y> <z> <yaw> <pitch>",
				Common.configLine() + "\n"
		);

		loadConfiguration(NO_DEFAULT, "worlds/" + spawnLocation.getWorld().getName() + ".yml");
	}

	public World getWorld() {
		return spawnLocation.getWorld();
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
		save();
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
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

	@Override
	protected void onLoad() {
		if (spawnLocation != null && offset != null && status != null) {
			save();
			return;
		}

		spawnLocation = SerializeUtil.deserializeLocationD(getString("location"));
		offset = getInteger("offset");
		status = get("status", Status.class);
	}

	@Override
	protected void onSave() {
		set("location", SerializeUtil.serializeLocD(spawnLocation));
		set("offset", offset);
		set("status", status);
	}

	public static void init() {
		LOADED_WORLD_CONFIG.loadItems();
		TeleportManager.checkFallScheduler();
	}

	public static void createNew(Location location) {
		String worldName = location.getWorld().getName();
		LOADED_WORLD_CONFIG.loadOrCreateItem(worldName, () -> new WorldConfig(location, -100, Status.ON));
	}

	public static void delete(final WorldConfig worldConfig) {
		LOADED_WORLD_CONFIG.removeItem(worldConfig);
	}

	public static boolean isLoaded(final Location location) {
		return isLoaded(location.getWorld());
	}

	public static boolean isLoaded(final World world) {
		return find(world).isPresent();
	}

	public static Optional<WorldConfig> find(final Location location) {
		World world = location.getWorld();
		return world != null ? find(world) : Optional.empty();
	}

	public static Optional<WorldConfig> find(final World world) {
		return Optional.ofNullable(LOADED_WORLD_CONFIG.findItem(world.getName()));
	}

	public static List<WorldConfig> getAllItems() {
		return LOADED_WORLD_CONFIG.getItems();
	}

	public static Set<String> getAllItemNames() {
		return LOADED_WORLD_CONFIG.getItemNames();
	}

	public enum Status {
		ON,
		OFF
	}

	public boolean isOn() {
		return status == Status.ON;
	}

	public Status getInvertedStatus() {
		return isOn() ? Status.OFF : Status.ON;
	}

	public static class TeleportManager {

		private static final Set<UUID> PLAYER_IN_TELEPORT = new HashSet<>();

		public static void removePlayerAfterTeleport(final Player player) {
			PLAYER_IN_TELEPORT.remove(player.getUniqueId());
		}

		public static boolean isPlayerInTeleport(final Player player) {
			return PLAYER_IN_TELEPORT.contains(player.getUniqueId());
		}

		private static void checkFallScheduler() {
			Common.runTimerAsync(
					20,
					() -> {
						Collection<? extends Player> onlinePlayers = Remain.getOnlinePlayers();
						onlinePlayers.parallelStream().forEach(player -> {
							World world = player.getWorld();
							WorldConfig.find(world).ifPresent(worldConfig -> {
								if (worldConfig.isOn() && player.getLocation().getY() < worldConfig.getOffset()) {
									PLAYER_IN_TELEPORT.add(player.getUniqueId());
									Common.runLater(() -> {
										player.teleport(worldConfig.getSpawnLocation());
									});
								}
							});
						});
					}
			);
		}
	}

}
