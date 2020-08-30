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

		sender.sendMessage("§7(§bzShop§7) §aVersion du plugin§7: §2" + main.getDescription().getVersion());
		sender.sendMessage("§7(§bzShop§7) §aAuteur§7: §2Maxlego08");
		sender.sendMessage("§7(§bzShop§7) §aDiscord§7: §2http://discord.groupez.xyz/");
		sender.sendMessage("§7(§bzShop§7) §aBuy it for §d15€§7: §2https://www.spigotmc.org/resources/81959/");
		String user = "%%__USER__%%";
		sender.sendMessage("§7(§bzShop§7) §aUser account§7: §2https://www.spigotmc.org/members/" + user);

		return CommandType.SUCCESS;
	}

}
