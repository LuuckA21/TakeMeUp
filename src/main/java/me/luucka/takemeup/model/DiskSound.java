package me.luucka.takemeup.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.remain.CompSound;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiskSound implements ConfigSerializable {

	private CompSound sound;
	private Float volume;
	private Float pitch;

	@Override
	public SerializedMap serialize() {
		SerializedMap map = new SerializedMap();

		map.put("Type", sound.name());
		map.put("Volume", volume);
		map.put("Pitch", pitch);

		return map;
	}

	public static DiskSound deserialize(SerializedMap map) {
		DiskSound sound = new DiskSound();

		sound.setSound(map.get("Type", CompSound.class));
		sound.setVolume(map.getFloat("Volume"));
		sound.setPitch(map.getFloat("Pitch"));

		return sound;
	}

	public void play(Entity entity) {
		sound.play(entity, volume, pitch);
	}
}
