package fr.maxlego08.ztournament;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.maxlego08.ztournament.api.Hider;
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import net.minelink.ctplus.CombatTagPlus;

public class ZTeam extends ZUtils implements Team {

	private final String name;
	private final int maxPlayers;
	private final List<OfflinePlayer> realPlayers = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private final List<String> users = new ArrayList<>();
	private final Player owner;
	private boolean isInDuel = false;
	private List<Player> invitePlayers = new ArrayList<>();
	private final Kit kit;
	private int position;

	private final Hider hider;

	/**
	 * 
	 * @param name
	 * @param maxPlayers
	 * @param owner
	 * @param kit
	 */
	public ZTeam(Hider hider, String name, int maxPlayers, Player owner, Kit kit) {
		super();
		this.hider = hider;
		this.name = name;
		this.maxPlayers = maxPlayers;
		this.owner = owner;
		this.players.add(owner);
		this.realPlayers.add(owner);
		this.kit = kit;
		this.users.add(owner.getName());
	}

	/**
	 * 
	 * @param message
	 * @param args
	 */
	public void message(String message, Object... args) {
		this.realPlayers.forEach(player -> {
			if (player.isOnline()) {
				super.message(player.getPlayer(), message, args);
			}
		});
	}

	/**
	 * 
	 * @param location
	 */
	public void teleport(Location location) {
		this.realPlayers.forEach(player -> {

			if (Bukkit.getServer().getPluginManager().isPluginEnabled("CombatTagPlus")) {

				CombatTagPlus plugin = (CombatTagPlus) Bukkit.getServer().getPluginManager().getPlugin("CombatTagPlus");
				plugin.getTagManager().untag(player.getUniqueId());

			}

			if (player.isOnline()) {
				player.getPlayer().teleport(location);
			}
		});
	}

	public void clear() {
		this.realPlayers.forEach(player -> {
			if (player.isOnline()) {
				player.getPlayer().getInventory().clear();
				player.getPlayer().getInventory().setBoots(null);
				player.getPlayer().getInventory().setChestplate(null);
				player.getPlayer().getInventory().setLeggings(null);
				player.getPlayer().getInventory().setHelmet(null);
				player.getPlayer().setItemOnCursor(null);
				player.getPlayer().getOpenInventory().getTopInventory().clear();
				player.getPlayer().setGameMode(GameMode.SURVIVAL);
				player.getPlayer().getActivePotionEffects().forEach(e -> {
					player.getPlayer().removePotionEffect(e.getType());
				});
			}
		});

	}

	public void give() {
		clear();
		players.forEach(player -> {
			if (player.isOnline())
				give(player);
		});
	}

	/**
	 * 
	 * @param player
	 */
	public void give(Player player) {

		if (!player.isOnline()) {
			return;
		}

		PlayerInventory inventory = player.getInventory();

		if (this.kit.getChestplate() != null) {
			inventory.setChestplate(this.kit.getChestplate());
		}
		if (this.kit.getLeggings() != null) {
			inventory.setLeggings(this.kit.getLeggings());
		}
		if (this.kit.getBoots() != null) {
			inventory.setBoots(this.kit.getBoots());
		}
		if (this.kit.getHelmet() != null) {
			inventory.setHelmet(this.kit.getHelmet());
		}

		this.kit.getItems().forEach((slot, item) -> inventory.setItem(slot, item));

	}

	/**
	 * Permet de savoir si un joueur est dans une équipe
	 * 
	 * @param player
	 * @return boolean
	 */
	public boolean match(Player player) {
		return this.realPlayers.contains(player);
	}

	/**
	 * Permet de rejoindre une équipe
	 * 
	 * @param player
	 * @return boolean
	 */
	public boolean join(Player player) {

		int amount = this.players.size() + 1;
		if (amount > this.maxPlayers) {
			return false;
		}

		this.players.add(player);
		this.realPlayers.add(player);
		this.users.add(player.getName());

		this.invitePlayers.remove(player);
		return true;
	}

	/**
	 * Permet de voir une équipe
	 * 
	 * @param team
	 */
	public void show(Team team) {

		Bukkit.getOnlinePlayers().forEach(player -> {
			this.realPlayers.forEach(user -> {
				if (user.isOnline()) {
					this.hider.show(user.getPlayer(), player);
				}
			});
		});

		hide();
		this.realPlayers.forEach(player -> {
			team.getRealPlayers().forEach(zPlayer -> {
				if (player.isOnline()) {
					this.hider.show(player.getPlayer(), zPlayer.getPlayer());
				}
			});
			this.realPlayers.forEach(currentPlayer -> {
				if (player.isOnline()) {
					this.hider.show(player.getPlayer(), currentPlayer.getPlayer());
				}
			});
		});
	}

