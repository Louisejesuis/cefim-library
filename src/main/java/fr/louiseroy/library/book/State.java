package fr.louiseroy.library.book;

public enum State {
    NEUF("Neuf"),
    BON_ETAT("Bon etat"),
    ABIME("Abimé");

    private final String label;

    State(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
