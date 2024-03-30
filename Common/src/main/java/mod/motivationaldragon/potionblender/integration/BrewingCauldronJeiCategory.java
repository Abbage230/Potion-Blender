package mod.motivationaldragon.potionblender.integration;

import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mod.motivationaldragon.potionblender.Constants;
import mod.motivationaldragon.potionblender.block.PotionBlenderBlock;
import mod.motivationaldragon.potionblender.recipes.BrewingCauldronRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class BrewingCauldronJeiCategory implements IRecipeCategory<BrewingCauldronRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(Constants.MOD_ID, "brewing_cauldron");
	public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/jei/brewing_cauldron_gui.png");

	public static final RecipeType<BrewingCauldronRecipe> brewing_cauldron_recipe_type = new RecipeType<>(UID, BrewingCauldronRecipe.class);

	private final IDrawable background;
	private final IDrawable icon;

	public BrewingCauldronJeiCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(BACKGROUND_TEXTURE,0,0,176,85);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PotionBlenderBlock.BREWING_CAULDRON_ITEM));
	}

	/**
	 * @return the type of recipe that this category handles.
	 * @since 9.5.0
	 */
	@Override
	public RecipeType<BrewingCauldronRecipe> getRecipeType() {
		return brewing_cauldron_recipe_type;
	}

	/**
	 * Returns a text component representing the name of this recipe type.
	 * Drawn at the top of the recipe GUI pages for this category.
	 *
	 * @since 7.6.4
	 */
	@Override
	public Component getTitle() {
		return Component.translatable("block.potionblender.brewing_cauldron");
	}

	/**
	 * Returns the drawable background for a single recipe in this category.
	 */
	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	/**
	 * Icon for the category tab.
	 * You can use {@link IGuiHelper#createDrawableIngredient(IIngredientType, Object)}
	 * to create a drawable from an ingredient.
	 *
	 * @return icon to draw on the category tab, max size is 16x16 pixels.
	 */
	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	/**
	 * Draw extras or additional info about the recipe.
	 * Use the mouse position for things like button highlights.
	 * Tooltips are handled by {@link #getTooltipStrings(Object, IRecipeSlotsView, double, double)}
	 *
	 * @param recipe          the current recipe being drawn.
	 * @param recipeSlotsView a view of the current recipe slots being drawn.
	 * @param guiGraphics     the current {@link GuiGraphics} for rendering.
	 * @param mouseX          the X position of the mouse, relative to the recipe.
	 * @param mouseY          the Y position of the mouse, relative to the recipe.
	 * @see IDrawable for a simple class for drawing things.
	 * @see IGuiHelper for useful functions.
	 * @see IRecipeSlotsView for information about the ingredients that are currently being drawn.
	 * @since 9.3.0
	 */
	@Override
	public void draw(BrewingCauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
		RenderSystem.enableBlend();
		RenderSystem.disableBlend();
	}

	/**
	 * Sets all the recipe's ingredients by filling out an instance of {@link IRecipeLayoutBuilder}.
	 * This is used by JEI for lookups, to figure out what ingredients are inputs and outputs for a recipe.
	 *
	 * @param builder
	 * @param recipe
	 * @param focuses
	 * @since 9.4.0
	 */
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BrewingCauldronRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(3));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(4));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(5));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(6));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(7));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 65).addItemStack(recipe.getOutput());
	}
}