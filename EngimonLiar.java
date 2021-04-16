public class EngimonLiar extends Engimon implements Creature {
    private Position position;

    EngimonLiar(String name, Species species, int level, Position p) {
        super(name, species, level);
        this.position = p;
    }

    @Override
    public Position getPosition() { return position; }

    @Override
    public void setPosition(Position p) {
        this.position = p;
    }

    public void increaseLevel() {
        this.addExp(Engimon.EXP_PER_LEVEL);
    }
}
