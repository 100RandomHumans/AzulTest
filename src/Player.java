import java.util.*;
public class Player {
    String name; //gives the player a name. This may not every be referenced, but its useful to have one static variable
    PArea a; //gives the player a play area
    Wall w; //gives the player a wall
    FloorLine fl; //gives the player a FloorLine
    int points; //number of points a player has
    boolean canPlay, canDraw; //whether or not a player can draw tiles or is playing tiles
    ArrayList<Tile> holder; //where the tiles a player just picked up are stored

    public Player(String n) //constructor
    {
        name =n;
        a = new PArea();
        w = new Wall();
        fl = new FloorLine();
        holder = new ArrayList<Tile>();
    }
    public int getPoints() //returns the number of points a player has
    {
        return points;
    }

    public PArea getArea() //returns the players play area
    {
        return a;
    }

    public Wall getWall() //returns the players given wall area
    {
        return w;
    }

    public void addPoints(int x) //adds the given amount of points to the players score
    {
        points+=x; //x can be negative cus floorline
    }

    public FloorLine getFloorLine() //returns the players floor line
    {
        return fl;
    }

    public void addToHolder(Tile t) //adds the given tile to the players holder
    {
        holder.add(t);
    }

    //this below returns an ArrayList of all the valid placements for the players held tiles (including the floor line)
    public ArrayList<Integer> checkValidPlacements()
    {
        ArrayList<Integer> validRows = new ArrayList<Integer>();

        for(int i = 0; i < 4; i++)
        {
            if((a.getRowSpace(i).get(0) == holder.get(0).getColor() || a.getRowSpace(i).get(0) == 5) && a.getRowSpace(i).get(1) > 0)
                validRows.add(i);
        }

        validRows.add(5);
        return validRows;
    }

    /*
    This below sets the PArea with all of the tiles in the players holder. the integer "row" that is passed in
    is the index for a valid row where a player can place tiles. the method will then place tiles in the players
    PArea until the row fills or the holder runs out. If the row fills, the remaining tiles are placed into the
    FloorLine.
     */
    public void setPArea(int row, Trash t)
    {
        if(row != 5)
            for(int i = 0; i < holder.size(); i++)
            {
                if(a.getRowSpace(row).get(1) > 0)
                    a.setArea(row, holder.get(0).getColor(), a.getRowSpace(row).get(1));
                else
                    fl.add(holder.get(0), t);

                holder.remove(0);
            }
        else
        {
            for(int i = 0; i < holder.size(); i++)
                fl.add(holder.remove(0), t);
        }
    }

    public void changeCanDraw(boolean x) {canDraw = x;}
    public void changeCanPlay(boolean x) {canPlay = x;}
    //these two above and the two below are self explanatory.
    public boolean playerCanDraw(){return canDraw;}
    public boolean playerCanPlay(){return canPlay;}
}