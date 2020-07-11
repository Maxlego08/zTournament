package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournament extends VCommand {

	public CommandTournament() {

		this.addSubCommand(new CommandTournamentArena());
		this.addSubCommand(new CommandTournamentArenaList());
		this.addSubCommand(new CommandTournamentArenaDelete());
		this.addSubCommand(new CommandTournamentStart());
		this.addSubCommand(new CommandTournamentTeamCreate());
		this.addSubCommand(new CommandTournamentTeamLeave());
		this.addSubCommand(new CommandTournamentTeamJoin());
		this.addSubCommand(new CommandTournamentSetLobby());
		this.addSubCommand(new CommandTournamentTeamInvite());
		this.addSubCommand(new CommandTournamentStop());
		this.addSubCommand(new CommandTournamentWave());
		this.addSubCommand(new CommandTournamentVersion());
		this.addSubCommand(new CommandTournamentReload());
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		Message.TOURNAMENT_HELP.getMessages().forEach(m -> message(sender, m));
		if (sender.hasPermission(Permission.ZTOURNAMENT_HELP.getPermission())) 
			Message.TOURNAMENT_HELP_ADMIN.getMessages().forEach(m -> message(sender, m));

		return CommandType.SUCCESS;
	}

}
