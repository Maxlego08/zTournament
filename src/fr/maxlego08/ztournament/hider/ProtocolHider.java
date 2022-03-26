package fr.maxlego08.ztournament.hider;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.maxlego08.ztournament.api.Hider;

public class ProtocolHider extends EntityHider implements Hider {

	public ProtocolHider(Plugin plugin) {
		super(plugin, Policy.BLACKLIST);
	}

	@Override
	public void hide(Player player, Player target) {
		this.hideEntity(player, target);
	}

	@Override
	public void show(Player player, Player target) {
		this.showEntity(player, target);
	}

}