	/**
	 * Permet de cacher une équipe
	 * 
	 * @param team
	 */
	@SuppressWarnings("deprecation")
	public void hide(Team team) {

		Bukkit.getOnlinePlayers().forEach(p -> {
			this.realPlayers.forEach(e -> {
				if (e.isOnline())
					e.getPlayer().hidePlayer(p);
			});
		});

		show();
		this.realPlayers.forEach(player -> {
			team.getRealPlayers().forEach(zPlayer -> {
				if (player.isOnline())
					player.getPlayer().hidePlayer(zPlayer.getPlayer());
			});
			this.realPlayers.forEach(currentPlayer -> {
				if (player.isOnline())
					player.getPlayer().hidePlayer(currentPlayer.getPlayer());
			});
		});
	}

	public void heal() {
		this.realPlayers.forEach(player -> {
			if (player.isOnline()) {
				player.getPlayer().setHealth(20.0);
				player.getPlayer().setFoodLevel(20);
				player.getPlayer().setFireTicks(0);
			}
		});
	}

	public boolean hasLoose() {
		return players.size() == 0;
	}

	/**
	 * 
	 */
	public void show() {
		this.realPlayers.forEach(player -> show(player));
	}

	/**
	 * 
	 */
	public void hide() {
		this.realPlayers.forEach(player -> hide(player));
	}

	/**
	 * Permet de voir tout les joueurs
	 * 
	 * @param offlinePlayer
	 */
	private void show(OfflinePlayer offlinePlayer) {
		if (offlinePlayer.isOnline()) {
			Bukkit.getOnlinePlayers().forEach(player -> this.hider.show(offlinePlayer.getPlayer(), player));
		}
	}

	/**
	 * Permet de cacher tout les joueurs
	 * 
	 * @param offlinePlayer
	 */
	private void hide(OfflinePlayer offlinePlayer) {
		if (offlinePlayer.isOnline()) {
			Bukkit.getOnlinePlayers().forEach(player -> this.hider.hide(offlinePlayer.getPlayer(), player));
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the maxPlayers
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param player
	 */
	public void death(Player player) {
		players.remove(player);
	}

	public boolean contains(Player player) {
		return realPlayers.contains(player);
	}

	public void disband() {

		message(Message.TOURNAMENT_TEAM_DISBAND);

	}

	/**
	 * 
	 * @param player
	 */
	public void leave(Player player) {

		this.realPlayers.remove(player);
		this.players.remove(player);
		this.users.remove(player.getName());

		message(Message.TOURNAMENT_TEAM_LEAVE_INFO, player.getName());

	}

	public boolean isInDuel() {
		return isInDuel;
	}

	public void setInDuel(boolean b) {
		this.isInDuel = b;
	}

	public void reMap() {
		this.players = this.realPlayers.stream().filter(e -> e.isOnline()).map(e -> e.getPlayer())
				.collect(Collectors.toList());
	}

	public List<OfflinePlayer> getRealPlayers() {
		return realPlayers;
	}

	public boolean isAlive(Player zPlayer) {
		return players.contains(zPlayer);
	}

	public void invite(Player player) {
		this.invitePlayers.add(player);
	}

	public void removeInvite(Player player) {
		this.invitePlayers.remove(player);
	}

	public boolean isInvite(Player player) {
		return this.invitePlayers.contains(player);
	}

	public boolean isOwner(Player player) {
		return owner.getUniqueId().equals(player.getUniqueId());
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isValid() {

		Iterator<OfflinePlayer> iterator = realPlayers.iterator();
		while (iterator.hasNext()) {

			OfflinePlayer player = iterator.next();
			if (!player.isOnline()) {
				message("§f" + player.getName() + " §evient d'être disqualifé de votre équipe.");
				players.remove(player);
				iterator.remove();
			} else if (player.isOnline() && !player.getPlayer().isValid()) {
				message("§f" + player.getName() + " §evient d'être disqualifé de votre équipe.");
				players.remove(player);
				iterator.remove();
			}

		}
		return players.size() > 0;
	}

	@Override
	public void message(Message message, Object... args) {
		this.players.forEach(player -> this.message(player, message, args));
	}

	@Override
	public fr.maxlego08.ztournament.api.Kit getKit() {
		return kit;
	}

	@Override
	public boolean match(OfflinePlayer player) {
		return realPlayers.contains(player);
	}

	@Override
	public boolean contains(UUID key) {
		return this.realPlayers.stream().anyMatch(e -> e.getUniqueId().equals(key));
	}

}
