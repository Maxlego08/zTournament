package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.command.commands.kits.CommandTournamentKits;
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
		this.addSubCommand(new CommandTournamentKits());
		this.addSubCommand(new CommandTournamentAxe());
		this.addSubCommand(new CommandTournamentPos1());
		this.addSubCommand(new CommandTournamentPos2());
		this.addSubCommand(new CommandTournamentKick());
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		messageWO(sender, Message.TOURNAMENT_HELP_HEADER);
		Message.TOURNAMENT_HELP.getMessages().forEach(m -> messageWO(sender, m));
		if (sender.hasPermission(Permission.ZTOURNAMENT_HELP.getPermission()))
			Message.TOURNAMENT_HELP_ADMIN.getMessages().forEach(m -> messageWO(sender, m));
		return CommandType.SUCCESS;
	}

}
