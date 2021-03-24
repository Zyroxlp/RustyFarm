package at.zyroxlp.vehiclesplus.addon.rusty.lists;

import java.util.ArrayList;

import org.bukkit.Material;

public class Arrays {
	
	public static ArrayList<Material> harvestitems;
	public static ArrayList<Material> trunkitems;
	
	public Arrays() {
		trunkitems = new ArrayList<Material>();
		Material wheatseed = Material.WHEAT_SEEDS;
		Material beetsseed = Material.BEETROOT_SEEDS;
		Material carrotitem = Material.CARROT;
		Material potatoitem = Material.POTATO;
		trunkitems.add(potatoitem);
		trunkitems.add(carrotitem);
		trunkitems.add(beetsseed);
		trunkitems.add(wheatseed);
				
	}

}
