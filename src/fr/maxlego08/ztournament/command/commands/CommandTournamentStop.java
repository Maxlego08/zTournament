package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentStop extends VCommand {

	public CommandTournamentStop(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_STOP);
		this.addSubCommand("stop");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.stopTournois(sender);
		return CommandType.SUCCESS;
	}


}
