import java.util.ArrayList;
import java.util.Collection;

public class Registry
{
		public static Collection<TimerPanel> register = new ArrayList<TimerPanel>();
		public static Collection<TimerModel> models = new ArrayList<TimerModel>();
		
		public static void add(TimerPanel obj)
		{
			register.add(obj);
		}
		
		public static void addModel(TimerModel obj)
		{
			models.add(obj);
		}
		
		public static void clearAll()
		{
			for(TimerPanel obj : register)
			{
				obj.clear();
			}
		}
		
		public static void clearModels()
		{
			for(TimerModel obj : models)
			{
				obj.clearAll();
			}
		}
		
		public static void startModels()
		{
			for(TimerModel obj : models)
			{
				obj.start();
			}
		}
		
		public static void stopModels()
		{
			for(TimerModel obj : models)
			{
				obj.stop();
			}
		}
}