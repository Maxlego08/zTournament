package fr.maxlego08.ztournament.command.commands.kits;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKitsEdit extends VCommand {

	public CommandTournamentKitsEdit(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_KIT_USE);
		this.addSubCommand("edit");
		this.addRequireArg("kit");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		tournament.getKits().editKit(getPlayer(), argAsString(0));
		return CommandType.SUCCESS;
	}

}
