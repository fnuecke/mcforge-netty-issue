package bugs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = NettyBug.MODID, version = NettyBug.VERSION)
public class NettyBug {
    public static final String MODID = "nettybug";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    private NettyBug instance;

    @EventHandler
    public void preInit(FMLInitializationEvent event) {
        GameRegistry.registerItem(new ItemTest().setUnlocalizedName("test").setCreativeTab(CreativeTabs.tabAllSearch), "test");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        EntityRegistry.registerModEntity(EntityTest.class, "nettybug:test", 1, instance, 16, 3, false);
    }

    private static class ItemTest extends Item {
        @Override
        public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
            if (!world.isRemote) {
                final EntityTest entity = new EntityTest(world);
                entity.setPosition(player.posX, player.posY, player.posZ);
                world.spawnEntityInWorld(entity);
            }
            return stack;
        }
    }

    public static class EntityTest extends EntityMinecartEmpty {
        public EntityTest(final World world) {
            super(world);
        }
    }
}
