public class Map {
    static boolean[][] backMap;
    static CellStates[][] frontMap = new CellStates[8][8];
    
    public static void GenerateMap() {
        backMap = new boolean[8][8];
        
        //Single Ships
        for (int i = 0; i < 1;) {
            Vector2 randPos = new Vector2(0, 0);
            randPos.RandomVector();
            if (!ShipTracker.CheckConflicts(randPos)) {
                //System.out.println(randPos.x + " " + randPos.y);
                backMap[randPos.x][randPos.y] = true;
                ShipTracker.ships[i] = new Ship(randPos);
                i++;
            }
            else {
                //System.out.println("Reroll");
            }
        }
        
        //Double ships
        for (int i = 1; i < 3;) {
            Vector2 randPos = new Vector2(0, 0);
            randPos.RandomVector();
            if (!ShipTracker.CheckConflicts(randPos)) {
                int yPos = randPos.x + 1;
                if (yPos > 7)
                    yPos = randPos.x - 1;
                Vector2 secondPos = new Vector2(yPos, randPos.y);
                
                if (!ShipTracker.CheckConflicts(secondPos)) {
                    //System.out.println(randPos.x + " " + randPos.y);
                    Map.backMap[randPos.x][randPos.y] = true;
                    Map.backMap[secondPos.x][secondPos.y] = true;
                    ShipTracker.ships[i] = new DoubleShip(randPos, secondPos);
                    i++;
                }
            }
            else {
                //System.out.println("Reroll");
            }
        }
    } 
    
    public static void HideFrontMap() {
        for (int i = 0; i < 8; i++) {
            for (int o = 0; o < 8; o++) {
                frontMap[i][o] = CellStates.Hidden;
            }
        }
    }
    
    public static void DrawMap() {
        System.out.println("  A B C D E F G H");
        
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            for (int o = 0; o < 8; o++) {
                System.out.print(" " + DrawCell(frontMap[i][o]));
            }
            System.out.print("\n");
        }
    }
    
    private static String DrawCell(CellStates cell) {
        switch (cell) {
            case Hidden:
                return "_";
            case Empty:
                return "o";
            case Hit:
                return "U";
            case Destroyed:
                return "X";
        }
        return "";
    }
    
    public static int HitCords(String cords) {
        char chr = cords.charAt(0);
        int x = Character.getNumericValue(cords.charAt(1));
        int y = Character.toLowerCase(chr) - 'a' + 1;
        x--; y--;
        
        if (frontMap[x][y] != CellStates.Hidden) {
            return -1;
        }
        
        if (backMap[x][y]) {
            Ship hitShip = ShipTracker.FindHit(new Vector2(x, y));
            
            if (hitShip.destroyed) {
                frontMap[x][y] = CellStates.Destroyed;
            }
            else {
                frontMap[x][y] = CellStates.Hit;
            }
            return 1;
        }
        else {
            frontMap[x][y] = CellStates.Empty;
            return 0;
        }
    }
    
    public static void ShowNear(Vector2 cords) {
        for (int i = 1; i > -2; i--) {
            for (int n = -1; n < 2; n++) {
                if (cords.x + i > 7 || cords.x + i < 0)
                    continue;
                else if (cords.y + n > 7 || cords.y + n < 0)
                    continue;
                
                if (frontMap[cords.x + i][cords.y + n] == CellStates.Hidden) {
                    frontMap[cords.x + i][cords.y + n] = CellStates.Empty;
                } 
            }
        }
    }
}