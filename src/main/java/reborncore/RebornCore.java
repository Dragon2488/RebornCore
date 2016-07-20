package reborncore;

import me.modmuss50.jsonDestroyer.JsonDestroyer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import reborncore.common.IModInfo;
import reborncore.common.RebornCoreConfig;
import reborncore.common.packets.PacketHandler;
import reborncore.common.util.LogHelper;
import reborncore.common.util.OreUtil;
import reborncore.common.util.inventory.InventoryCapabilityAttacher;
import reborncore.shields.RebornCoreShields;
import reborncore.shields.json.ShieldJsonLoader;

@Mod(modid = RebornCore.MOD_ID, name = RebornCore.MOD_NAME, version = RebornCore.MOD_VERSION, acceptedMinecraftVersions = "[1.9.4]")
public class RebornCore implements IModInfo
{

	public static final String MOD_NAME = "RebornCore";
	public static final String MOD_ID = "reborncore";
	public static final String MOD_VERSION = "@MODVERSION@";
	public static final String WEB_URL = "http://files.modmuss50.me/";

	public static LogHelper logHelper;
	public static JsonDestroyer jsonDestroyer = new JsonDestroyer();
	public static RebornCoreConfig config;
	@SidedProxy(clientSide = "reborncore.ClientProxy", serverSide = "reborncore.CommonProxy")
	public static CommonProxy proxy;

	public RebornCore()
	{
		logHelper = new LogHelper(this);
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = RebornCoreConfig.initialize(event.getSuggestedConfigurationFile());
		proxy.preInit(event);
		ShieldJsonLoader.load(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		jsonDestroyer.load();
		// packets
		PacketHandler.setChannels(NetworkRegistry.INSTANCE.newChannel(MOD_ID + "_packets", new PacketHandler()));
		OreUtil.scanForOres();

		RebornCoreShields.init();
		MinecraftForge.EVENT_BUS.register(InventoryCapabilityAttacher.instace);

		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}

	public String MOD_NAME()
	{
		return MOD_NAME;
	}

	@Override
	public String MOD_ID()
	{
		return MOD_ID;
	}

	@Override
	public String MOD_VERSION()
	{
		return MOD_VERSION;
	}

	@Override
	public String MOD_DEPENDENCIES()
	{
		return "";
	}
}
