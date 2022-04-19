package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentWave extends VCommand {

	public CommandTournamentWave(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_WAVE);
		this.addSubCommand("wave");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.nextWave(sender);
		return CommandType.SUCCESS;
	}

}
