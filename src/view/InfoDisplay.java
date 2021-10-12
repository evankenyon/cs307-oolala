package view;

public abstract class InfoDisplay extends DisplayComponent {
  protected SetArgumentValueDisplay setHomeX;
  protected SetArgumentValueDisplay setHomeY;

  public InfoDisplay() {
    setHomeX = new SetArgumentValueDisplay("Set home x coordinate: ");
    setHomeY = new SetArgumentValueDisplay("Set home y coordinate: ");
    setHomeX.getDisplayComponentNode().setId("Set-Home-X");
    setHomeY.getDisplayComponentNode().setId("Set-Home-Y");
  }

  public int getHomeX() {
    return setHomeX.getArgument();
  }

  public int getHomeY() {
    return setHomeY.getArgument();
  }
}
