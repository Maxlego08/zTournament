package fr.maxlego08.ztournament.zcore.utils.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import fr.maxlego08.ztournament.ArenaObject;
import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.zcore.ZPlugin;

public class ArenaAdapter extends TypeAdapter<Arena> {

	private static Type seriType = new TypeToken<Map<String, Object>>() {
	}.getType();

	private static String POS1 = "pos1";
	private static String POS2 = "pos2";
	private static String UUID = "uuid";
	private static String NAME = "name";

	public ArenaAdapter() {
	}

	@Override
	public void write(JsonWriter jsonWriter, Arena location) throws IOException {
		if (location == null) {
			jsonWriter.nullValue();
			return;
		}
		jsonWriter.value(getRaw(location));
	}

	@Override
	public Arena read(JsonReader jsonReader) throws IOException {
		if (jsonReader.peek() == JsonToken.NULL) {
			jsonReader.nextNull();
			return null;
		}
		return fromRaw(jsonReader.nextString());
	}

	private String getRaw(Arena arena) {
		Map<String, Object> serial = new HashMap<String, Object>();
		serial.put(UUID, arena.getUniqueId());
		serial.put(POS1, arena.getPos1String());
		serial.put(POS2, arena.getPos2String());
		serial.put(NAME, arena.getName());
		return ZPlugin.z().getGson().toJson(serial);
	}

	private Arena fromRaw(String raw) {
		Map<String, Object> keys = ZPlugin.z().getGson().fromJson(raw, seriType);
		java.util.UUID uuid = java.util.UUID.fromString((String) keys.get(UUID));

		String pos1 = (String) keys.get(POS1);
		String pos2 = (String) keys.get(POS2);
		String name = (String) keys.get(NAME);

		return new ArenaObject(uuid, name, pos1, pos2);
	}

}
