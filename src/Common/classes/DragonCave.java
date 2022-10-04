package Common.classes;

import java.io.Serializable;
import java.util.Objects;

public class DragonCave implements Serializable {
    private int depth;

    public DragonCave(int depth){
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DragonCave)) return false;
        DragonCave that = (DragonCave) o;
        return depth == that.depth;
    }

    public int hashCode() {
        return Objects.hash(depth);
    }

    public String toString() {
        return "DragonCave{" +
                "depth=" + depth +
                '}';
    }
}