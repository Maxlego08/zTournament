package fr.maxlego08.ztournament.nms;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_10_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.NMS;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;

public class NMS_1_10 extends ZUtils implements NMS {

	@Override
	public void sendTitle(Player player, String title, String subtitle, int start, int time, int end) {
		
		CraftPlayer craftPlayer = (CraftPlayer) player;

		PacketPlayOutTitle packetTimes = new PacketPlayOutTitle(start, time, end);
		craftPlayer.getHandle().playerConnection.sendPacket(packetTimes);

		if (title != null) {
			PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE,
					CraftChatMessage.fromString(title)[0], start, time, end);
			craftPlayer.getHandle().playerConnection.sendPacket(packetTitle);
		}

		if (subtitle != null) {
			PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
					CraftChatMessage.fromString(subtitle)[0], start, time, end);
			craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
		}		
		
	}
	
}
