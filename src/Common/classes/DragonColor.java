package Common.classes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum DragonColor implements Serializable {
    RED("красный", "red"),
    ORANGE("оранжевый", "orange"),
    YELLOW("жёлтый", "yellow"),
    GREEN("зелёный", "green"),
    LITE_BLUE("голубой", "liteblue"),
    BLUE("синий", "blue"),
    VIOLET("фиолетовый", "violet"),
    BLACK("чёрный", "black"),
    GREY("серый", "grey"),
    WHITE("белый", "white");

    private final String colorName;
    private final String engColorName;

    private DragonColor(String colorName, String engColorName) {
        this.colorName = colorName;
        this.engColorName = engColorName;
    }

    private final static Map<String, DragonColor> colors = Arrays.stream(DragonColor.values())
            .collect(Collectors.toMap(k->k.colorName, v->v));
    private final static Map<String, DragonColor> engColors = Arrays.stream(DragonColor.values())
            .collect(Collectors.toMap(k->k.engColorName, v->v));

    public static DragonColor getColorByName(String colorName) {
        if (colors.get(colorName.toLowerCase()) != null){
            return colors.get(colorName.toLowerCase());
        }
        else if (engColors.get(colorName.toLowerCase()) != null)
        {
            return engColors.get(colorName.toLowerCase());
        }
        else {
            return null;
        }
    }

    public static String getAll(){
        return "[" + colors.keySet().stream().map((x) -> colors.get(x).colorName + "/" + colors.get(x).engColorName)
                .collect(Collectors.joining(", ")) + "]";
    }

    public String toString() {
        return engColorName;
    }
}