package fr.maxlego08.ztournament.command.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.TournoisType;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentStart extends VCommand {

	public CommandTournamentStart() {
		this.setPermission(Permission.ZTOURNAMENT_START);
		this.addSubCommand("start");
		this.addRequireArg("type");
		this.addRequireArg("kit");
		this.setTabCompletor();
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		String name = argAsString(1);

		if (!tournament.getKits().existKit(name)) {
			message(player, Message.TOURNAMENT_KIT_NOT_EXIST, "%name%", name);
			return CommandType.SUCCESS;
		}

		Kit kit = tournament.getKits().getKit(name);

		TournoisType type = TournoisType.valueOf("V" + argAsInteger(0));
		tournament.startTournois(sender, type, kit);

		return CommandType.SUCCESS;
	}

	@Override
	public List<String> toTab(ZTournamentPlugin plugin, CommandSender sender2, String[] args) {
		if (args.length == 2)
			return super.generateList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), args[1]);
		if (args.length == 3)
			return super.generateList(plugin.getTournament().getKits().getNames(), args[2]);
		return null;
	}
}
