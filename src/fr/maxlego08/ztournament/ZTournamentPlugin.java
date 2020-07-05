package fr.maxlego08.ztournament;

import fr.maxlego08.ztournament.command.CommandManager;
import fr.maxlego08.ztournament.inventory.InventoryManager;
import fr.maxlego08.ztournament.listener.AdapterListener;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.scoreboard.ScoreBoardManager;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.utils.Metrics;
import fr.maxlego08.ztournament.zcore.utils.builder.CooldownBuilder;

/**
 * System to create your plugins very simply Projet:
 * https://github.com/Maxlego08/TemplatePlugin
 * 
 * @author Maxlego08
 *
 */
public class ZTournamentPlugin extends ZPlugin {

	@Override
	public void onEnable() {

		preEnable();

		commandManager = new CommandManager(this);

		if (!isEnabled())
			return;
		inventoryManager = InventoryManager.getInstance();

		scoreboardManager = new ScoreBoardManager(1000);
		
		/* Add Listener */

		addListener(new AdapterListener(this));
		addListener(inventoryManager);

		/* Add Saver */
		addSave(Config.getInstance());
		addSave(new CooldownBuilder());

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

}
