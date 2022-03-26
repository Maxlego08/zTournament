package fr.maxlego08.ztournament.hider;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.Hider;

@SuppressWarnings("deprecation")
public class SpigotHider implements Hider{

	@Override
	public void hide(Player player, Player target) {
		player.hidePlayer(target);
	}

	@Override
	public void show(Player player, Player target) {
		player.showPlayer(target);
	}	


}
