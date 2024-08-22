package me.luucka.takemeup;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.luucka.takemeup.model.WorldConfig;
import org.bukkit.Effect;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompProperty;
import org.mineacademy.fo.remain.CompSound;
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
							WorldConfig worldConfig = WorldConfig.find(player.getWorld()).orElse(null);
							return worldConfig != null
									&& worldConfig.isOn()
									&& player.getLocation().getY() < worldConfig.getOffset();
						})
						.forEach(player -> WorldConfig.find(player.getWorld()).ifPresent(worldConfig -> {
									CompProperty.INVULNERABLE.apply(player, true);
									Common.runLater(() -> player.teleport(worldConfig.getSpawnLocation()));
									Common.runLaterAsync(2, () -> {
												player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, null);
												CompSound.SUCCESSFUL_HIT.play(player);
											}
									);
									Common.runLaterAsync(50, () -> CompProperty.INVULNERABLE.apply(player, false));
								}
						))
		);
	}

}
