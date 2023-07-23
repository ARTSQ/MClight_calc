import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomChunk {

    public class LightValue{
        public Integer lightLevel;
        public boolean isLightSource;


        public String toColoredString() {
            return (isLightSource
                    ? ANSI_YELLOW + Integer.toHexString(lightLevel).toUpperCase() + ANSI_RESET
                    : Integer.toHexString(lightLevel).toUpperCase());
        }

        public LightValue(int lightLevel, boolean isLightSource) {
            this.lightLevel = lightLevel;
            this.isLightSource = isLightSource;

        }
    }

    public class Coordinates{
        final int x;
        final int z;

        public Coordinates(int x, int z) {
            this.x = x;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, z);
        }
    }

    //HashMap<Coordinates,LightValue> roomLight = new HashMap<Coordinates,LightValue>();
    HashMap<Coordinates, LightValue> roomLight;

    final int size;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public RoomChunk(Integer roomSize) {
        this.roomLight = new HashMap<Coordinates, LightValue>();
        for (int x = 0; x<roomSize; x++){
            for (int z = 0;z<roomSize;z++){
                this.roomLight.put(new Coordinates(x,z),new LightValue(0,false));
            }
        }
        this.size=roomSize;
    }




    public String toColoredString() {
        String result = "";
        for (int x = 0; x<size; x++){
            for (int z = 0;z<size;z++){
                result = result+roomLight.get(new Coordinates(x,z)).toColoredString();
            }
            result=result+"\n";
        }
        return result;
    }



    private int safeGetElement(int x, int z){
        return roomLight.get(new Coordinates(x,z)) == null ? 0: roomLight.get(new Coordinates(x,z)).lightLevel;
    }

    private Integer[] getNeighbors(int x, int z){
        return new Integer[]{
                safeGetElement(x-1,z),
                safeGetElement(x+1,z),
                safeGetElement(x,z-1),
                safeGetElement(x,z+1)
        };
    }

        private void calculateLight(int x, int z, int lightLevel){
        
        if (x < 0 || z < 0 || x >= size || z >= size || (roomLight.get(new Coordinates(x,z)).lightLevel != 0)&& roomLight.get(new Coordinates(x,z)).lightLevel>=lightLevel || lightLevel == 0) {
            return;
        }
        roomLight.get(new Coordinates(x,z)).lightLevel=lightLevel;
        calculateLight(x-1,z,lightLevel-1);
        calculateLight(x+1,z,lightLevel-1);
        calculateLight(x,z-1,lightLevel-1);
        calculateLight(x,z+1,lightLevel-1);

    }

    public void addLightSource(int x, int z, int lightLevel){
        calculateLight(x,z,lightLevel);
        roomLight.get(new Coordinates(x,z)).isLightSource=true;

    }
}
