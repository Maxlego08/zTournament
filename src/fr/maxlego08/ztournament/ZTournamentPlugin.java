package fr.maxlego08.ztournament;

import org.bukkit.plugin.ServicePriority;

import fr.maxlego08.ztournament.api.Hider;
import fr.maxlego08.ztournament.api.Kits;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.command.CommandManager;
import fr.maxlego08.ztournament.command.commands.CommandTournament;
import fr.maxlego08.ztournament.hider.EntityListener;
import fr.maxlego08.ztournament.hider.ProtocolHider;
import fr.maxlego08.ztournament.hider.SpigotHider;
import fr.maxlego08.ztournament.inventory.InventoryManager;
import fr.maxlego08.ztournament.inventory.inventories.InventoryKitEdit;
import fr.maxlego08.ztournament.inventory.inventories.InventoryKitShow;
import fr.maxlego08.ztournament.listener.AdapterListener;
import fr.maxlego08.ztournament.placeholder.TournamentExpansion;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.save.MessageLoader;
import fr.maxlego08.ztournament.scoreboard.ScoreBoardManager;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.enums.Inventory;
import fr.maxlego08.ztournament.zcore.logger.Logger;
import fr.maxlego08.ztournament.zcore.logger.Logger.LogType;
import fr.maxlego08.ztournament.zcore.utils.Metrics;
import fr.maxlego08.ztournament.zcore.utils.plugins.Plugins;
import fr.maxlego08.ztournament.zcore.utils.plugins.VersionChecker;

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
	private TournamentListener listener;
	private MessageLoader messages;

	private Hider hider = new SpigotHider();

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

		registerInventory(Inventory.INVENTORY_KIT_SHOW, new InventoryKitShow());
		registerInventory(Inventory.INVENTORY_KIT_CREATE, new InventoryKitEdit());

		/* Add Saver */
		addSave(Config.getInstance());
		addSave(messages = new MessageLoader(this));
		// addSave(new CooldownBuilder());
		addSave(this.tournament);
		addSave(this.kits);

		getSavers().forEach(saver -> saver.load(getPersist()));

		if (Config.disablePotionAndPearl && isEnable(Plugins.PROTOCOLLIB)) {
			Logger.info("Activation of the entity hider!", LogType.SUCCESS);
			this.addListener(new EntityListener(this));
		}

		if (isEnable(Plugins.PROTOCOLLIB) && Config.enableProtocollibEntityHider) {
			this.hider = new ProtocolHider(this);
			Logger.info("Use ProtocolLib entity hider !", LogType.SUCCESS);
		}

		/* Add Listener */

		addListener(new AdapterListener(this));
		addListener(this.inventoryManager);
		addListener(this.listener = new TournamentListener(this.tournament));

		if (isEnable(Plugins.PLACEHOLDER)) {

			TournamentExpansion expansion = new TournamentExpansion(this);
			expansion.register();
			Logger.info("Successful loading of PlaceholderAPI.");

		}

		registerCommand("ztournament", new CommandTournament(this), Config.commandAliases);
		
		new Metrics(this);

		VersionChecker checker = new VersionChecker(this, 3);
		checker.useLastVersion();

		postEnable();

	}

	@Override
	public void onDisable() {

		preDisable();

		this.tournament.onPluginDisable();
		getSavers().forEach(saver -> saver.save(getPersist()));

		postDisable();

	}

	public Tournament getTournament() {
		return tournament;
	}

	public TournamentListener getListener() {
		return listener;
	}

	public Hider getHider() {
		return hider;
	}

	public MessageLoader getMessages() {
		return messages;
	}

}
