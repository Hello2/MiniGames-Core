package com.wundero.MiniGames_Core.misc_multiple;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Randomizer { //Used for chests, items and mobs
	
	public static Location getRandLoc(World w, Location loc1, Location loc2)
	{
		Location loc;
		double x,y,z;
		
		double x1,x2,y1,y2,z1,z2;
		x1 = loc1.getX();
		x2 = loc2.getX();
		y1 = loc1.getY();
		y2 = loc2.getY();
		z1 = loc1.getZ();
		z2 = loc2.getZ();
		
		double[] f = { x1, x2, y1, y2, z1, z2 };
		double[] d = genRandCoords(f);
		x = d[0];
		y = d[1];
		z = d[2];
		
		while(true)
		{
			if(!(w.getBlockAt(new Location(w,x,y,z)).getType().equals(Material.AIR))||!(w.getBlockAt(new Location(w,x,y+1.0D,z)).getType().equals(Material.AIR)))
			{
				d = genRandCoords(f);
				x = d[0];
				y = d[1];
				z = d[2];
			}
			else break;
		}
		
		
		loc = new Location(w,x,y,z);
		
		return loc;
	}
	
	public static Location getRandLocEnder(World w, Location loc1, Location loc2)
	{
		Location loc;
		double x,y,z;
		
		double x1,x2,y1,y2,z1,z2;
		x1 = loc1.getX();
		x2 = loc2.getX();
		y1 = loc1.getY();
		y2 = loc2.getY();
		z1 = loc1.getZ();
		z2 = loc2.getZ();
		
		double[] f = { x1, x2, y1, y2, z1, z2 };
		double[] d = genRandCoords(f);
		x = d[0];
		y = d[1];
		z = d[2];
		
		while(true)
		{
			if(!(w.getBlockAt(new Location(w,x,y,z)).getType().equals(Material.AIR))||!(w.getBlockAt(new Location(w,x,y+1.0,z)).getType().equals(Material.AIR))||!(w.getBlockAt(new Location(w,x,y+2.0,z)).getType().equals(Material.AIR))||((w.getBlockAt(new Location(w,x,y-1.0,z)).getType().equals(Material.AIR))))
			{
				d = genRandCoords(f);
				x = d[0];
				y = d[1];
				z = d[2];
			}
			else break;
		}
		
		
		loc = new Location(w,x,y,z);
		
		return loc;
	}
	
	public static Location getRandLocSpider(World w, Location loc1, Location loc2)
	{
		Location loc;
		double x,y,z;
		
		double x1,x2,y1,y2,z1,z2;
		x1 = loc1.getX();
		x2 = loc2.getX();
		y1 = loc1.getY();
		y2 = loc2.getY();
		z1 = loc1.getZ();
		z2 = loc2.getZ();
		
		double[] f = { x1, x2, y1, y2, z1, z2 };
		double[] d = genRandCoords(f);
		x = d[0];
		y = d[1];
		z = d[2];
		
		while(true)
		{
			if(
					!(w.getBlockAt(new Location(w,x,y,z)).getType().equals(Material.AIR))||!(
					(w.getBlockAt(new Location(w,x+1.0,y,z)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x,y,z+1.0)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x+1.0,y,z+1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x+1.0,y,z-1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x-1.0,y,z+1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x-1.0,y,z)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x,y,z-1.0)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x-1.0,y,z-1.0)).getType().equals(Material.AIR))
					)
					||
					(w.getBlockAt(new Location(w,x,y-1.0,z)).getType().equals(Material.AIR))||(
					(w.getBlockAt(new Location(w,x+1.0,y-1.0,z)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x,y-1.0,z+1.0)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x+1.0,y-1.0,z+1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x+1.0,y-1.0,z-1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x-1.0,y-1.0,z+1.0)).getType().equals(Material.AIR))
					&&
					(w.getBlockAt(new Location(w,x-1.0,y-1.0,z)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x,y-1.0,z-1.0)).getType().equals(Material.AIR))&&(w.getBlockAt(new Location(w,x-1.0,y-1.0,z-1.0)).getType().equals(Material.AIR))
					)
				)
			{
				
				d = genRandCoords(f);
				x = d[0];
				y = d[1];
				z = d[2];
			}
			else break;
		}
		
		
		loc = new Location(w,x,y,z);
		
		return loc;
	}
	
	public static int randItem(int[] itemList)
	{
		Random rnd = new Random();
		return itemList[rnd.nextInt(itemList.length)];
	}
	
	private static double[] genRandCoords(double[] in)
	{
		double[] out = new double[3];
		
		double x,y,z;
		
		double x1,x2,y1,y2,z1,z2;
		
		x1 = in[0];
		x2 = in[1];
		y1 = in[2];
		y2 = in[3];
		z1 = in[4];
		z2 = in[5];
		
		if(x1>x2||x1==x2)
		{
			x = genRandPosNegNum((int) x1,(int) x2);
		}
		else
		{
			x = genRandPosNegNum((int) x2,(int) x1);
		}
		
		if(y1>y2||y1==y2)
		{
			y = genRandPosNegNum((int) y1,(int) y2);
		}
		else
		{
			y = genRandPosNegNum((int) y2,(int) y1);
		}
		
		if(z1>z2||z1==z2)
		{
			z = genRandPosNegNum((int) z1,(int) z2);
		}
		else
		{
			z = genRandPosNegNum((int) z2,(int) z1);
		}
		
		out[0] = x;
		out[1] = y;
		out[2] = z;
		return out;
	}
	
	private static int genRandPosNegNum(int max, int min)
	{
		Random rnd = new Random();
		int ii = rnd.nextInt(((max+1)-min))+min;
		return ii;
	}
	
	public static int randomNumber(int min, int max)
	{
		Random rnd = new Random();
		int f = max-min;
		int r = (rnd.nextInt(f))+min;
		return r;
	}
	
	public static boolean isNegative(int x)
	{
		if(x>=0) return false;
		else return true;
	}
	
	public static int[] randNumList(int min, int max, int size)
	{
		Random rnd = new Random();
		int[] f = new int[size];
		for(int i = size-1; i>=0; i--)
		{
			int g = max-min;
			int r = (rnd.nextInt(g))+min;
			f[i] = r;
		}
		return f;
	}
	
	public static String randTeamName()
	{
		//TODO get config to check for list of teams
		String[] tN = { "Red", "Blue", "Cyan", "Black", "Dark Green", "Dark Red", "Purple", "Gold", "Grey", "Dark Grey", "Lavendar", "Lime", "Light Blue", "Pink", "Yellow", "White" };
		Random r = new Random();
		int index = r.nextInt(tN.length+1);
		return tN[index];
	}
	
	public static Location getRandLocItem(World w, Location loc1, Location loc2)
	{
		Location loc;
		double x,y,z;
		
		double x1,x2,y1,y2,z1,z2;
		x1 = loc1.getX();
		x2 = loc2.getX();
		y1 = loc1.getY();
		y2 = loc2.getY();
		z1 = loc1.getZ();
		z2 = loc2.getZ();
		
		double[] f = { x1, x2, y1, y2, z1, z2 };
		double[] d = genRandCoords(f);
		x = d[0];
		y = d[1];
		z = d[2];
		
		while(true)
		{
			if(!w.getBlockAt(new Location(w,x,y,z)).getType().equals(Material.AIR))
			{
				
				d = genRandCoords(f);
				x = d[0];
				y = d[1];
				z = d[2];
			}
			else break;
		}
		
		
		loc = new Location(w,x,y,z);
		
		return loc;
	}
	
}
