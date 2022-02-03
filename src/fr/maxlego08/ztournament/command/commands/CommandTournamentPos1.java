package fr.maxlego08.ztournament.command.commands;

import org.bukkit.Location;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentPos1 extends VCommand {

	public CommandTournamentPos1() {
		this.setConsoleCanUse(false);
		this.addSubCommand("pos1");
		this.addRequireArg("name");
		this.setPermission(Permission.ZTOURNAMENT_POS1);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		String name = argAsString(0);
		Location location = player.getLocation();
		tournament.setPosition(true, sender, name, location);
		
		return CommandType.SUCCESS;
	}

}
