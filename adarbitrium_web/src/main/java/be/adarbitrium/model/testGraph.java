package be.adarbitrium.model;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartUtils;

/**
 * A simple introduction to using JFreeChart. This demo is described in the
 * JFreeChart Developer Guide.
 */
public class testGraph {
    
	JFreeChart chart;
	
    public testGraph() {
        // create a dataset...
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.2);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);
        // create a chart...
        chart = ChartFactory.createPieChart(
            "Sample Pie Chart",
            data,
            true, // legend?
            true, // tooltips?
            false // URLs?
        );
    }
    
    public void writeFile(String filePath) {
    	File file = new File(filePath);
    	try {
			ChartUtils.saveChartAsPNG(file, chart, 400, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}