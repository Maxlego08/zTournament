package fr.maxlego08.ztournament.command.commands.kits;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKitsCreate extends VCommand {

	public CommandTournamentKitsCreate(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_KIT_USE);
		this.addSubCommand("create");
		this.addRequireArg("kit");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.getKits().createKit(sender, argAsString(0));
		return CommandType.SUCCESS;
	}

}
