package fr.maxlego08.ztournament.command.commands;

import java.util.Optional;

import org.bukkit.Location;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Selection;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentArena extends VCommand {

	public CommandTournamentArena() {
		this.setPermission(Permission.ZTOURNAMENT_ARENA);
		this.addSubCommand("arena");
		this.addRequireArg("name");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		String name = argAsString(0);
		Optional<Selection> optional = plugin.getListener().getSelection(player.getUniqueId());

		if (!optional.isPresent()) {
			message(sender, Message.TOURNAMENT_CREATE_ERROR_SELECTION);
			return CommandType.DEFAULT;
		}

		Selection selection = optional.get();

		if (!selection.isValid()) {
			message(sender, Message.TOURNAMENT_CREATE_ERROR_SELECTION);
			return CommandType.DEFAULT;
		}

		Location minLocation = selection.getRightLocation();
		Location maxLocation = selection.getLeftLocation();
		plugin.getListener().clearSelection(player);
		tournament.createArena(sender, name, minLocation, maxLocation);

		return CommandType.SUCCESS;
	}

}
