package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentTeamJoin extends VCommand {

	public CommandTournamentTeamJoin() {
		this.addSubCommand("join");
		this.addRequireArg("name");
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZTOURNAMENT_TEAM_JOIN);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin
			main) {

		String name = argAsString(0);
		tournament.joinTeam(player, name);

		return CommandType.SUCCESS;
	}

}
