public class Ship {
    public Vector2 position;
    public boolean destroyed;
    
    public Ship(Vector2 pos) {
        position = pos;
    }
    
    protected void OnHit() {
        destroyed = true;
    }
    
    public boolean CheckCords(Vector2 cords) {
        if (cords.Compare(position)) {
            OnHit();
            Map.ShowNear(position);
            return true;
        }
        return false;
    }
    
    public boolean CheckConflict(Vector2 cords) {
        for (int i = 1; i > -2; i--) {
            for (int n = -1; n < 2; n++) {
                if (cords.x + i > 7 || cords.x + i < 0)
                    continue;
                else if (cords.y + n > 7 || cords.y + n < 0)
                    continue;
                
                if (new Vector2(position.x + i, position.y + n).Compare(cords)) {
                    return true;
                } 
            }
        }
        return false;
    }
}