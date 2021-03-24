package EventsFunction;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import at.zyroxlp.vehiclesplus.addon.rusty.lists.Arrays;
import at.zyroxlp.vehiclesplus.addon.rusty.main.Main;
import me.legofreak107.vehiclesplus.vehicles.api.events.SendKeyEvent;
import me.legofreak107.vehiclesplus.vehicles.api.events.VehicleEnterEvent;
import me.legofreak107.vehiclesplus.vehicles.vehicles.objects.SpawnedVehicle;


public class Events implements Listener{
	
	public static HashMap<Player, SpawnedVehicle> vehicleMap;
	
	public Events() {
	vehicleMap = new HashMap<Player, SpawnedVehicle>();
	}
	
	@EventHandler
	public void entervehicle(VehicleEnterEvent event) {
		Player player = event.getDriver();
		SpawnedVehicle vehicle = event.getVehicle();
		vehicleMap.put(player, vehicle);
	}
	
	@EventHandler
	public void movevehicle(SendKeyEvent event) {
		if(event.getInput().getForward() == true) {
			Player player = event.getDriver();
			SpawnedVehicle vehicle = vehicleMap.get(player);
			if(vehicle.getBaseVehicle().getName().contains("Harvester")) {
				Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() +1, player.getLocation().getZ());
				Block block = loc.getBlock();
				if(block.getType() == Material.WHEAT) {
					Ageable ageable = (Ageable) block.getBlockData();
					if(ageable.getAge() == 7) {
						ItemStack wheat = new ItemStack(Material.WHEAT);
						vehicle.getStorageVehicle().getVehicleTrunk().addItem(wheat);
						setType(block, Material.AIR);
					}
				}
			}else if(vehicle.getBaseVehicle().getName().contains("Planter")) {
				Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
				Block block = loc.getBlock();
				if(block.getType() == Material.FARMLAND) {
					Material plan = vehicle.getStorageVehicle().getVehicleTrunk().getItem(0).getType();
					if(Arrays.trunkitems.contains(plan)) {
						ItemStack planremove = new ItemStack(vehicle.getStorageVehicle().getVehicleTrunk().getItem(0).getType(), 1);
						vehicle.getStorageVehicle().getVehicleTrunk().removeItem(planremove);
						Location loc1 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() +1, player.getLocation().getZ());
						Block block1 = loc1.getBlock();
						switch(plan) {
							case WHEAT:
								setType(block1, Material.WHEAT_SEEDS);
								break;
							case BEETROOT:
								setType(block1, Material.BEETROOTS);
								break;
							case POTATO:
								setType(block1, Material.POTATOES);
								break;
							case CARROT:
								setType(block1, Material.CARROTS);
								break;
							default:
								break;
						}
					}
				}
			}
		}
	}
	
	public static void setType(final Block b, final Material m){
	    new BukkitRunnable() {
	        public void run() {
	            b.setType(m);
	        }
	    }.runTask(Main.getPlugin());
	}

}
