
package fr.maxlego08.ztournament.command.commands;

import org.bukkit.inventory.ItemStack;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.command.VCommand;
import fr.maxlego08.ztournament.zcore.enums.Permission;
import fr.maxlego08.ztournament.zcore.utils.commands.CommandType;

public class CommandTournamentAxe extends VCommand {

	public CommandTournamentAxe(ZTournamentPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZTOURNAMENT_AXE);
		this.addSubCommand("axe");
		this.setConsoleCanUse(false);
	}

	@Override
	protected CommandType perform(ZTournamentPlugin main) {

		ItemStack itemStack = main.getListener().getAxe();
		give(player, itemStack);
		message(sender, "§7You have just received the selection axe.");

		return CommandType.SUCCESS;
	}

}
