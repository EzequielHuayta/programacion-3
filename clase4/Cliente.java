package clase4;

public class Cliente {
    private int _id;
    private String _nombre;
    private int _score;

    public Cliente(int id, String nombre, int score){
        this._id = id;
        this._nombre = nombre;
        this._score = score;
    }

    int getId() {
        return this._id;
    }

    int getScore() {
        return this._score;
    }

    String getNombre() {
        return this._nombre;
    }
}
