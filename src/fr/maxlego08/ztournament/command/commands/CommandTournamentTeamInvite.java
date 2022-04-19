package fr.maxlego08.ztournament.command.commands;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentTeamInvite extends VCommand {

	public CommandTournamentTeamInvite(ZTournamentPlugin plugin) {
		super(plugin);
		this.addSubCommand("invite");
		this.addRequireArg("joueur");
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZTOURNAMENT_TEAM_INVITE);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		Player zPlayer = argAsPlayer(0);
		tournament.invitePlayer(player, zPlayer);

		return CommandType.SUCCESS;
	}

}
