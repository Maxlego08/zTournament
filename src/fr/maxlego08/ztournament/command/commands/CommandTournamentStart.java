package fr.maxlego08.ztournament.command.commands;

import java.util.Arrays;
import java.util.Optional;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.TournoisType;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentStart extends VCommand {

	public CommandTournamentStart(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_START);
		this.addSubCommand("start");
		this.addRequireArg("type", (a, b) -> Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
		this.addRequireArg("kit", (a, b) -> plugin.getTournament().getKits().getNames());
		this.setTabCompletor();
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		String name = argAsString(1);

		Optional<Kit> optional = this.tournament.getKits().getKit(name);

		if (!optional.isPresent()) {
			message(player, Message.TOURNAMENT_KIT_NOT_EXIST, "%name%", name);
			return CommandType.SUCCESS;
		}

		Kit kit = optional.get();

		TournoisType type = TournoisType.valueOf("V" + argAsInteger(0));
		this.tournament.startTournois(sender, type, kit);

		return CommandType.SUCCESS;
	}
}
