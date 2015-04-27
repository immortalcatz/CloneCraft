package net.jamezo97.clonecraft.command.parameter;

import java.util.ArrayList;

public class Parameters {

	private static ArrayList<Parameter> paramTypes = new ArrayList<Parameter>();
	
	public static Parameter getParamByClass(Class<? extends Parameter> paramClass){
		for(int a = 0; a < paramTypes.size(); a++)
		{
			if(paramTypes.get(a).getClass() == paramClass)
			{
				return paramTypes.get(a);
			}
		}
		return null;
	}
	
	public static Parameter registerParam(Parameter param){
		paramTypes.add(param);
		return param;
	}
	
	public static final Parameter p_entityType = new ParamEntityType();
	public static final Parameter p_player = new ParamPlayer();
	public static final Parameter p_integer = new ParamInteger();
	
	static
	{
		
	}
	
	
}
