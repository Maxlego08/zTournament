package fr.maxlego08.ztournament.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;

public class AdapterListener extends ZUtils implements Listener {

	private final ZTournamentPlugin template;

	public AdapterListener(ZTournamentPlugin template) {
		this.template = template;
	}

	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onConnect(event, event.getPlayer()));
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onQuit(event, event.getPlayer()));
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onMove(event, event.getPlayer()));
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		template.getListenerAdapters()
				.forEach(adapter -> adapter.onInventoryClick(event, (Player) event.getWhoClicked()));
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onEntityDeath(event, event.getEntity()));
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onInteract(event, event.getPlayer()));
	}

	@EventHandler
	public void onDrag(InventoryDragEvent event) {
		template.getListenerAdapters()
				.forEach(adapter -> adapter.onInventoryDrag(event, (Player) event.getWhoClicked()));
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onInventoryClose(event, (Player) event.getPlayer()));
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onDrop(event, event.getPlayer()));
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		template.getListenerAdapters()
				.forEach(adapter -> adapter.onCommand(event, event.getPlayer(), event.getMessage()));
	}

	@EventHandler
	public void onCommand(PlayerEggThrowEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onEgg(event, event.getPlayer(), event.getEgg()));
	}

	@EventHandler
	public void onCommand(ProjectileLaunchEvent event) {
		template.getListenerAdapters().forEach(adapter -> adapter.onProjectilLaunch(event, event.getEntity()));
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {

		if (event.getEntity() instanceof Player) {
			this.template.getListenerAdapters().forEach(adapter -> adapter.onPlayerDamage(event, event.getCause(),
					event.getDamage(), (Player) event.getEntity()));
		}

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDamage4(EntityDamageByEntityEvent event) {

		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			this.template.getListenerAdapters().forEach(adapter -> adapter.onPlayerDamage(event, event.getCause(),
					event.getDamage(), (Player) event.getDamager(), (Player) event.getEntity()));
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onDamageLow(EntityDamageByEntityEvent event) {

		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			this.template.getListenerAdapters().forEach(adapter -> adapter.onPlayerDamageLow(event, event.getCause(),
					event.getDamage(), (Player) event.getDamager(), (Player) event.getEntity()));
		} else if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile) {
			
			Projectile projectile = (Projectile) event.getDamager();
			
			this.template.getListenerAdapters().forEach(adapter -> adapter.onPlayerDamageByArrow(event, event.getCause(),
					event.getDamage(), projectile, (Player) event.getEntity()));
			
		}
	}
}
