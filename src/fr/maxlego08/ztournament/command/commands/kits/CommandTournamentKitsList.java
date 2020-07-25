package fr.maxlego08.ztournament.command.commands.kits;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKitsList extends VCommand {

	public CommandTournamentKitsList() {
		this.setPermission(Permission.ZTOURNAMENT_KIT_USE);
		this.addSubCommand("list");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		message(sender, "�eKit�8: �7%s", toList(tournament.getKits().getNames(), "�8", "�7"));

		return CommandType.SUCCESS;
	}

}