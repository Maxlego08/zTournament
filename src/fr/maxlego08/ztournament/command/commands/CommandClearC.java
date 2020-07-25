package fr.maxlego08.ztournament.command.commands;

import org.bukkit.Bukkit;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandClearC extends VCommand {

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		
		for(int a = 0; a != 200; a++)
			Bukkit.broadcastMessage("");
		
		return CommandType.SUCCESS;
	}

}
