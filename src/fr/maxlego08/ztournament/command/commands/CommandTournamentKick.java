package fr.maxlego08.ztournament.command.commands;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKick extends VCommand {

	public CommandTournamentKick(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_KICK);
		this.addSubCommand("kick");
		this.addRequireArg("player");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {
		Player player = this.argAsPlayer(0);
		this.tournament.kick(sender, player);
		return CommandType.SUCCESS;
	}

}
