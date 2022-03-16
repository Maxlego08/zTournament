package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentArenaList extends VCommand {

	public CommandTournamentArenaList() {
		this.setPermission(Permission.ZTOURNAMENT_ARENA_SHOW);
		this.addSubCommand("list");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		this.tournament.sendArena(player);
		return CommandType.SUCCESS;
	}

}
