package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentSetLobby extends VCommand {

	public CommandTournamentSetLobby(ZTournamentPlugin plugin) {
		super(plugin);

		this.addSubCommand("setlobby");
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZTOURNAMENT_SETLOBBY);

	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.setLobbyLocation(player, player.getLocation());
		return CommandType.SUCCESS;
	}

}
