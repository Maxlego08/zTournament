package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentTeamLeave extends VCommand {

	public CommandTournamentTeamLeave(ZTournamentPlugin plugin) {
		super(plugin);
		this.addSubCommand("leave");
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZTOURNAMENT_TEAM_LEAVE);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin
			main) {

		tournament.leave(player, true);

		return CommandType.SUCCESS;
	}

}
