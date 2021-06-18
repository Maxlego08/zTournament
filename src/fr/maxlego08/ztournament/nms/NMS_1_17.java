package fr.maxlego08.ztournament.nms;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.NMS;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;

public class NMS_1_17 extends ZUtils implements NMS {

	@Override
	public void sendTitle(Player player, String title, String subtitle, int start, int time, int end) {
		player.sendTitle(title, subtitle, start, time, end);
	}
	
}
