package fr.maxlego08.ztournament.command.commands.kits;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentKits extends VCommand {

	public CommandTournamentKits(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_KIT_USE);
		this.addSubCommand("kits");
		this.addSubCommand("kit");
		this.addSubCommand(new CommandTournamentKitsList(plugin));
		this.addSubCommand(new CommandTournamentKitsShow(plugin));
		this.addSubCommand(new CommandTournamentKitsCreate(plugin));
		this.addSubCommand(new CommandTournamentKitsEdit(plugin));
		this.addSubCommand(new CommandTournamentKitsDelete(plugin));
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		messageWO(sender, Message.TOURNAMENT_HELP_HEADER);
		Message.TOURNAMENT_HELP_ADMIN_KIT.getMessages().forEach(m -> messageWO(sender, m));

		return CommandType.SUCCESS;
	}

}
