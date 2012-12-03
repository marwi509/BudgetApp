package budgetapp.main;



import java.io.*;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	int currentBudget = 0;
	
	int min(int a,int b)
	{
		if(a<b)
			return a;
		return b;
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try{
        	  // Open the file that is the first 
        	  // command line parameter
        	  FileInputStream fstream = new FileInputStream("/mnt/sdcard/budget/budget.txt");
        	  // Get the object of DataInputStream
        	  DataInputStream in = new DataInputStream(fstream);
        	  BufferedReader br = new BufferedReader(new InputStreamReader(in));
        	  String strLine = br.readLine();
        	  currentBudget = Integer.parseInt(strLine);
        	  //Read File Line By Line
        	  
        	  //Close the input stream
        	  in.close();
        	  
              	TextView newBudget = (TextView)findViewById(R.id.textViewCurrentBudget);
              	EditText resultText = (EditText)findViewById(R.id.editTextSubtract);
              	resultText.requestFocus();
              	newBudget.setText(""+currentBudget);
              	if(currentBudget<0)
            		newBudget.setTextColor(Color.rgb(255,255-min(255,Math.abs(currentBudget/5)),255-min(255,Math.abs(currentBudget/5))));
            	else
            		newBudget.setTextColor(Color.rgb(255-min(255,Math.abs(currentBudget/5)),255,255-min(255,Math.abs(currentBudget/5))));
        	  }
		        catch(NumberFormatException e)
		    	{
		    		currentBudget=0;
		    	}
		        catch (Exception e){//Catch exception if any
        	    	System.err.println("Error: " + e.getMessage());
        	  }
        	  
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void subtractFromBudget(View view) {
       
    	EditText resultText = (EditText)findViewById(R.id.editTextSubtract);
    	String result = resultText.getText().toString();
    	try
    	{
        	int resultInt = Integer.parseInt(result);
        	TextView newBudget = (TextView)findViewById(R.id.textViewCurrentBudget);
        	currentBudget-=resultInt;
        	newBudget.setText(""+currentBudget);
        	//Set color
        	if(currentBudget<0)
        		newBudget.setTextColor(Color.rgb(255,255-min(255,Math.abs(currentBudget/5)),255-min(255,Math.abs(currentBudget/5))));
        	else
        		newBudget.setTextColor(Color.rgb(255-min(255,Math.abs(currentBudget/5)),255,255-min(255,Math.abs(currentBudget/5))));
    	  
        	
        		  // Create file 
        		  FileWriter fstream = new FileWriter("/mnt/sdcard/budget/budget.txt");
        		  BufferedWriter out = new BufferedWriter(fstream);
        		  out.write(""+currentBudget);
        		  //Close the output stream
        		  out.close();
        		  resultText.setText("");
    	}
    	catch(NumberFormatException e)
    	{
    		System.out.println(e);
    	}
    	catch (Exception e){//Catch exception if any
  		  System.err.println("Error: " + e.getMessage());
  		  }
    		
    }
}
