package net.isaacj.tfw.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.isaacj.tfw.block.custom.NetheriteBlasterBlock;
import net.isaacj.tfw.item.inventory.ImplementedInventory;
import net.isaacj.tfw.recipe.NetheriteBlasterRecipes;
import net.isaacj.tfw.screen.NetheriteBlasterScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NetheriteBlasterEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int fuelTime = 0;
    private int maxFuelTime = 0;


    public NetheriteBlasterEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.NETHERITE_BLASTER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return NetheriteBlasterEntity.this.progress;
                    case 1:
                        return NetheriteBlasterEntity.this.maxProgress;
                    case 2:
                        return NetheriteBlasterEntity.this.fuelTime;
                    case 3:
                        return NetheriteBlasterEntity.this.maxFuelTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        NetheriteBlasterEntity.this.progress = value;
                        break;
                    case 1:
                        NetheriteBlasterEntity.this.maxProgress = value;
                        break;
                    case 2:
                        NetheriteBlasterEntity.this.fuelTime = value;
                        break;
                    case 3:
                        NetheriteBlasterEntity.this.maxFuelTime = value;
                        break;

                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Netherite Blaster");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new NetheriteBlasterScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("blaster.progress", progress);
        nbt.putInt("blaster.fuelTime", fuelTime);
        nbt.putInt("blaster.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("blaster.progress");
        fuelTime = nbt.getInt("blaster.fuelTime");
        maxFuelTime = nbt.getInt("blaster.maxFuelTime");
    }

    private void consumeFuel() {
        if (!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, NetheriteBlasterEntity entity) {

        if (isConsumingFuel(entity)) {
            entity.fuelTime--;
        }


        if (hasRecipe(entity)) {
            if (hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if (isConsumingFuel(entity)) {
                entity.progress++;
                if (entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
            else {
                entity.progress--;
            }
        } else {
            entity.resetProgress();
        }
    }


    private static boolean hasFuelInFuelSlot(NetheriteBlasterEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(NetheriteBlasterEntity entity) {
        return entity.fuelTime > 0;
    }


    private static boolean hasRecipe(NetheriteBlasterEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<NetheriteBlasterRecipes> match = world.getRecipeManager()
                .getFirstMatch(NetheriteBlasterRecipes.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(NetheriteBlasterEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<NetheriteBlasterRecipes> match = world.getRecipeManager()
                .getFirstMatch(NetheriteBlasterRecipes.Type.INSTANCE, inventory, world);

        if (match.isPresent()) {
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 2));

            entity.resetProgress();
        }
    }


    private void resetProgress() {
        this.progress = 0;
    }


    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        ItemStack outputSlot = inventory.getStack(3);
        return outputSlot.isEmpty() || (outputSlot.getItem() == output.getItem());
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        ItemStack outputSlot = inventory.getStack(3);
        return outputSlot.isEmpty() || (outputSlot.getCount() + 2 <= outputSlot.getMaxCount());
    }

}