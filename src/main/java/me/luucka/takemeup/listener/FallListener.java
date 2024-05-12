package me.luucka.takemeup.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.luucka.takemeup.model.WorldConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.mineacademy.fo.annotation.AutoRegister;

@AutoRegister
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FallListener implements Listener {

	@Getter
	private static final FallListener instance = new FallListener();

	@EventHandler
	public void onFall(final EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			if (WorldConfig.TeleportManager.isPlayerInTeleport(player)) {
				event.setCancelled(true);
				WorldConfig.TeleportManager.removePlayerAfterTeleport(player);
			}
		}
	}
}
