package fr.maxlego08.ztournament;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.api.TournoisType;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import fr.maxlego08.ztournament.zcore.utils.storage.Persist;

public class TournamentManager extends ZUtils implements Tournament {

	@Override
	public void save(Persist persist) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(Persist persist) {
		// TODO Auto-generated method stub

	}

	@Override
	public Team getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getByPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duel getDuel(Team team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arena getAvaibleArena() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAvaibleArenaCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Team getByPassTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPlayer(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public Team getRandomTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDuelTeam() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLobbyLocation(Player sender, Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createRandomDuel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createArena(CommandSender sender, Location pos1, Location pos2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendArena(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArena(CommandSender sender, UUID uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canHurt(Player player, Player damager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inventoryHasItem(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTeam(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkTeam() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startTournois(CommandSender sender, TournoisType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startWave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTeam(Player player, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void joinTeam(Player player, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void invitePlayer(Player player, Player target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave(Player player, boolean message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loose(Team team, Duel duel, Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void canStartNextWave() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canUseCommand(CommandSender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stopTournois(CommandSender sender) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextWave(CommandSender sender) {
		// TODO Auto-generated method stub

	}

}
