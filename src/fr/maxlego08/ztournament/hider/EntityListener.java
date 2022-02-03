package fr.maxlego08.ztournament.hider;

import java.util.Collection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;

public class EntityListener extends EntityHider implements Listener {

	private final Plugin plugin;

	public EntityListener(Plugin plugin) {
		super(plugin, Policy.BLACKLIST);
		this.plugin = plugin;
	}

	@EventHandler
	public void onSplash(PotionSplashEvent event) {
		ThrownPotion thrownPotion = event.getEntity();
		if (thrownPotion.getShooter() instanceof Player) {
			Player shooter = (Player) thrownPotion.getShooter();
			PacketListener listener = new PacketAdapter(this.plugin, PacketType.Play.Server.WORLD_EVENT) {
				@Override
				public void onPacketSending(PacketEvent event) {
					Player player = event.getPlayer();
					event.setCancelled(!shooter.canSee(player));
				}
			};
			this.addListenerAndRemove(listener, 20l);
			this.soundListener(shooter);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if (entity instanceof Player){
			Player player = (Player) entity;
			this.soundListener(player);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		this.soundListener(player);
	}
	
	@EventHandler
	public void onLaunch(ProjectileLaunchEvent event) {
		Projectile projectile = event.getEntity();
		if (projectile.getShooter() instanceof Player) {
			Player shooter = (Player) projectile.getShooter();
			Collection<? extends Player> onlinePlayers = this.plugin.getServer().getOnlinePlayers();
			onlinePlayers.stream().filter(p -> p != shooter && !shooter.canSee(p)).forEach(p -> {
				this.hideEntity(p, projectile);
			});
			this.soundListener(shooter);

		}
	}

	private void soundListener(Player shooter) {
		PacketListener listener = new PacketAdapter(this.plugin, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
			@Override
			public void onPacketSending(PacketEvent event) {
				Player player = event.getPlayer();
				event.setCancelled(!shooter.canSee(player));
			}
		};
		this.addListenerAndRemove(listener, 20l);
	}

	/**
	 * Add listener to protocol lib and remove it after x ticks
	 * 
	 * @param listener
	 * @param removeTicks
	 */
	protected void addListenerAndRemove(PacketListener listener, long removeTicks) {
		ProtocolLibrary.getProtocolManager().addPacketListener(listener);
		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
			ProtocolLibrary.getProtocolManager().removePacketListener(listener);
		}, removeTicks);
	}

}
