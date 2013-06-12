package mcp.mobius.waila;

import java.util.logging.Level;

import codechicken.nei.api.API;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;

import mcp.mobius.waila.addons.betterbarrels.HUDHandlerBetterBarrels;
import mcp.mobius.waila.addons.buildcraft.HUDHandlerBCTanks;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2Generator;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2Machine;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2Storage;
import mcp.mobius.waila.handlers.NEIHUDHandler;
import mcp.mobius.waila.handlers.WailaTooltipHandler;

public class ProxyClient extends ProxyServer {

	public ProxyClient() {}
	
	
	@Override
	public void registerHandlers(){
		GuiContainerManager.addTooltipHandler(new WailaTooltipHandler());
		//GuiContainerManager.addInputHandler(new EnchantementHandler());
		API.registerHighlightHandler(new NEIHUDHandler(), ItemInfo.Layout.FOOTER);
		//API.addKeyBind("showenchant", "Display enchantements", Keyboard.KEY_RSHIFT);
		this.registerMods();		
	}	

	public void registerMods(){
		/* BETTER BARRELS */
		try {
			Class ModBetterBarrels = Class.forName("mcp.mobius.betterbarrels.mod_BetterBarrels");
			mod_Waila.log.log(Level.INFO, "BetterBarrel mod found.");
			HUDHandlerBetterBarrels.register();
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "BetterBarrel mod not found. Skipping.");			
		}
		
		/* BUILDCRAFT */
		try {
			Class ModBuildcraftFactory = Class.forName("buildcraft.BuildCraftFactory");
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod found.");
			HUDHandlerBCTanks.register();
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod not found. Skipping.");			
		}		
		
		/* INDUSTRIALCRAFT2 */
		try {
			Class ModIndustrialCraft = Class.forName("ic2.core.IC2");
			mod_Waila.log.log(Level.INFO, "Industrialcraft2 mod found.");
			HUDHandlerIC2Storage.register();
			HUDHandlerIC2Machine.register();
			HUDHandlerIC2Generator.register();
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "Industrialcraft2 mod not found. Skipping.");			
		}		
		
	}	
	
}