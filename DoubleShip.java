public class DoubleShip extends Ship {
    Vector2 secondPos;
    private boolean hit;
    
    public DoubleShip(Vector2 pos, Vector2 nPos) {
        super(pos);
        secondPos = nPos;
    }
    
    @Override
    public boolean CheckCords(Vector2 cords) {
        if (cords.Compare(position)) {
            OnHit();
            return true;
        }
        else if (cords.Compare(secondPos)) {
            OnHit();
            return true;
        }
        return false;
    }
    
    @Override
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
        for (int i = 1; i > -2; i--) {
            for (int n = -1; n < 2; n++) {
                if (cords.x + i > 7 || cords.x + i < 0)
                    continue;
                else if (cords.y + n > 7 || cords.y + n < 0)
                    continue;
                
                if (new Vector2(secondPos.x + i, secondPos.y + n).Compare(cords)) {
                    return true;
                } 
            }
        }
        return false;
    }
    
    @Override
    protected void OnHit() {
        if (hit) {
            destroyed = true;
            Map.frontMap[position.x][position.y] = CellStates.Destroyed;
            Map.frontMap[secondPos.x][secondPos.y] = CellStates.Destroyed;
            Map.ShowNear(position);
            Map.ShowNear(secondPos);
        }
        else {
            hit = true;
        }
    }
}