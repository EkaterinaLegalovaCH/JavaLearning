public class IdGenerator {
    private static int currentId = 1;

    public static int getNextId() {
        return currentId++;
    }

    public static void setStartingId(int maxExistingId) {
        currentId = maxExistingId + 1;
    }

}
