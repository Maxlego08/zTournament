package fr.maxlego08.ztournament.command.commands.kits;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKitsDelete extends VCommand {

	public CommandTournamentKitsDelete() {
		this.setPermission(Permission.ZTOURNAMENT_KIT_USE);
		this.addSubCommand("delete");
		this.addRequireArg("kit");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.getKits().deleteKit(sender, argAsString(0));
		return CommandType.SUCCESS;
	}

}
