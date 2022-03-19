package fr.maxlego08.ztournament.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import fr.maxlego08.ztournament.zcore.utils.ZUtils;

@SuppressWarnings("deprecation")
public abstract class ListenerAdapter extends ZUtils {

	protected void onConnect(PlayerJoinEvent event, Player player) {
	}

	protected void onQuit(PlayerQuitEvent event, Player player) {
	}

	protected void onMove(PlayerMoveEvent event, Player player) {
	}

	protected void onInventoryClick(InventoryClickEvent event, Player player) {
	}

	protected void onInventoryClose(InventoryCloseEvent event, Player player) {
	}

	protected void onInventoryDrag(InventoryDragEvent event, Player player) {
	}

	protected void onBlockBreak(BlockBreakEvent event, Player player) {
	}

	protected void onBlockPlace(BlockPlaceEvent event, Player player) {
	}

	protected void onEntityDeath(EntityDeathEvent event, Entity entity) {
	}

	protected void onInteract(PlayerInteractEvent event, Player player) {
	}

	protected void onPlayerTalk(AsyncPlayerChatEvent event, String message) {
	}

	protected void onCraftItem(CraftItemEvent event) {
	}

	protected void onCommand(PlayerCommandPreprocessEvent event, Player player, String message) {
	}

	protected void onGamemodeChange(PlayerGameModeChangeEvent event, Player player) {
	}

	public void onDrop(PlayerDropItemEvent event, Player player) {
	}

	public void onPickUp(PlayerPickupItemEvent event, Player player) {
	}

	public void onMobSpawn(CreatureSpawnEvent event) {
	}

	public void onItemisOnGround(PlayerDropItemEvent event, Player player, Item item, Location location) {
	}

	public void onItemMove(PlayerDropItemEvent event, Player player, Item item, Location location, Block block) {
	}

	public void onPlayerWalk(PlayerMoveEvent event, Player player, int i) {
	}

	public void onPlayerDamage(EntityDamageByEntityEvent event, DamageCause cause, double damage, Player damager,
			Player entity) {
	}

	public void onPlayerDamage(EntityDamageEvent event, DamageCause cause, double damage, Player entity) {
	}

	public void onPlayerDamageLow(EntityDamageByEntityEvent event, @NotNull DamageCause cause, double damage,
			Player damager, Player entity) {
	}

	public void onEgg(PlayerEggThrowEvent event, Player player, Egg egg) {
	}

	public void onProjectilLaunch(ProjectileLaunchEvent event, Projectile entity) {
	}

	public void onPlayerDamageByArrow(EntityDamageByEntityEvent event, @NotNull DamageCause cause, double damage,
			Projectile projectile, Player entity) {
	}

}
