package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentTeamCreate extends VCommand {

	public CommandTournamentTeamCreate() {
		this.addSubCommand("create");
		this.addRequireArg("nom de la team");
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZTOURNAMENT_TEAM_CREATE);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		String name = argAsString(0);
		tournament.createTeam(player, name);

		return CommandType.SUCCESS;
	}

}
