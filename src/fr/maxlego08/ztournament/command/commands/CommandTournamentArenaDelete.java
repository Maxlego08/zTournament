package fr.maxlego08.ztournament.command.commands;

import java.util.UUID;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentArenaDelete extends VCommand {

	public CommandTournamentArenaDelete() {
		this.setPermission(Permission.ZTOURNAMENT_DELETE);
		this.addSubCommand("delete");
		this.addRequireArg("uuid");
		this.setDescription("Allows you to delete an arena. Makes /ztournament list to retrieve the arena list (json message).");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		UUID uuid = UUID.fromString(argAsString(0));
		tournament.deleteArena(sender, uuid);

		return CommandType.SUCCESS;
	}

}
