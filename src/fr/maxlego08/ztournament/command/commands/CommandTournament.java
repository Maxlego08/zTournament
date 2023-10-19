package fr.maxlego08.ztournament.command.commands;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.command.commands.kits.CommandTournamentKits;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournament extends VCommand {

	public CommandTournament(ZTournamentPlugin plugin) {
		super(plugin);
		this.addSubCommand(new CommandTournamentArena(plugin));
		this.addSubCommand(new CommandTournamentArenaList(plugin));
		this.addSubCommand(new CommandTournamentArenaDelete(plugin));
		this.addSubCommand(new CommandTournamentStart(plugin));
		this.addSubCommand(new CommandTournamentTeamCreate(plugin));
		this.addSubCommand(new CommandTournamentTeamLeave(plugin));
		this.addSubCommand(new CommandTournamentTeamJoin(plugin));
		this.addSubCommand(new CommandTournamentSetLobby(plugin));
		this.addSubCommand(new CommandTournamentTeamInvite(plugin));
		this.addSubCommand(new CommandTournamentStop(plugin));
		this.addSubCommand(new CommandTournamentWave(plugin));
		this.addSubCommand(new CommandTournamentVersion(plugin));
		this.addSubCommand(new CommandTournamentReload(plugin));
		this.addSubCommand(new CommandTournamentKits(plugin));
		this.addSubCommand(new CommandTournamentAxe(plugin));
		this.addSubCommand(new CommandTournamentPos1(plugin));
		this.addSubCommand(new CommandTournamentPos2(plugin));
		this.addSubCommand(new CommandTournamentKick(plugin));	
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		messageWO(this.sender, Message.TOURNAMENT_HELP_HEADER);
		Message.TOURNAMENT_HELP.getMessages().forEach(m -> messageWO(this.sender, m));
		if (this.sender.hasPermission(Permission.ZTOURNAMENT_HELP.getPermission())) {
			Message.TOURNAMENT_HELP_ADMIN.getMessages().forEach(m -> messageWO(this.sender, m));
		}
		
		return CommandType.SUCCESS;
	}

}
