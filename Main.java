import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.*;  
import java.io.IOException;
import java.util.Timer;

enum CellStates {
    Hidden,
    Empty,
    Hit,
    Destroyed
}

public class Main
{
    static ArrayList<Float> results = new ArrayList<Float>();
    
    public static final String purple = "\u001B[35m";
    public static final String blue = "\u001B[34m";

    static Scanner input;
    
    static int choice;
    
	public static void main(String[] args) {
        input = new Scanner(System.in);
    
        MainMenu();
	}
	
	static public void ResetColor() {
	    System.out.print("\u001B[0m");
	}
	
	static public void ClearConsole() {
	    System.out.print("\033[H\033[2J");
	}
	
	static public void MainMenu() {
	    input = new Scanner(System.in);
	    while (true) {
            System.out.println(purple + "Выберите действие: ");
    		ResetColor();
    		System.out.println("1.Новая игра \n2.Результаты \n3.Выход");
    		System.out.print(blue + "Ваш выбор: ");
    		ResetColor();
    		choice = input.nextInt();
    		if (choice >= 1 && choice <= 3) {
    		    break;
    		}
    		else {
                ClearConsole();
    		    System.out.println("Неизвестный выбор");
    		}
        }
        
        switch (choice) {
            case 1:
                StartGame();
                break;
            case 2:
                Results();
                break;
            case 3:
                break;
        }
	}
	
	static public void Results() {
	    ClearConsole();
	    Collections.sort(results);
	    System.out.println(purple + "Топ: ");
	    ResetColor();
	    
	    int amount = 3;
	    if (results.size() < amount) {
	        amount = results.size();
	    }
	    for (int i = 0; i < amount; i++) {
	        System.out.print(results.get(i));
	        System.out.println(" сек.");
	    }
	    System.console().readLine();
        ClearConsole();
        MainMenu();
	}
	
	static public void StartGame() {
	    Instant starts = Instant.now();
	    Map.GenerateMap();
	    Map.HideFrontMap();
	    
	    while (true) {
	        Timer timer = new Timer();
	        TimerTask task = new TimerTask() {
                public void run() {
                    TimeOut(timer);
                }
            };
	        timer.schedule(task, 15000);
	        
    	    Map.DrawMap();
    	    System.out.print(blue + "Куда стреляем: ");
        	ResetColor();
        	String cords = input.next();
        	timer.cancel();
        	System.out.println("");
        	if (!Utility.CheckCords(cords)) {
        	    ClearConsole();
        	    System.out.println("Неверные координаты, введите снова");
        	    continue;
        	}
        	ClearConsole();
        	
        	int result = Map.HitCords(cords);
        	
        	if (result == 1) {
        	    System.out.println("Попадание!");
        	}
        	else if (result == 0) {
        	    System.out.println("Промах!");
        	    continue;
        	}
        	else if (result == -1) {
        	    System.out.println("Клетка уже раскрыта.");
        	    continue;
        	}
        	
        	if (ShipTracker.CheckShips()) {
        	    Map.DrawMap();
        	    Instant ends = Instant.now();
        	    String newTime = Utility.ConvertTime(Duration.between(starts, ends) + "");
        	    results.add(Float.parseFloat(newTime));
        	    System.out.println("ВЫ ПОБЕДИЛИ!!!!");
        	    System.out.println("Время " + newTime + " сек.");
        	    break;
        	}
	    }
    	System.console().readLine();
        ClearConsole();
        MainMenu();
	}
	
	static void TimeOut(Timer timer) {
	    timer.cancel();
	    timer.purge();
	    System.out.println("Время истекло!");
	    System.exit(0);
	}
}
