package erebus.lib;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.block.BlockLogErebus;
import erebus.block.BlockSaplingErebus;
import erebus.block.BlockStairPlanks;
import erebus.item.block.ItemBlockLocalised;

public enum EnumWood {

	//@formatter:off
	Acacia,
	Eucalyptus,
	Mahogany,
	Baobab,
	Mossbark,
	Pink,
	Scorched,
	Asper,
	Cypress,
	Sap,
	Weedwood,
	White(false),
	Bamboo(false);
	//@formatter:on

	private final boolean hasLog;

	EnumWood(boolean hasLog) {
		this.hasLog = hasLog;
	}

	EnumWood() {
		this(true);
	}

	public String getTranslatedName() {
		return StatCollector.translateToLocal("wood." + Reference.MOD_ID + "." + name().toLowerCase());
	}

	private static ArrayList<Block> logs;
	private static ArrayList<Block> stairs;
	private static ArrayList<Block> saplings;

	public static void initBlocks() {
		logs = new ArrayList<Block>();
		stairs = new ArrayList<Block>();
		saplings = new ArrayList<Block>();

		for (EnumWood wood : values()) {
			if (wood.hasLog) {
				Block log = new BlockLogErebus(wood);
				GameRegistry.registerBlock(log, ItemBlockLocalised.class, "log" + wood.name());
				Blocks.fire.setFireInfo(log, 5, 5);
				logs.add(log);

				Block sapling = new BlockSaplingErebus(wood);
				GameRegistry.registerBlock(sapling, ItemBlockLocalised.class, "sapling" + wood.name());
				saplings.add(sapling);
			}

			Block stair = new BlockStairPlanks(ModBlocks.planksErebus, wood);
			GameRegistry.registerBlock(stair, ItemBlockLocalised.class, "plankStair" + wood.name());
			Blocks.fire.setFireInfo(stair, 5, 5);
			stairs.add(stair);
		}
	}

	public static void initRecipes() {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].hasLog) {
				Block log = logs.get(i);
				OreDictionary.registerOre("logWood", log);
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, i), new ItemStack(log));
				GameRegistry.addSmelting(new ItemStack(Items.coal, 1, 1), new ItemStack(log), 1.0F);

				OreDictionary.registerOre("treeSapling", saplings.get(i));
			}

			Block stair = stairs.get(i);
			OreDictionary.registerOre("stairWood", stair);
			GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] { "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.planksErebus, 1, i) });
		}
	}
}