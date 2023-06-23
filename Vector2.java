public class Vector2 {
    public int x;
    public int y;
    
    public void RandomVector() {
        x = Random.RandInt(0, 8);
        y = Random.RandInt(0, 8);
    }
    
    public boolean Compare(Vector2 pos) {
        if (x == pos.x && y == pos.y) {
            return true;
        }
        return false;
    }
    
    public Vector2(int posx, int posy) {
        x = posx;
        y = posy;
    }
}