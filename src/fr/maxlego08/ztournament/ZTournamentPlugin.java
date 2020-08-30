package fr.maxlego08.ztournament;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bukkit.plugin.ServicePriority;

import fr.maxlego08.ztournament.api.Kits;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.command.CommandManager;
import fr.maxlego08.ztournament.command.commands.CommandTournament;
import fr.maxlego08.ztournament.inventory.InventoryManager;
import fr.maxlego08.ztournament.inventory.inventories.InventoryKitEdit;
import fr.maxlego08.ztournament.inventory.inventories.InventoryKitShow;
import fr.maxlego08.ztournament.listener.AdapterListener;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.save.Lang;
import fr.maxlego08.ztournament.scoreboard.ScoreBoardManager;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.enums.Inventory;
import fr.maxlego08.ztournament.zcore.logger.Logger;
import fr.maxlego08.ztournament.zcore.utils.Metrics;
import fr.maxlego08.ztournament.zcore.utils.UpdateChecker;

/**
 * System to create your plugins very simply Projet:
 * https://github.com/Maxlego08/TemplatePlugin
 * 
 * @author Maxlego08
 *
 */
public class ZTournamentPlugin extends ZPlugin {

	private Kits kits;
	private Tournament tournament;

	@Override
	public void onEnable() {

		preEnable();

		commandManager = new CommandManager(this);

		if (!isEnabled())
			return;
		inventoryManager = InventoryManager.getInstance();

		scoreboardManager = new ScoreBoardManager(1000);

		kits = new KitManager();
		tournament = new TournamentManager(kits);

		getServer().getServicesManager().register(Tournament.class, tournament, this, ServicePriority.High);

		registerCommand("ztournament", new CommandTournament(), "tournois", "tournament");

		registerInventory(Inventory.INVENTORY_KIT_SHOW, new InventoryKitShow());
		registerInventory(Inventory.INVENTORY_KIT_CREATE, new InventoryKitEdit());

		/* Add Listener */

		TournamentListener listener;

		addListener(new AdapterListener(this));
		addListener(inventoryManager);
		addListener(listener = new TournamentListener(tournament));

		/* Add Saver */
		addSave(Config.getInstance());
		addSave(Lang.getInstance());
		// addSave(new CooldownBuilder());
		addSave(tournament);
		addSave(kits);

		getSavers().forEach(saver -> saver.load(getPersist()));

		new Metrics(this);

		UpdateChecker checker = new UpdateChecker(this, 81959);
		AtomicBoolean atomicBoolean = new AtomicBoolean();
		checker.getVersion(version -> {
			atomicBoolean.set(this.getDescription().getVersion().equalsIgnoreCase(version));
			listener.setUseLastVersion(atomicBoolean.get());
			if (atomicBoolean.get())
				Logger.info("There is not a new update available.");
			else
				Logger.info("There is a new update available. Your version: " + this.getDescription().getVersion()
						+ ", Laste version: " + version);
		});

		postEnable();

	}

	@Override
	public void onDisable() {

		preDisable();

		tournament.onPluginDisable();

		getSavers().forEach(saver -> saver.save(getPersist()));

		postDisable();

	}

	public Tournament getTournament() {
		return tournament;
	}

}
