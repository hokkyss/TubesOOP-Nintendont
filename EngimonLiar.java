import javax.swing.text.Position;

public class EngimonLiar extends Engimon{
    private Position p;

    EngimonLiar(String name, Species species, int level, Position p){
        super(name,species,level);
        this.p = p;    
    }

    public void setPosition(Position p){
        this.p = p;
    }
}
