package harry.tutorialmod.gui;

import harry.tutorialmod.Reference;
import harry.tutorialmod.container.ContainerMicrowave;
import harry.tutorialmod.tileentity.TileEntityMicrowave;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMicrowave extends GuiContainer
{
	private static final ResourceLocation background = new ResourceLocation(Reference.MODID + ":textures/gui/microwave.png");
	private final InventoryPlayer playerInv;
	public TileEntityMicrowave tileMicrowave;
	
	public GuiMicrowave(InventoryPlayer playerInventory, TileEntityMicrowave furnaceInventory) 
	{
		super(new ContainerMicrowave(playerInventory, furnaceInventory));
		playerInv = playerInventory;
		tileMicrowave = furnaceInventory;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String name = tileMicrowave.getDisplayName().getUnformattedText();
		fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 117, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        if(tileMicrowave.isBurning())
        {
        	int k = this.getBurnLeftScaled(42);
        	int m = 40 - k;
        	drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - m, 10);
        }
        
        int l = this.getCookProgressScaled(24);
        drawTexturedModalRect(i + 79, j + 38, 176, 14, l + 1, 17);
    }
    
    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileMicrowave.getField(2);
        int j = this.tileMicrowave.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileMicrowave.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileMicrowave.getField(0) * pixels / i;
    }
}
