package fr.maxlego08.ztournament.command.commands;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentTeamInvite extends VCommand {

	public CommandTournamentTeamInvite() {
		this.addSubCommand("invite");
		this.addRequireArg("joueur");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		Player zPlayer = argAsPlayer(0);
		tournament.invitePlayer(player, zPlayer);

		return CommandType.SUCCESS;
	}

}
