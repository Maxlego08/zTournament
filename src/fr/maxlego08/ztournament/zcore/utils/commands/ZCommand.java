package fr.maxlego08.ztournament.zcore.utils.commands;

import java.util.function.BiConsumer;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;

public class ZCommand extends VCommand {

	private BiConsumer<VCommand, ZTournamentPlugin> command;

	@Override
	public CommandType perform(ZTournamentPlugin main) {
		
		if (command != null){
			command.accept(this, main);
		}

		return CommandType.SUCCESS;
	}

	public VCommand setCommand(BiConsumer<VCommand, ZTournamentPlugin> command) {
		this.command = command;
		return this;
	}

	public VCommand sendHelp(String command) {
		this.command = (cmd, main) -> main.getCommandManager().sendHelp(command, cmd.getSender());
		return this;
	}

}
