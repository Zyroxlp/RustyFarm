package at.zyroxlp.vehiclesplus.addon.rusty.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import EventsFunction.Events;
import at.zyroxlp.vehiclesplus.addon.rusty.lists.Arrays;


public class Main extends JavaPlugin {	

	public static Main getPlugin() {
		return main;
	}
	
	public static Main main;
	public Arrays arrays;
	
	@Override
	public void onEnable() {
		main = this;
		arrays = new Arrays();
		//Commands//
		//Events//
		PluginManager pluginManger = Bukkit.getPluginManager();
		pluginManger.registerEvents(new Events(), main);
	}
}
