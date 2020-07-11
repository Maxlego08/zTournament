package fr.maxlego08.ztournament;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import fr.maxlego08.ztournament.zcore.utils.builder.ItemBuilder;

public class TeamObject extends ZUtils implements Team {

	private final String name;
	private final int maxPlayers;
	private final List<OfflinePlayer> realPlayers = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private final List<String> users = new ArrayList<>();
	private final Player owner;
	private boolean isInDuel = false;
	private List<Player> invitePlayers = new ArrayList<>();

	private int position;

	/**
	 * @param name
	 * @param maxPlayers
	 */
	public TeamObject(String name, int maxPlayers, Player owner) {
		super();
		this.name = name;
		this.maxPlayers = maxPlayers;
		this.owner = owner;
		this.players.add(owner);
		this.realPlayers.add(owner);
		this.users.add(owner.getName());
	}

	/**
	 * 
	 * @param message
	 * @param args
	 */
	public void message(String message, Object... args) {
		realPlayers.forEach(player -> {
			if (player.isOnline())
				super.message(player.getPlayer(), message, args);
		});
	}

	/**
	 * 
	 * @param location
	 */
	public void teleport(Location location) {
		players.forEach(player -> {
			if (player.isOnline())
				player.teleport(location);
		});
	}

	public void clear() {
		realPlayers.forEach(player -> {
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

		if (!player.isOnline())
			return;

		PlayerInventory inventory = player.getInventory();

		ItemBuilder builder = new ItemBuilder(Material.IRON_LEGGINGS, "�eTournois PVP");
		builder.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		builder.addEnchant(Enchantment.DURABILITY, 7);

		inventory.setLeggings(builder.build());

		builder = new ItemBuilder(Material.IRON_CHESTPLATE, "�eTournois PVP");
		builder.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		builder.addEnchant(Enchantment.DURABILITY, 7);
		inventory.setChestplate(builder.build());

		builder = new ItemBuilder(Material.IRON_BOOTS, "�eTournois PVP");
		builder.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		builder.addEnchant(Enchantment.DURABILITY, 7);

		inventory.setBoots(builder.build());

		builder = new ItemBuilder(Material.IRON_HELMET, "�eTournois PVP");
		builder.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		builder.addEnchant(Enchantment.DURABILITY, 7);

		inventory.setHelmet(builder.build());

		builder = new ItemBuilder(Material.DIAMOND_SWORD, "�eTournois PVP");
		builder.addEnchant(Enchantment.DAMAGE_ALL, 2);
		inventory.setItem(0, builder.build());

		builder = new ItemBuilder(Material.GOLDEN_APPLE, 4, "�eTournois PVP");
		inventory.setItem(1, builder.build());

		builder = new ItemBuilder(Material.POTION, 1, "�ePotion");
		builder.durability(8226);
		inventory.setItem(2, builder.build());

		builder = new ItemBuilder(Material.POTION, 1, "�ePotion");
		builder.durability(8226);
		inventory.setItem(3, builder.build());

		for (int a = 4; a != 16; a++) {

			builder = new ItemBuilder(Material.POTION, 1, "�ePotion");
			builder.durability(16421);
			inventory.setItem(a, builder.build());

		}

	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean match(Player player) {
		return realPlayers.contains(player);
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean join(Player player) {
		int amount = players.size() + 1;
		if (amount > maxPlayers)
			return false;

		players.add(player);
		realPlayers.add(player);
		users.add(player.getName());

		invitePlayers.remove(player);
		return true;
	}

	/**
	 * Permet de voir une �quipe
	 * 
	 * @param team
	 */
	@SuppressWarnings("deprecation")
	public void show(Team team) {

		Bukkit.getOnlinePlayers().forEach(p -> {
			realPlayers.forEach(e -> {
				if (e.isOnline())
					e.getPlayer().showPlayer(p);
			});
		});

		hide();
		realPlayers.forEach(player -> {
			team.getRealPlayers().forEach(zPlayer -> {
				if (player.isOnline())
					player.getPlayer().showPlayer(zPlayer.getPlayer());
			});
			realPlayers.forEach(currentPlayer -> {
				if (player.isOnline())
					player.getPlayer().showPlayer(currentPlayer.getPlayer());
			});
		});
	}

	/**
	 * Permet de cacher une �quipe
	 * 
	 * @param team
	 */
	@SuppressWarnings("deprecation")
	public void hide(Team team) {

		Bukkit.getOnlinePlayers().forEach(p -> {
			realPlayers.forEach(e -> {
				if (e.isOnline())
					e.getPlayer().hidePlayer(p);
			});
		});

		show();
		realPlayers.forEach(player -> {
			team.getRealPlayers().forEach(zPlayer -> {
				if (player.isOnline())
					player.getPlayer().hidePlayer(zPlayer.getPlayer());
			});
			realPlayers.forEach(currentPlayer -> {
				if (player.isOnline())
					player.getPlayer().hidePlayer(currentPlayer.getPlayer());
			});
		});
	}

	public void heal() {
		realPlayers.forEach(player -> {
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
		realPlayers.forEach(player -> show(player));
	}

	/**
	 * 
	 */
	public void hide() {
		realPlayers.forEach(player -> hide(player));
	}

	/**
	 * 
	 * @param e
	 */
	@SuppressWarnings("deprecation")
	private void show(OfflinePlayer e) {
		if (e.isOnline())
			Bukkit.getOnlinePlayers().forEach(player -> e.getPlayer().showPlayer(player));
	}

	/**
	 * 
	 * @param e
	 */
	@SuppressWarnings("deprecation")
	private void hide(OfflinePlayer e) {
		if (e.isOnline())
			Bukkit.getOnlinePlayers().forEach(player -> e.getPlayer().hidePlayer(player));
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

		message("�eVotre team vient d'�tre dissoute.");

	}

	/**
	 * 
	 * @param player
	 */
	public void leave(Player player) {

		this.realPlayers.remove(player);
		this.players.remove(player);
		this.users.remove(player.getName());

		message("�f%s �evient de quitter votre �quipe.", player.getName());

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
				message("�f" + player.getName() + " �evient d'�tre disqualif� de votre �quipe.");
				players.remove(player);
				iterator.remove();
			} else if (player.isOnline() && !player.getPlayer().isValid()) {
				message("�f" + player.getName() + " �evient d'�tre disqualif� de votre �quipe.");
				players.remove(player);
				iterator.remove();
			}

		}
		return players.size() > 0;
	}

	@Override
	public void message(Message message) {
		this.message(message.getMessage());
	}

}
