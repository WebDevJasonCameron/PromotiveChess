package core;

public record Move(int from, int to) {
    @Override public String toString() {
        return from + " -> " + to;
    }
}
