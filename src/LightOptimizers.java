import java.util.List;

public static class LightOptimizers {
    public static RoomChunk generateLightGrid(int roomSize, int dx, int dz, int lightLevel, int startOffsetX, int startOffsetZ, int lineOffsetZ, boolean linearOffset){
        RoomChunk roomChunk =new RoomChunk(roomSize);
        int rowStart = startOffsetZ;
        for (int x=startOffsetX; x<roomChunk.size; x=x+dx){
            for (int z=rowStart; z<roomChunk.size; z=z+dz){
                roomChunk.addLightSource(x,z,lightLevel);
            }
            if (linearOffset) {
                rowStart = (rowStart+lineOffsetZ-dz < 0) ? rowStart+lineOffsetZ : rowStart+lineOffsetZ-dz;
            } else {rowStart = (rowStart-lineOffsetZ < 0) ? rowStart+lineOffsetZ : rowStart-lineOffsetZ;}
        }
        return roomChunk;
    }


    public class OffsetParameters{

    }
    public static List<RoomChunk> generateOptimalOptions(int roomSize,int lightLevel, boolean flag1 ){

    }



}
