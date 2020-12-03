package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentVersion extends VCommand {

	public CommandTournamentVersion() {
		this.addSubCommand("version");
		this.addSubCommand("v");
		this.addSubCommand("ver");
		this.addSubCommand("?");
		this.addSubCommand("aide");
		this.addSubCommand("help");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		message(sender, "§aVersion du plugin§7: §2" + main.getDescription().getVersion());
		message(sender, "§aAuteur§7: §2Maxlego08");
		message(sender, "§aDiscord§7: §2http://discord.groupez.xyz/");
		message(sender, "§aBuy it for §d15€§7: §2https://www.spigotmc.org/resources/81959/");
		String user = "%%__USER__%%";
		message(sender, "§aUser account§7: §2https://www.spigotmc.org/members/" + user);

		return CommandType.SUCCESS;
	}

}
