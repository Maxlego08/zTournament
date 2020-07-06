package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournament extends VCommand {

	public CommandTournament() {

		this.addSubCommand(new CommandTournamentArena());
		this.addSubCommand(new CommandTournamentArenaList());
		this.addSubCommand(new CommandTournamentArenaDelete());
		this.addSubCommand(new CommandTournamentStart());
		this.addSubCommand(new CommandTournamentTeamCreate());
		this.addSubCommand(new CommandTournamentTeamLeave());
		this.addSubCommand(new CommandTournamentTeamJoin());
		this.addSubCommand(new CommandTournamentSetLobby());
		this.addSubCommand(new CommandTournamentTeamInvite());
		this.addSubCommand(new CommandTournamentStop());
		this.addSubCommand(new CommandTournamentWave());
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		message(sender, "§6» §e/tournois create §f<§bnom de team§f> §8- §7Créer une nouvelle team.");
		message(sender, "§6» §e/tournois join §f<§bnom de team§f> §8- §7Rejoindre une team.");
		message(sender, "§6» §e/tournois invite §f<§bplayer§f> §8- §7Inviter un joueur.");
		message(sender, "§6» §e/tournois leave §8- §7Quitter votre team.");

		if (sender.hasPermission("admin.tournamen")) {

			message(sender, "§6» §e/tournois arena §f<§bloc1§f> §f<§bloc2§f> §8- §7Créer une arène.");
			message(sender, "§6» §e/tournois delete §f<§buuid§f> §8- §7Supprimer une arène.");
			message(sender, "§6» §e/tournois list §8- §7Voir la liste des arènes.");
			message(sender, "§6» §e/tournois lobby §8- §7Mettre la position du lobby.");
			message(sender, "§6» §e/tournois stop §8- §7Permet de stop un tournois.");
			message(sender, "§6» §e/tournois wave §8- §7Commencer la prochaine manche.");
			message(sender, "§6» §e/tournois start §f<§b1/2/3§f> §8- §7Lancer un tournois.");
		}

		return CommandType.SUCCESS;
	}

}
