package designmode;



public class simpleFactoryPattern {
    public static void main(String[] args) {
        Chart chart;
		chart = ChartFactory.getChart("PieChart");
		chart.display();
    }
}

interface Chart {
    public void display();
} 

class PieChart implements Chart
{

	public PieChart()
	{
		System.out.println("创建饼状图");
	}
	@Override
	public void display() {
		System.out.println("显示饼状图");
	}
	
}

class LineChart implements Chart
{

	public LineChart()
	{
		System.out.println("创建线形图");
	}
	@Override
	public void display() {
		System.out.println("显示线形图");
	}
	
}

class ChartFactory
{
	public static Chart getChart(String type)
	{
		Chart chart = null;
		if (type.equalsIgnoreCase("PieChart"))
		{
			chart = new PieChart();
			System.out.println("初始化饼状图");
		} else if (type.equalsIgnoreCase("LineChart"))
		{
			chart = new LineChart();
			System.out.println("初始化线形图");
		}
		return chart;
	}
}
