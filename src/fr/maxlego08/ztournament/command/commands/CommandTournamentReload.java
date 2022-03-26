package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentReload extends VCommand {

	public CommandTournamentReload() {
		this.setPermission(Permission.ZTOURNAMENT_RELOAD);
		this.addSubCommand("reload", "rl");
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		long ms = System.currentTimeMillis();

		Config.getInstance().load(main.getPersist());
		main.getMessages().load(main.getPersist());

		long end = Math.abs(System.currentTimeMillis() - ms);

		message(sender, "§eReload in §6%s% §ems", "%s%", format(end));

		return CommandType.SUCCESS;
	}

}
