package me.luucka.takemeup;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.luucka.takemeup.model.DiskWorldConfig;
import org.bukkit.Effect;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompProperty;
import org.mineacademy.fo.remain.Remain;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TeleportManager {

	public static void init() {
		fallScheduler();
	}

	private static void fallScheduler() {
		Common.runTimerAsync(
				20,
				() -> Remain.getOnlinePlayers().stream()
						.filter(player -> {
							DiskWorldConfig diskWorldConfig = DiskWorldConfig.findDiskWorldConfig(player.getWorld()).orElse(null);
							return diskWorldConfig != null
									&& diskWorldConfig.isOn()
									&& player.getLocation().getY() < diskWorldConfig.getYLimit();
						})
						.forEach(player -> DiskWorldConfig.findDiskWorldConfig(player.getWorld()).ifPresent(diskWorldConfig -> {
									CompProperty.INVULNERABLE.apply(player, true);
									Common.runLater(() -> player.teleport(diskWorldConfig.getReturnLocation()));
									Common.runLaterAsync(2, () -> {
												player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, null);
//												CompParticle.REDSTONE.spawn(player.getLocation().add(0, 0.5, 0), 0.5, 1, 0.5, 0.1, 10, 1);

												diskWorldConfig.getSoundAfterTeleport().play(player);
											}
									);
									Common.runLaterAsync(50, () -> CompProperty.INVULNERABLE.apply(player, false));
								}
						))
		);
	}

}
