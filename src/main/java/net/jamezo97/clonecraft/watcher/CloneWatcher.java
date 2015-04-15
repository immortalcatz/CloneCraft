package net.jamezo97.clonecraft.watcher;

import java.util.ArrayList;
import java.util.HashMap;

import net.jamezo97.clonecraft.clone.EntityClone;
import net.jamezo97.clonecraft.network.Handler2UpdateCloneData;

public class CloneWatcher {
	
	public final EntityClone clone;
	public int frequency = 1;

	private Object[] prev;
	
	public CloneWatcher(EntityClone clone, int frequency){
		this.clone = clone;
		this.frequency = frequency;
		prev = new Object[totalCount];
	}
	
	public void tick(){
		if(!clone.isEntityAlive()) return;
		if(clone.ticksExisted % frequency != 0) return;
		int index = 0;
		Object oTemp;
		Watchable wTemp;
		for(int a = 0; a < watchers.size(); a++){
			wTemp = watchers.get(a);
			for(int b = 0; b < wTemp.getSize(); b++){
				oTemp = wTemp.get(clone, b);
				if((oTemp == null && prev[index] != null) || (oTemp != null && prev[index] == null) || !oTemp.equals(prev[index])){
					//UPDATE!
					System.out.println("Update: " + oTemp + ", " + prev[index]);
//					new Handler2UpdateCloneData(clone, wTemp).sendToPlayers(wTemp.toSendUpdateTo(clone));
//					break;
				}
//				prev[index] = oTemp;
				index++;
			}
		}
	}
	
	private static int totalCount = 0;
	
	private static ArrayList<Watchable> watchers = new ArrayList<Watchable>();
	
	private static HashMap<Integer, Watchable> idToWatcher = new HashMap<Integer, Watchable>();
	
	static{
		register(new WatchTeam(0));
		register(new WatchExp(1));
	}
	
	public static void register(Watchable watcher){
		watchers.add(watcher);
		totalCount += watcher.getSize();
		idToWatcher.put(watcher.getId(), watcher);
	}
	
	public static Watchable getWatcherById(int id){
		return idToWatcher.get(id);
	}
	
}
