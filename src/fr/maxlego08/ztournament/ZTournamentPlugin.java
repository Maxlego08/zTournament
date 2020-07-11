package fr.maxlego08.ztournament;

import org.bukkit.plugin.ServicePriority;

import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.command.CommandManager;
import fr.maxlego08.ztournament.command.commands.CommandTournament;
import fr.maxlego08.ztournament.inventory.InventoryManager;
import fr.maxlego08.ztournament.listener.AdapterListener;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.save.Lang;
import fr.maxlego08.ztournament.scoreboard.ScoreBoardManager;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.utils.Metrics;

/**
 * System to create your plugins very simply Projet:
 * https://github.com/Maxlego08/TemplatePlugin
 * 
 * @author Maxlego08
 *
 */
public class ZTournamentPlugin extends ZPlugin {

	private final Tournament tournament = new TournamentManager();

	@Override
	public void onEnable() {

		preEnable();

		commandManager = new CommandManager(this);

		if (!isEnabled())
			return;
		inventoryManager = InventoryManager.getInstance();

		scoreboardManager = new ScoreBoardManager(1000);

		getServer().getServicesManager().register(Tournament.class, tournament, this, ServicePriority.High);

		
		registerCommand("tournament", new CommandTournament(), "tournois");
		
		/* Add Listener */

		addListener(new AdapterListener(this));
		addListener(inventoryManager);
		addListener(new TournamentListener(tournament));

		/* Add Saver */
		addSave(Config.getInstance());
		addSave(Lang.getInstance());
//		addSave(new CooldownBuilder());
		addSave(tournament);

		getSavers().forEach(saver -> saver.load(getPersist()));

		new Metrics(this);

		postEnable();

	}

	@Override
	public void onDisable() {

		preDisable();

		getSavers().forEach(saver -> saver.save(getPersist()));

		postDisable();

	}

	public Tournament getTournament() {
		return tournament;
	}

}
