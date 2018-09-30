//Created by Nishant Chaudhary
//https://github.com/ChaudharyNishant

package sample;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.Scene;

public class Main extends Application
{
	int lvl = 1;
	String ans = new String("");
	String ques = new String("");
	int k = 0;
	Timeline timeline = new Timeline(), normal = new Timeline(), click = new Timeline();
	int c = 1;
	
	public void start(Stage primaryStage) throws Exception
	{
		GridPane grid = new GridPane();
		Button btn[][] = new Button[2][2];
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 2; j++)
			{
				btn[i][j] = new Button();
				btn[i][j].setPrefHeight(125);
				btn[i][j].setPrefWidth(125);
				grid.add(btn[i][j], j, i);
			}
		
		btn[0][0].setText("Remember");
		btn[0][0].setId("yellowtitle");
		btn[0][1].setText("Me");
		btn[0][1].setId("bluetitle");
		btn[1][0].setText("New Game");
		btn[1][0].setId("redtext");
		btn[1][1].setText("Quit");
		btn[1][1].setId("greentext");
		
		Ans(btn, primaryStage);
		
		Scene scene = new Scene(grid, 238, 238);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	void Ques(Button btn[][], Stage primaryStage)
	{	
		primaryStage.setTitle("Lvl " + lvl);
		btn[0][0].setId("yellow");
		btn[0][1].setId("blue");
		btn[1][0].setId("red");
		btn[1][1].setId("green");
		timeline = new Timeline(new KeyFrame( Duration.millis(1000), ae ->
		{
			Random rand = new Random();
			int i = rand.nextInt(2);
			int j = rand.nextInt(2);
			ques = ques + Integer.toString(i) + Integer.toString(j);
			
			if(i == 0 && j == 0)
				btn[i][j].setId("darkyellow");
			else if(i == 0 && j == 1)
				btn[i][j].setId("darkblue");
			else if(i == 1 && j == 0)
				btn[i][j].setId("darkred");
			else
				btn[i][j].setId("darkgreen");
			
			c++;
			
			normal.play();
			if(c > lvl)
				timeline.stop();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        normal = new Timeline(new KeyFrame( Duration.millis(500), ae ->
        {
        	btn[0][0].setId("yellow");
			btn[0][1].setId("blue");
			btn[1][0].setId("red");
			btn[1][1].setId("green");
			
			if(c > lvl)
			{
				timeline.stop();
				normal.stop();
			}
			
			else
			{
				normal.stop();
				timeline.play();
			}
        }));
        normal.setCycleCount(Animation.INDEFINITE);
	}
	
	void Ans(Button btn[][], Stage primaryStage)
	{
		btn[0][0].setOnAction(e ->
		{
			if(!(timeline.getStatus().toString().equals("RUNNING") || normal.getStatus().toString().equals("RUNNING") || click.getStatus().toString().equals("RUNNING")))
			{
				if(btn[0][0].getText().equals(""))
					Clicked(btn, 0, 0, primaryStage);
				else if(btn[0][0].getText().equals("Next Level"))
				{
					ques = "";
					ans = "";
					lvl++;
					c = 1;
					k = 0;
					for(int i = 0; i < 2; i++)
						for(int j = 0; j < 2; j++)
							btn[i][j].setText("");
					Ques(btn, primaryStage);
				}
			}
		});
		
		btn[0][1].setOnAction(e ->
		{
			if(!(timeline.getStatus().toString().equals("RUNNING") || normal.getStatus().toString().equals("RUNNING") || click.getStatus().toString().equals("RUNNING")))
			{
				if(btn[0][1].getText().equals(""))
					Clicked(btn, 0, 1, primaryStage);
				else if(btn[0][1].getText().equals("Restart Level"))
				{
					ques = "";
					ans = "";
					c = 1;
					k = 0;
					for(int i = 0; i < 2; i++)
						for(int j = 0; j < 2; j++)
							btn[i][j].setText("");
					Ques(btn, primaryStage);
				}
			}
		});
		
		btn[1][0].setOnAction(e ->
		{
			if(!(timeline.getStatus().toString().equals("RUNNING") || normal.getStatus().toString().equals("RUNNING") || click.getStatus().toString().equals("RUNNING")))
			{
				if(btn[1][0].getText().equals(""))
					Clicked(btn, 1, 0, primaryStage);
				else
				{
					ques = "";
					ans = "";
					lvl = 1;
					c = 1;
					k = 0;
					for(int i = 0; i < 2; i++)
						for(int j = 0; j < 2; j++)
							btn[i][j].setText("");
					Ques(btn, primaryStage);
				}
			}
		});
		
		btn[1][1].setOnAction(e ->
		{
			if(!(timeline.getStatus().toString().equals("RUNNING") || normal.getStatus().toString().equals("RUNNING") || click.getStatus().toString().equals("RUNNING")))
			{
				if(btn[1][1].getText().equals(""))
					Clicked(btn, 1, 1, primaryStage);
				else
					System.exit(0);
			}
		});
		
	}
	
	void Clicked(Button btn[][], int i, int j, Stage primaryStage)
	{
		ans = ans + Integer.toString(i) + Integer.toString(j);
		String temp = new String("");
		if(k < 2 * lvl)
			temp = ques.substring(0, k += 2);
		
		if(!ans.equals(temp))
			btn[i][j].setId("black");
		else if(i == 0 && j == 0)
			btn[0][0].setId("darkyellow");
		else if(i == 0 && j == 1)
			btn[0][1].setId("darkblue");
		else if(i == 1 && j == 0)
			btn[1][0].setId("darkred");
		else
			btn[1][1].setId("darkgreen");
		
		String temp1 = temp;
		
        click = new Timeline(new KeyFrame( Duration.millis(200), ae ->
        {
        	btn[0][0].setId("yellow");
			btn[0][1].setId("blue");
			btn[1][0].setId("red");
			btn[1][1].setId("green");
			
        	click.stop();
        	if(ans.equals(ques))
			{
				btn[0][0].setText("Next Level");
				btn[0][0].setId("yellowtext");
				btn[0][1].setText("Restart Level");
				btn[0][1].setId("bluetext");
				btn[1][0].setText("New Game");
				btn[1][0].setId("redtext");
				btn[1][1].setText("Quit");
				btn[1][1].setId("greentext");
			}
        	
        	if(!ans.equals(temp1))
    		{
        		btn[0][0].setText("You");
        		btn[0][0].setId("yellowtitle");
        		btn[0][1].setText("Can't");
        		btn[0][1].setId("bluetitle");
				btn[1][0].setText("New Game");
				btn[1][0].setId("redtext");
				btn[1][1].setText("Quit");
				btn[1][1].setId("greentext");
    		}
        }));
        click.setCycleCount(Animation.INDEFINITE);
        click.play();
	}
	
	public static void main(String args[])
	{
		launch(args);
	}
}

//Created by Nishant Chaudhary
//https://github.com/ChaudharyNishant
