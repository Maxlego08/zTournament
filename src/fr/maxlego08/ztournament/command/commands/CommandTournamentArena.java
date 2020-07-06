package fr.maxlego08.ztournament.command.commands;

import org.bukkit.Location;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentArena extends VCommand {

	public CommandTournamentArena() {
		this.setPermission(Permission.ZTOURNAMENT_ARENA);
		this.addSubCommand("arena");
		this.addRequireArg("loc1");
		this.addOptionalArg("loc2");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		Location location = argAsLocation(0);
		Location location2 = argAsLocation(1, player.getLocation());
		tournament.createArena(sender, location, location2);

		return CommandType.SUCCESS;
	}

}
