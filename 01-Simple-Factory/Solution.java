public class Solution {
    interface Door {
        int getHeight();
        int getWidth();
    }

    static class WoodenDoor implements Door {
        private final int height;
        private final int width;

        WoodenDoor(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }

    static class DoorFactory {
        static Door makeDoor(int height, int width) {
            return new WoodenDoor(height, width);
        }
    }

    public static void main(String[] args) {
        Door door = DoorFactory.makeDoor(200, 100);

        System.out.println("Height: " + door.getHeight());
        System.out.println("Width: " + door.getWidth());
    }
}