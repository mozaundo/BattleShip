public class ShipTracker {
    public static Ship[] ships = new Ship[3];
    
    public static Ship FindHit(Vector2 pos) {
        for (int i = 0; i < 3; i++) {
            //System.out.println("Checking");
            if (ships[i] == null) {
                //System.out.println("NULL");
                continue;
            }
            
            //System.out.println(ships[i].position.x + " " + ships[i].position.y);
            if (ships[i].CheckCords(pos)) {
                return ships[i];
            }
        }
        return null;
    }
    
    public static boolean CheckShips() {
        for (int i = 0; i < 3; i++) {
            if (ships[i] == null) continue;
            
            if (ships[i].destroyed == false){
                return false;
            }
        }
        return true;
    }
    
    public static boolean CheckConflicts(Vector2 cords) {
        for (int i = 0; i < 3; i++) {
            if (ships[i] == null) continue;
            
            if (ships[i].CheckConflict(cords)) {
                return true;
            }
        }
        return false;
    }
}